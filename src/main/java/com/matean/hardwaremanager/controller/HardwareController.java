package com.matean.hardwaremanager.controller;

import com.matean.hardwaremanager.dto.CategoryDto;
import com.matean.hardwaremanager.dto.HardwareDto;
import com.matean.hardwaremanager.exception.ItemAlreadyExistsException;
import com.matean.hardwaremanager.model.Category;
import com.matean.hardwaremanager.model.Hardware;
import com.matean.hardwaremanager.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hardware")
public class HardwareController {

    @Autowired
    private HardwareService hardwareService;

    @GetMapping
    public List<HardwareDto> getAllHardware() {
        List<Hardware> hardwareList = hardwareService.getAllHardware();
        List<HardwareDto> hardwareDtoList = hardwareList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return hardwareDtoList;
    }

    @GetMapping("/{id}")
    public HardwareDto getHardwareById(@PathVariable UUID id) {
        Hardware hardware = hardwareService.getHardwareById(id);
        if (hardware == null) {
            return null; // Or throw a NotFoundException
        }
        HardwareDto hardwareDto = convertToDto(hardware);
        return hardwareDto;
    }

    private HardwareDto convertToDto(Hardware hardware) {
        if (hardware == null) {
            return null;
        }
        return new HardwareDto(
                hardware.getId(),
                hardware.getDescription(),
                hardware.getQuantity(),
                hardware.getWholesalePrice(),
                hardware.getRetailPrice(),
                hardware.getUpdatedAt(),
                hardware.isDeleted(),
                hardware.getWholesalePriceUnit(),
                hardware.getRetailPriceUnit(),
                hardware.getUpdatedBy(),
                hardware.getLocation(),
                hardware.getCategory(),
                hardware.getImageUrl()
        );
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public HardwareDto createHardware(@RequestPart("hardware") HardwareDto hardwareDto, @RequestPart("file") MultipartFile file) {
        Hardware hardware = convertToEntity(hardwareDto);
        Hardware savedHardware = hardwareService.saveHardware(hardware, file);
        return convertToDto(savedHardware);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public HardwareDto updateHardware(@PathVariable UUID id, @RequestPart("hardware") HardwareDto hardwareDto, @RequestPart(value = "file", required = false) MultipartFile file) {
        Hardware hardware = convertToEntity(hardwareDto);
        hardware.setId(id);
        Hardware updatedHardware = hardwareService.saveHardware(hardware, file);
        return convertToDto(updatedHardware);
    }

    private Hardware convertToEntity(HardwareDto hardwareDto) {
        if (hardwareDto == null) {
            return null;
        }
        Hardware hardware = new Hardware();
        hardware.setId(hardwareDto.getId());
        hardware.setDescription(hardwareDto.getDescription());
        hardware.setQuantity(hardwareDto.getQuantity());
        hardware.setWholesalePrice(hardwareDto.getWholesalePrice());
        hardware.setRetailPrice(hardwareDto.getRetailPrice());
        hardware.setUpdatedAt(hardwareDto.getUpdatedAt());
        hardware.setDeleted(hardwareDto.isDeleted());
        hardware.setWholesalePriceUnit(hardwareDto.getWholesalePriceUnit());
        hardware.setRetailPriceUnit(hardwareDto.getRetailPriceUnit());
        hardware.setUpdatedBy(hardwareDto.getUpdatedBy());
        hardware.setLocation(hardwareDto.getLocation());
        hardware.setCategory(hardwareDto.getCategory());
        hardware.setImageUrl(hardwareDto.getImageUrl());
        return hardware;
    }

    @DeleteMapping("/{id}")
    public void deleteHardware(@PathVariable UUID id) {
        hardwareService.deleteHardware(id);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<String> handleItemAlreadyExistsException(ItemAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
