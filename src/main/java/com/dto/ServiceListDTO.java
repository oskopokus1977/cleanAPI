package com.dto;

import java.util.List;

public class ServiceListDTO {
    private List<ServiceDTO> serviceDTOList;

    public ServiceListDTO() {
    }

    public ServiceListDTO(List<ServiceDTO> serviceDTOList) {
        this.serviceDTOList = serviceDTOList;
    }

    public List<ServiceDTO> getServiceDTOList() {
        return serviceDTOList;
    }

    public void setServiceDTOList(List<ServiceDTO> serviceDTOList) {
        this.serviceDTOList = serviceDTOList;
    }

}
