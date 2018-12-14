package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ResultDTO{
    private Integer code;
    private String message;
    private Object data;
}