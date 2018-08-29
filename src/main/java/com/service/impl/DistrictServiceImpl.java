package com.service.impl;

import com.exception.CustomException;
import com.model.District;
import com.repository.DistrictRepository;
import com.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        if (districts.size() == 0) {
            throw new CustomException("Not found districts", HttpStatus.NOT_FOUND);
        }
        return districts;
    }

    @Override
    public District addDistrict(District district) {
        if (district.getName() == null) {
            throw new CustomException("District name not entered", HttpStatus.BAD_REQUEST);
        }
        District byName = districtRepository.getByName(district.getName());
        if (byName != null) {
            throw new CustomException("District name '" + district.getName() + "' is already exists", HttpStatus.CONFLICT);
        }
        if (district.getId() != null) {
            District byId = districtRepository.getById(district.getId());
            if (byId != null) {
                throw new CustomException("District id '" + district.getId() + "' is already exists", HttpStatus.CONFLICT);
            }
        }
        return districtRepository.saveAndFlush(district);
    }

    @Override
    public District delete(Integer id) {
        District district = districtRepository.getById(id);
        if (district == null) {
            throw new CustomException("District not found", HttpStatus.NOT_FOUND);
        }
        districtRepository.delete(district);
        return district;
    }

    @Override
    public District getByIdOrName(Integer id, String name) {
        District district = districtRepository.getByIdOrName(id, name);
        if (district == null) {
            throw new CustomException("District not found", HttpStatus.NOT_FOUND);
        }
        return district;
    }

    @Override
    public District editDistrict(Integer id, District district) {
        District existingDistrict = districtRepository.getById(id);
        if (existingDistrict == null) {
            throw new CustomException("District not found", HttpStatus.NOT_FOUND);
        }
        if (district.getName() == null) {
            throw new CustomException("District name not entered", HttpStatus.BAD_REQUEST);
        }
        district.setId(id);
        District editedDistrict = districtRepository.saveAndFlush(district);
        return editedDistrict;
    }
}
