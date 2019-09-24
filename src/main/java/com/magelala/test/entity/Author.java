package com.magelala.test.entity;

import lombok.Data;

@Data
public class Author {

    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String tel;
    private String email;
    private String role;
    private int articleNum;

}
