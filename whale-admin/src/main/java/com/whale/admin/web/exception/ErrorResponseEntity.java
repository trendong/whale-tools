package com.whale.admin.web.exception;

/**
 * 异常信息模板
 *
 * @author trendong
 */
public class ErrorResponseEntity {

    private int code;
    private String message;

    public ErrorResponseEntity() {
    }

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
