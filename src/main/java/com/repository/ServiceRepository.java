package com.repository;

import com.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Service getByName(String name);
    Service getById(Integer id);
    Service getByIdOrName(Integer id, String name);
}
