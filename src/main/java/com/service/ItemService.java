package com.service;

import com.model.Item;

import javax.servlet.http.HttpServletRequest;

public interface ItemService {
    Item createItem(Item item, Integer serviceId, Integer districtId, HttpServletRequest req);
}
