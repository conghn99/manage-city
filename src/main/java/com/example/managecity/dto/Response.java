package com.example.managecity.dto;

import com.example.managecity.validate.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private T data;
    private int code;
    private String message;

    public Response(ResponseStatus status){
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public Response(T data) {
        this.data = data;
        this.code = ResponseStatus.OK.getCode();
        this.message = ResponseStatus.OK.getMessage();
    }
}
