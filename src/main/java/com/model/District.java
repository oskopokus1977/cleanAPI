package com.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "districts")
public class District {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", length = 35)
  private String name;

  @OneToMany(mappedBy = "district")
  private List<Item> items;

  @OneToMany(mappedBy = "districts")
  private List<UserItemFilter> usersItemFilters;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public List<UserItemFilter> getUsersItemFilters() {
    return usersItemFilters;
  }

  public void setUsersItemFilters(List<UserItemFilter> usersItemFilters) {
    this.usersItemFilters = usersItemFilters;
  }
  
}
