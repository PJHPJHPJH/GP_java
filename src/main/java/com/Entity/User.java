package com.Entity;

import lombok.Data;


@Data
public class User {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
}
