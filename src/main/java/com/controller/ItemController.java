package com.controller;

import com.dto.ItemRequestDTO;
import com.dto.ItemResponseDTO;
import com.model.Item;
import com.model.User;
import com.service.ItemService;
import com.service.UserService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")//crossBrowser enable
@RestController
@RequestMapping("/items")
@Api(tags = "Items")
public class ItemController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;
    @PostMapping(value = "/add_item")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ItemResponseDTO createItem(HttpServletRequest req,
                                      @RequestBody ItemRequestDTO itemRequestDTO) {

        Item item = modelMapper.map(itemRequestDTO, Item.class);
        Integer serviceId = itemRequestDTO.getServiceId();
        Integer districtId = itemRequestDTO.getDistrictId();
        Item created_item = itemService.createItem(item, serviceId, districtId, req);
        return modelMapper.map(created_item, ItemResponseDTO.class);
    }

}