package com.service;

import com.model.Service;

import java.util.List;

public interface ServiceService {

    List<Service> getAllServices();
    Service delete(Integer id);
    Service getByIdOrName(Integer id, String name);
    Service editService(Integer id, Service service);
    Service addService(Service service);
}
