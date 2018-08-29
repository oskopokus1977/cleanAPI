package com.model;

import javax.persistence.*;

@Entity
@Table(name = "taken_item")
public class TakenItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @OneToOne
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne
  @JoinColumn(name = "executor_id")
  private User executor;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public User getExecutor() {
    return executor;
  }

  public void setExecutor(User executor) {
    this.executor = executor;
  }

}
