package com.atguigu.yygh.common.handler;

import com.atguigu.yygh.common.exception.YyghException;
import com.atguigu.yygh.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiZhanHong
 * @Date: 2023/05/17/10:25
 * @Description:
 */
@ControllerAdvice//凡是由@ControllerAdvice 标记的类都表示全局异常处理类
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    //返回json数据
    @ResponseBody
    public R handleException (Exception ex){
        ex.printStackTrace();//输出异常：日志文件
        log.error(ex.getMessage());
        return R.error().message(ex.getMessage()).code(20003);
    }


    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public R sqlException (SQLException sqlException){

        sqlException.printStackTrace();
        log.error(sqlException.getMessage());
        return R.error().message("SQL异常");
    }

    @ExceptionHandler(value = ArithmeticException.class )//细粒度的异常处理
    @ResponseBody
    public R handleArithmeticException(ArithmeticException ex){
        ex.printStackTrace();//输出异常：日志文件
       log.error(ex.getMessage());
        return R.error().message("数学异常");
    }

    @ExceptionHandler(value = RuntimeException.class )//细粒度的异常处理
    @ResponseBody
    public R handleRuntimeException(RuntimeException ex){
        ex.printStackTrace();//输出异常：日志文件
        log.error(ex.getMessage());
        return R.error().message("编译时异常");
    }

    @ExceptionHandler(value = YyghException.class )//细粒度的异常处理
    public R handleYyghException(YyghException ex){
        ex.printStackTrace();//输出异常：日志文件
        log.error(ex.getMessage());
        return R.error().message(ex.getMessage()).code(ex.getCode());
    }

}
