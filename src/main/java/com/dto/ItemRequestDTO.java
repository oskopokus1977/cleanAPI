package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.ItemStatus;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ItemRequestDTO {

    @JsonIgnore
    private Integer id;
    @ApiModelProperty(position = 1)
    private Integer serviceId;
    @ApiModelProperty(position = 2)
    private Integer districtId;
    @ApiModelProperty(position = 3)
    private String description;
    @ApiModelProperty(position = 4)
    private String address;
    @ApiModelProperty(position = 5)
    private Integer price;
    @ApiModelProperty(position = 6)
    private String cleaningDateTime;
    @JsonIgnore
    @ApiModelProperty(position = 7)
    private ItemStatus status = ItemStatus.NEW;
    @JsonIgnore
    @ApiModelProperty(position = 8)
    private String itemCreatingDateTime = new Date().toString();

    public ItemRequestDTO() {
    }

    public ItemRequestDTO(Integer serviceId, Integer districtId, String description, String address, Integer price, String cleaningDateTime) {
        this.serviceId = serviceId;
        this.districtId = districtId;
        this.description = description;
        this.address = address;
        this.price = price;
        this.cleaningDateTime = cleaningDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCleaningDateTime() {
        return cleaningDateTime;
    }

    public void setCleaningDateTime(String cleaningDateTime) {
        this.cleaningDateTime = cleaningDateTime;
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
}

