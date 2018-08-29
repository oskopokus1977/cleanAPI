package com.controller;

import com.dto.DistrictDTO;
import com.dto.DistrictListDTO;
import com.model.District;
import com.service.DistrictService;
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
@RequestMapping("/districts")
@Api(tags = "Districts")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    @ApiOperation(value = "${DistrictController.all}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")
    })
    public ResponseEntity<DistrictListDTO> getAllDistricts() {
        List<District> districts = districtService.getAllDistricts();
        List<DistrictDTO> districtDTOList = new ArrayList<>();
        for (District district : districts) {
            DistrictDTO districtDTO = modelMapper.map(district, DistrictDTO.class);
            districtDTOList.add(districtDTO);
        }
        return new ResponseEntity<>(new DistrictListDTO(districtDTOList), HttpStatus.OK);

    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${DistrictController.add}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")
    })
    public ResponseEntity<DistrictDTO> addDistrict(@ApiParam("District entity") @RequestBody DistrictDTO districtReqDTO) {
        District district = modelMapper.map(districtReqDTO, District.class);
        District addedDistrict = districtService.addDistrict(district);
        DistrictDTO districtRespDTO = modelMapper.map(addedDistrict, DistrictDTO.class);
        return new ResponseEntity<>(districtRespDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${DistrictController.delete}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 503, message = "Server is busy")
    })
    public ResponseEntity<DistrictDTO> deleteDistrict(@ApiParam("District id") @PathVariable Integer id) {
        District deletedDistrict = districtService.delete(id);
        DistrictDTO districtDTO = modelMapper.map(deletedDistrict, DistrictDTO.class);
        return new ResponseEntity<>(districtDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    @ApiOperation(value = "${DistrictController.get}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 503, message = "Server is busy")
    })
    public ResponseEntity<DistrictDTO> getDistrictByIdOrName(
            @ApiParam("District id")
            @RequestParam(name = "id", required = false) Integer id,
            @ApiParam("District name")
            @RequestParam(name = "name", required = false) String name) {
        District district = districtService.getByIdOrName(id, name);
        DistrictDTO districtDTO = modelMapper.map(district, DistrictDTO.class);
        return new ResponseEntity<>(districtDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${DistrictController.edit}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not found item types"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 503, message = "Server is busy")
    })
    public ResponseEntity<DistrictDTO> editService(
            @ApiParam("District id")
            @PathVariable Integer id,
            @ApiParam("District entity")
            @RequestBody DistrictDTO districtReqDTO) {
        District district = modelMapper.map(districtReqDTO, District.class);
        District updatedDistrict = districtService.editDistrict(id, district);
        DistrictDTO districtRespDTO = modelMapper.map(updatedDistrict, DistrictDTO.class);
        return new ResponseEntity<>(districtRespDTO, HttpStatus.OK);
    }
}
