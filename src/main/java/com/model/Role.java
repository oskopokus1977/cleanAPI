package com.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ROLE_ADMIN, ROLE_CLIENT;

  public String getAuthority() {
    return name();
  }

}

//package com.model;
//
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "roles")
//public class Role {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private int id;
//
//  @Column(name = "name")
//  private String name;
//
//  @ManyToMany(mappedBy = "roles")
//  private Set<User> users;
//
//  public Role() {
//  }
//
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public Set<User> getUsers() {
//    return users;
//  }
//
//  public void setUsers(Set<User> users) {
//    this.users = users;
//  }
//
//  @Override
//  public String toString() {
//    return "Role{" +
//            "id=" + id +
//            ", name='" + name + '\'' +
//            ", users=" + users +
//            '}';
//  }
//
//}
