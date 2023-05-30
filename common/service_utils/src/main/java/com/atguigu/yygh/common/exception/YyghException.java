package com.atguigu.yygh.common.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiZhanHong
 * @Date: 2023/05/17/10:53
 * @Description:
 */
public class YyghException extends RuntimeException{

    private Integer code;//响应码
    private String message;//说明信息

    public YyghException(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
