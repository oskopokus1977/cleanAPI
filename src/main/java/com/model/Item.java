package com.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "item_creation_date", nullable = false)
    private String itemCreatingDateTime;

    @Column(name = "cleaning_date", nullable = false)
    private String cleaningDateTime;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Type(type = "org.hibernate.type.EnumType")
    @Column(name = "status", length = 50)
    private ItemStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public String getItemCreatingDateTime() {
        return itemCreatingDateTime;
    }

    public void setItemCreatingDateTime(String itemCreatingDateTime) {
        this.itemCreatingDateTime = itemCreatingDateTime;
    }

    public String getCleaningDateTime() {
        return cleaningDateTime;
    }

    public void setCleaningDateTime(String cleaningDateTime) {
        this.cleaningDateTime = cleaningDateTime;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
