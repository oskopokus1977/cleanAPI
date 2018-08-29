package com.repository;

import com.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    District getByName(String name);

    District getById(Integer id);

    District getByIdOrName(Integer id, String name);
}
