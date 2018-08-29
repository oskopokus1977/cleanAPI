//package com.model;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//import java.util.List;
//
//@Entity
//@Table(name = "users")
//public class User {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Integer id;
//
//  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
//  @Column(unique = true, nullable = false)
//  private String username;
//
//  @Column(unique = true, nullable = false)
//  private String email;
//
//  @Size(min = 8, message = "Minimum password length: 8 characters")
//  private String password;
//
//  @ElementCollection(fetch = FetchType.EAGER)
//  List<Role> roles;
//
//  public Integer getId() {
//    return id;
//  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }
//
//  public String getUsername() {
//    return username;
//  }
//
//  public void setUsername(String username) {
//    this.username = username;
//  }
//
//  public String getEmail() {
//    return email;
//  }
//
//  public void setEmail(String email) {
//    this.email = email;
//  }
//
//  public String getPassword() {
//    return password;
//  }
//
//  public void setPassword(String password) {
//    this.password = password;
//  }
//
//  public List<Role> getRoles() {
//    return roles;
//  }
//
//  public void setRoles(List<Role> roles) {
//    this.roles = roles;
//  }
//
//}


package com.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "username", unique = true, nullable = false)
    @Size(min=5, message="Name should have atleast 5 characters")
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min=5, message="Passport should have atleast 5 characters")
    private String password;

    @Column(name = "phone", unique = true)
    private String phone;

    @Email(regexp = "^([a-z0-9_\\.-]+)@([a-z0-9][a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "creation_date")
    private String createdDate;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    @Column(name = "notify")
    private boolean notify;

    @Column(name = "reset_token", length = 36)
    private String resetToken;


    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserItemFilter userItemFilter;


    @OneToMany(mappedBy = "customer")
    private List<Item> createdItems;


    @OneToMany(mappedBy = "executor")
    private List<TakenItem> takenItems;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserItemFilter getUserItemFilter() {
        return userItemFilter;
    }

    public void setUserItemFilter(UserItemFilter userItemFilter) {
        this.userItemFilter = userItemFilter;
    }


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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Item> getCreatedItems() {
        return createdItems;
    }

    public void setCreatedItems(List<Item> createdItems) {
        this.createdItems = createdItems;
    }

    public List<TakenItem> getTakenItems() {
        return takenItems;
    }

    public void setTakenItems(List<TakenItem> takenItems) {
        this.takenItems = takenItems;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
