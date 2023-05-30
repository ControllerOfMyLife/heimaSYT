package com.atguigu.yygh.cmn.service.impl;

import com.atguigu.yygh.cmn.mapper.DictMapper;
import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiZhanHong
 * @Date: 2023/05/29/14:33
 * @Description:
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Override
    //,key = "'selectIndexList'+#pid"
//    @Cacheable(value = "abc",key = "'selectIndexList'+#pid")
    public List<Dict> getChildListByPid(Long pid) {
        QueryWrapper<Dict> queryWrapper=new QueryWrapper<Dict>();
        queryWrapper.eq("parent_id",pid);
        List<Dict> dicts = baseMapper.selectList(queryWrapper);
        for (Dict dict : dicts) {
            dict.setHasChildren(isHasChildren(dict.getId()));
        }
        return dicts;
    }

    private boolean isHasChildren(Long pid) {
        QueryWrapper<Dict> queryWrapper=new QueryWrapper<Dict>();
        queryWrapper.eq("parent_id",pid);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count >0;
    }

    @Override
    public void download(HttpServletResponse response) throws IOException {

    }

    @Override
    public void upload(MultipartFile file) throws IOException {

    }

    @Override
    public String getNameByValue(Long value) {
        return null;
    }

    @Override
    public String getNameByDictCodeAndValue(String dictCode, Long value) {
        return null;
    }
}
