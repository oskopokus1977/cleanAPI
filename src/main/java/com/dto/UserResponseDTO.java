package com.dto;

import io.swagger.annotations.ApiModelProperty;
import com.model.Role;

import java.util.List;

public class UserResponseDTO {

  @ApiModelProperty(position = 1)
  private String token;

  @ApiModelProperty(position = 2)
  private Integer id;
  @ApiModelProperty(position = 3)
  private String username;
  @ApiModelProperty(position = 4)
  private String email;
  @ApiModelProperty(position = 5)
  private boolean notify;
  @ApiModelProperty(position = 6)
  private String phone;
  @ApiModelProperty(position = 7)
  private String createdDate;
  @ApiModelProperty(position = 8)
  List<Role> roles;


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public boolean isNotify() {
    return notify;
  }

  public void setNotify(boolean notify) {
    this.notify = notify;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}
