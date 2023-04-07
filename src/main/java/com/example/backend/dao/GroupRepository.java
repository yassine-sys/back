package com.example.backend.dao;

import com.example.backend.entities.Group;
import com.example.backend.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long> {

   // Optional<Group> findGroupByModule(Long Id);
}
