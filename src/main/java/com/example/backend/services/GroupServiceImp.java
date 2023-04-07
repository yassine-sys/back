package com.example.backend.services;

import com.example.backend.dao.FunctionRepository;
import com.example.backend.dao.GroupRepository;
import com.example.backend.entities.Function;
import com.example.backend.entities.Group;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "GroupService")
public class GroupServiceImp implements GroupService{

    GroupRepository groupRepository;
    FunctionRepository functionRepository;
    public GroupServiceImp(GroupRepository groupRepository, FunctionRepository functionRepository) {
        this.groupRepository = groupRepository;
        this.functionRepository = functionRepository;
    }
    @Override
    public Group addGroup(Group group) {
        group.setDateCreation(new Date());
        group.setModule_groups(group.getModule_groups());
        List<Function> functions = new ArrayList<>();
        for (Function function : group.getListe_function()) {
            if (function.getId() != null) {
                functions.add(functionRepository.getOne(function.getId()));
            } else {
                functions.add(functionRepository.save(function));
            }
        }
        group.setListe_function(functions);
        return groupRepository.save(group);
    }

    @Override
    public Group editGroup(Group group,Long id) {
        Group g=this.findById(id);
        System.out.println(g);
        if(g.getgId()==id){
            g.setgName(group.getgName());
            g.setgDescription(group.getgDescription());
            g.setDateModif(group.getDateModif());
            g.setEtat(group.getEtat());
            g.setModule_groups(group.getModule_groups());
            return groupRepository.save(g);
        }
        return null;
    }

    @Override
    public List<Group> getListGroup() {
        return groupRepository.findAll(Sort.by(Sort.Direction.DESC,"dateCreation"));
    }

    @Override
    public void deleteGroup(Long gId) {
        Group group = groupRepository.getOne(gId);
        if (group != null)
        {
            groupRepository.delete(group);
        }

    }

    @Override
    public Group findById(Long gId) {
        return groupRepository.findById(gId).get();
    }

    @Override
    public List<Group> findGroupByModule(Long Id){
        return  groupRepository.findAll().stream().filter
                (x->x.getModule_groups().stream().anyMatch(t->t.getId()==Id)).collect(Collectors.toList());
    }

    @Override
    public Optional<Group> FindGroupByFunc(Long gId){
        return  groupRepository.findAll().stream().filter(
                x->x.getListe_function().stream().anyMatch(t->t.getId()==gId)).findAny();
    }


}
