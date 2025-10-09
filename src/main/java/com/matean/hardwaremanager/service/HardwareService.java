package com.matean.hardwaremanager.service;

import com.matean.hardwaremanager.exception.ItemAlreadyExistsException;
import com.matean.hardwaremanager.model.Hardware;
import com.matean.hardwaremanager.repository.HardwareRepository;
import com.matean.hardwaremanager.service.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HardwareService {

    @Autowired
    private HardwareRepository hardwareRepository;

    @Autowired
    private S3Service s3Service;

    public List<Hardware> getAllHardware() {
        return hardwareRepository.findAllByIsDeletedFalse();
    }

    public Hardware getHardwareById(UUID id) {
        return hardwareRepository.findById(id).orElse(null);
    }

    public Hardware saveHardware(Hardware hardware, MultipartFile file) {
        // Check if it's a new entity (ID is null)
        if (hardware.getId() == null) {
            Optional<Hardware> existingHardware = hardwareRepository.findByDescription(hardware.getDescription());
            if (existingHardware.isPresent()) {
                throw new ItemAlreadyExistsException("Hardware with description '" + hardware.getDescription() + "' already exists.");
            }
        }

        if (file != null && !file.isEmpty()) {
            String imageUrl = s3Service.uploadFile(file, "hardware");
            hardware.setImageUrl(imageUrl);
        }

        return hardwareRepository.save(hardware);
    }

    public void deleteHardware(UUID id) {
        hardwareRepository.deleteById(id);
    }
}
