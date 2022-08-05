package com.yql.webservice.entity;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Package com.yql.webservice.entity
 * @ClassName User
 * @Description TODO
 * @Author Ryan
 * @Date 2022/8/5
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -3248459217804755356L;
    private Long id;
    private String name;
    private Integer age;
    private String birthday;
    // WebService 对参数类型有限制 无法直接使用 LocalDate
//    private LocalDate birthday;
    private List<String> hobbies;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

