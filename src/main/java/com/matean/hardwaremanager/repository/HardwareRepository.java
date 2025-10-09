package com.matean.hardwaremanager.repository;

import com.matean.hardwaremanager.model.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HardwareRepository extends JpaRepository<Hardware, UUID> {
    Optional<Hardware> findByDescription(String description);
    List<Hardware> findAllByIsDeletedFalse();
}
