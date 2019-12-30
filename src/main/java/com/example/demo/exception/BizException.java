package com.example.demo.exception;

public class BizException extends RuntimeException {
    private String msg;
    BizException() {
        super();
    }
    public BizException(String msg){
        super(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
