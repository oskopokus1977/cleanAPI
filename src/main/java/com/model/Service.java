package com.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", length = 30, unique = true, nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "service")
  private List<Item> items;

  @ManyToMany(mappedBy = "services")
  private List<UserItemFilter> usersFilters;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public List<UserItemFilter> getUsersFilters() {
    return usersFilters;
  }

  public void setUsersFilters(List<UserItemFilter> usersFilters) {
    this.usersFilters = usersFilters;
  }
}
