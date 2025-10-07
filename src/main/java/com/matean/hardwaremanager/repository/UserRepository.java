package com.matean.hardwaremanager.repository;

import com.matean.hardwaremanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
