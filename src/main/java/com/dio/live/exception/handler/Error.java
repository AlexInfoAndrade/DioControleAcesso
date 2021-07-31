package com.dio.live.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Error implements Serializable {

    private Integer code;
    private String msg;
    private Long timeStamp;
}
