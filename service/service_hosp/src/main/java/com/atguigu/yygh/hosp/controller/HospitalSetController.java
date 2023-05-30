package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.common.utils.MD5;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiZhanHong
 * @Date: 2023/05/16/9:26
 * @Description:
 */
@RestController
@Api(tags = "医院设置信息")
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;


    @ApiOperation(value = "查询所有的医院设置信息")
    @GetMapping("findAll")
    public R findAll (){
        List<HospitalSet> list = hospitalSetService.list();
        return R.ok().data("list",list);
    }

    //根据设置id删除数据
    @ApiOperation(value = "根据医院设置id删除医院设置信息")
    @DeleteMapping("/deleteById/{id}")
    public R deleteById(@PathVariable Integer id){
        hospitalSetService.removeById(id);
        return  R.ok();
    }

    // /admin/hosp/hospitalSet/page/{pageNum}/{size}
    //带条件分页查询,QueryVo,用户输入的查询条件
    @ApiOperation(value = "带查询条件的分页")
    @PostMapping(value = "/page/{pageNum}/{size}")
    public R getPageInfo(@ApiParam(name = "pageNum",value = "当前页")@PathVariable Integer pageNum,
                         @ApiParam(name = "size",value = "每页显示多少条") @PathVariable Integer size,
                         @RequestBody HospitalSetQueryVo hospitalSetQueryVo){

        Page<HospitalSet> page = new Page<HospitalSet>(pageNum,size);

        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<HospitalSet>();

        if (!StringUtils.isEmpty(hospitalSetQueryVo.getHosname())){

            //表的列名匹配vo的属性
            queryWrapper.like("hosname",hospitalSetQueryVo.getHosname());

        }

        if (!StringUtils.isEmpty(hospitalSetQueryVo.getHoscode())){
            //精确查询
            queryWrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());

        }


        //MybatisPlus自带的分页查询方法
        hospitalSetService.page(page, queryWrapper);
        return R.ok().data("total",page.getTotal()).data("rows",page.getRecords());

    }

    //新增医院接口
    @ApiOperation(value = "新增医院")
    @PostMapping("/save")
    public  R save(@RequestBody  HospitalSet hospitalSet){

        //设置状态 1 使用 0 不能使用
        hospitalSet.setStatus(1);

        //当前时间戳+随机数+MD5加密
        Random random = new Random();

        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        hospitalSetService.save(hospitalSet);

        return R.ok();
    }

    //修改之回显数据
    @GetMapping( "detail/{id}")
    public R detail (@PathVariable Integer id){

        HospitalSet byId = hospitalSetService.getById(id);
        return R.ok().data("item",byId);
    }


    //修改之真修改数据,前端传来一个HosSet对象
    @PutMapping("/update")
    public R update (@RequestBody HospitalSet hospitalSet){

        hospitalSetService.updateById(hospitalSet);
        return R.ok();
    }

    //批量删除,传来要删除的id的集合
    @DeleteMapping("/delete")
    public R batchDelete (@RequestBody List<Integer> ids){
        hospitalSetService.removeByIds(ids);
        return R.ok();
    }

    //页面锁定与解锁
    @PutMapping("/status/{id}/{status}")
    public R updateStatus(@PathVariable long id, @PathVariable Integer status){
        //找数据，改状态，更新
        HospitalSet byId = hospitalSetService.getById(id);
        byId.setStatus(status);
        hospitalSetService.updateById(byId);

        //        HospitalSet hospitalSet=new HospitalSet();
        //        hospitalSet.setId(id);
        //        hospitalSet.setStatus(status);
        //        hospitalSetService.updateById(hospitalSet);
        return R.ok();
    }
}
