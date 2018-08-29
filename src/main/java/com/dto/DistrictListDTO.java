package com.dto;

import java.util.List;

public class DistrictListDTO {

    private List<DistrictDTO> districtDTOList;

    public DistrictListDTO(List<DistrictDTO> districtDTOList) {
        this.districtDTOList = districtDTOList;
    }

    public DistrictListDTO() {
    }

    public List<DistrictDTO> getDistrictDTOList() {
        return districtDTOList;
    }

    public void setDistrictDTOList(List<DistrictDTO> districtDTOList) {
        this.districtDTOList = districtDTOList;
    }
}
