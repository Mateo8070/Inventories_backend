package com.matean.hardwaremanager.repository;

import com.matean.hardwaremanager.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
}
