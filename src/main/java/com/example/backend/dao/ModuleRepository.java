package com.example.backend.dao;

import com.example.backend.entities.Group;
import com.example.backend.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module,Long> {
   // List<Module> findModulesByGroupId(Long gId);


}
