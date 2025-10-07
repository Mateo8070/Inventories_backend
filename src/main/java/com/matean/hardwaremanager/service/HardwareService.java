package com.matean.hardwaremanager.service;

import com.matean.hardwaremanager.exception.ItemAlreadyExistsException;
import com.matean.hardwaremanager.model.Hardware;
import com.matean.hardwaremanager.repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HardwareService {

    @Autowired
    private HardwareRepository hardwareRepository;

    public List<Hardware> getAllHardware() {
        return hardwareRepository.findAllWithCategory();
    }

    public Hardware getHardwareById(UUID id) {
        return hardwareRepository.findByIdWithCategory(id).orElse(null);
    }

    public Hardware saveHardware(Hardware hardware) {
        // Check if it's a new entity (ID is null)
        if (hardware.getId() == null) {
            Optional<Hardware> existingHardware = hardwareRepository.findByDescription(hardware.getDescription());
            if (existingHardware.isPresent()) {
                throw new ItemAlreadyExistsException("Hardware with description '" + hardware.getDescription() + "' already exists.");
            }
        }
        return hardwareRepository.save(hardware);
    }

    public void deleteHardware(UUID id) {
        hardwareRepository.deleteById(id);
    }
}
