package com.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users_info")
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "first_name", length = 50)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 50)
  private String lastName;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender", length = 11)
  private Gender gender;
//  @Column(name = "birthday")
//  @Type(type = "org.hibernate.type.LocalDateType")
//  private LocalDate birthday;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
