package com.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserLoginDTO {

    @ApiModelProperty(position = 1)
    private String email;
    @ApiModelProperty(position = 2)
    private String phone;
    @ApiModelProperty(position = 3)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
