package com.service.impl;

import com.exception.CustomException;
import com.model.District;
import com.model.Item;
import com.model.Service;
import com.model.User;
import com.repository.DistrictRepository;
import com.repository.ItemRepository;
import com.repository.ServiceRepository;
import com.service.ItemService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@org.springframework.stereotype.Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Item createItem(Item item, Integer serviceId, Integer districtId, HttpServletRequest req) {



        validateItem(item);
        Service service = serviceRepository.getById(serviceId);
        if (service == null) {
            throw new CustomException("Unknown service id", HttpStatus.NOT_ACCEPTABLE);
        }
        District district = districtRepository.getById(districtId);
        if (district == null) {
            throw new CustomException("Unknown district id", HttpStatus.NOT_ACCEPTABLE);
        }
        User user = userService.whoami(req);
        item.setCustomer(user);
        item.setService(service);
        item.setDistrict(district);
        itemRepository.save(item);
        return item;
    }

    private void validateItem(Item item) {
        if (item.getAddress() == null) {
            throw new CustomException("Address not entered", HttpStatus.BAD_REQUEST);
        }
        if (item.getPrice() == null) {
            throw new CustomException("Price not entered", HttpStatus.BAD_REQUEST);
        }
        if (item.getCleaningDateTime() == null) {
            throw new CustomException("Cleaning date note entered", HttpStatus.BAD_REQUEST);
        }

    }
}
