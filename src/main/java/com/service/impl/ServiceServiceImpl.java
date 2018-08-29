package com.service.impl;

import com.exception.CustomException;
import com.model.Service;
import com.repository.ServiceRepository;
import com.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<Service> getAllServices() {

        List<Service> services = serviceRepository.findAll();
        if (services.size() == 0) {
            throw new CustomException("Not found item types", HttpStatus.NOT_FOUND);
        }
        System.out.println(services);
        return services;
    }

    @Override
    public Service delete(Integer id) {
        Service service = serviceRepository.getById(id);
        if (service == null) {
            throw new CustomException("Service not found", HttpStatus.NOT_FOUND);
        }
        serviceRepository.delete(service);
        return service;
    }

    @Override
    public Service getByIdOrName(Integer id, String name) {
        Service service = serviceRepository.getByIdOrName(id, name);
        if (service == null) {
            throw new CustomException("Service not found", HttpStatus.NOT_FOUND);
        }
        return service;
    }


    @Override
    public Service editService(Integer id, Service service) {
        Service existingService = serviceRepository.getById(id);
        if (existingService == null) {
            throw new CustomException("Service not found", HttpStatus.NOT_FOUND);
        }
        if (service.getName() == null) {
            throw new CustomException("Service name not entered", HttpStatus.BAD_REQUEST);
        }
        service.setId(id);
        Service editedService = serviceRepository.saveAndFlush(service);
        return editedService;
    }

    @Override
    public Service addService(Service service) {
        if (service.getName() == null) {
            throw new CustomException("Service name not entered", HttpStatus.BAD_REQUEST);
        }
        Service byName = serviceRepository.getByName(service.getName());
        if (byName != null) {
            throw new CustomException("Service name " + service.getName() + " is already exists", HttpStatus.CONFLICT);
        }
        if (service.getId() != null) {
            Service byId = serviceRepository.getById(service.getId());
            if (byId != null) {
                throw new CustomException("Service id " + service.getId() + " is already exists", HttpStatus.CONFLICT);
            }
        }
        return serviceRepository.saveAndFlush(service);
    }
}
