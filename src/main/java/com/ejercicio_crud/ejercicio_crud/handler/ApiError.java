package com.ejercicio_crud.ejercicio_crud.handler;

import java.util.Date;

public class ApiError {
    private Integer errorCode;
    private String errorMessage;
    private Date date;

    public ApiError(Integer errorCode, String errorMessage, Date date) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.date = date;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
