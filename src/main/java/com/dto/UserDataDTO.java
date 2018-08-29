package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Role;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.model.Role.ROLE_ADMIN;
import static com.model.Role.ROLE_CLIENT;

public class UserDataDTO {

    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    private String password;
    @ApiModelProperty(position = 2)
    private String email;
    @ApiModelProperty(position = 3)
    private String phone;
    @ApiModelProperty(position = 4)
    private boolean notify;
    @ApiModelProperty(position = 5)
    @JsonIgnore
    List<Role> roles = new ArrayList<>(Arrays.asList(ROLE_CLIENT));
    @ApiModelProperty(position = 6)
    @JsonIgnore
    private String createdDate = new Date().toString();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public boolean isNotify() {return notify;}

    public void setNotify(boolean notify) {this.notify = notify;}

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
