package com.example.backend.services;

import com.example.backend.entities.Group;

import java.util.List;
import com.example.backend.entities.Module;


public interface ModuleService {

    Module addModule(Module module) ;
    Module editModule(Module module,Long id);
    List<Module> getListModule() ;
    void deleteModule(Long Id) ;

    Module findById(Long Id);

    List<Module> findModuleByGroup(Long Id);
}
