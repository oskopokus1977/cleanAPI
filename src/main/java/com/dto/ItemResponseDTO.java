package com.dto;

import com.model.ItemStatus;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ItemResponseDTO {

    @ApiModelProperty(position = 1)
    private Integer id;
    @ApiModelProperty(position = 2)
    private ItemStatus status = ItemStatus.NEW;
    @ApiModelProperty(position = 3)
    private String description;
    @ApiModelProperty(position = 4)
    private ServiceDTO service;
    @ApiModelProperty(position = 5)
    private DistrictDTO district;
    @ApiModelProperty(position = 6)
    private String address;
    @ApiModelProperty(position = 7)
    private Integer price;
    @ApiModelProperty(position = 8)
    private String cleaningDateTime;
    @ApiModelProperty(position = 9)
    private String itemCreatingDateTime = new Date().toString();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
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

    public String getItemCreatingDateTime() {
        return itemCreatingDateTime;
    }

    public void setItemCreatingDateTime(String itemCreatingDateTime) {
        this.itemCreatingDateTime = itemCreatingDateTime;
    }
}
