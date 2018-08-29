package com.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_item_filter")
public class UserItemFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "enable")
    private Boolean enable;

    @ManyToMany
    @JoinTable(
            name = "user_services",
            joinColumns = @JoinColumn(name = "userfilte_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private List<Service> services;

    @Column(name = "max_price")
    private Integer maxPrice;

    @Column(name = "min_price")
    private Integer minPrice;

    @ManyToMany
    @JoinTable(
            name = "user_districts",
            joinColumns = @JoinColumn(name = "userfilter_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "district_id", referencedColumnName = "id"))
    private List<District> districts;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
