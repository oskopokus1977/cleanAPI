package com.controller;

import com.dto.ServiceDTO;
import com.dto.ServiceListDTO;
import com.exception.CustomException;
import com.model.Service;
import com.service.ServiceService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")//crossBrowser enable
@RestController
@RequestMapping("/services")
@Api(tags = "Services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${ServiceController.all}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")
    })
    public ResponseEntity<Object> getAllServices() {
        List<Service> services = serviceService.getAllServices();
        List<ServiceDTO> serviceDTOList = new ArrayList<>();
        for (Service service : services) {
            ServiceDTO serviceDTO = modelMapper.map(service, ServiceDTO.class);
            serviceDTOList.add(serviceDTO);
        }
        return new ResponseEntity<>(new ServiceListDTO(serviceDTOList), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ServiceController.add}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")
    })
    public ResponseEntity<Object> addService(@ApiParam("Service entity") @RequestBody ServiceDTO serviceReqDTO) {
        Service service = modelMapper.map(serviceReqDTO, Service.class);
        Service addedService = serviceService.addService(service);
        ServiceDTO serviceRespDTO = modelMapper.map(addedService, ServiceDTO.class);
        return new ResponseEntity<>(serviceRespDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ServiceController.delete}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 503, message = "Server is busy")
    })
    public ResponseEntity<ServiceDTO> deleteService(@ApiParam("Service id") @PathVariable Integer id) {
        Service deletedService = serviceService.delete(id);
        ServiceDTO serviceDTO = modelMapper.map(deletedService, ServiceDTO.class);
        return new ResponseEntity<>(serviceDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/get")
    @ApiOperation(value = "${ServiceController.get}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 503, message = "Server is busy")
    })
    public ResponseEntity<ServiceDTO> getServiceById(
            @ApiParam("Service id")
            @RequestParam(name = "id", required = false) Integer id,
            @ApiParam("Service name")
            @RequestParam(name = "name", required = false) String name) {
        Service deletedService = serviceService.getByIdOrName(id, name);
        ServiceDTO serviceDTO = modelMapper.map(deletedService, ServiceDTO.class);
        return new ResponseEntity<>(serviceDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ServiceController.edit}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 503, message = "Server is busy")
    })
    public ResponseEntity<ServiceDTO> editService(
            @ApiParam("Service id")
            @PathVariable Integer id,
            @ApiParam("Service entity")
            @RequestBody ServiceDTO serviceReqDTO) {
        Service service = modelMapper.map(serviceReqDTO, Service.class);
        Service updatedService = serviceService.editService(id, service);
        ServiceDTO serviceRespDTO = modelMapper.map(updatedService, ServiceDTO.class);
        return new ResponseEntity<>(serviceRespDTO, HttpStatus.OK);
    }
}
