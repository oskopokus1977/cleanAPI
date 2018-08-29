package com.service;

import com.model.District;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DistrictService {
    List<District> getAllDistricts();

    District addDistrict(District district);

    District delete(Integer id);

    District getByIdOrName(Integer id, String name);

    District editDistrict(Integer id, District district);
}
