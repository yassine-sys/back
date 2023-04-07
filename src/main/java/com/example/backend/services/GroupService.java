package com.example.backend.services;

import com.example.backend.entities.Group;

import java.util.List;
import java.util.Optional;


public interface GroupService {

    Group addGroup(Group group) ;
    Group editGroup(Group group,Long id);

    List<Group> getListGroup() ;
    void deleteGroup(Long gId) ;
    Optional<Group> FindGroupByFunc(Long gId) ;

    Group findById(Long gId);

    List<Group> findGroupByModule(Long Id);
}
