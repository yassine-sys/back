package com.example.backend.services;

import com.example.backend.dao.FunctionRepository;
import com.example.backend.dao.GroupRepository;
import com.example.backend.dao.RepRapportRepository;
import com.example.backend.dao.SubModuleRepository;
import com.example.backend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("FunctionService")

public class FunctionServiceImp implements FunctionService{
    @Autowired
    FunctionRepository funcRepo;
    @Autowired
    SubModuleRepository subRepo;

    @Autowired
    private RepRapportRepository repRapportRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    GroupRepository grpRepo;
    @PersistenceContext
    private EntityManager em;

    @Override
    public Function addFunction(Function f) {
        return funcRepo.save(f);
    }

    @Override
    public List<Function> getListFunction() {
       return funcRepo.findAll();
    }

    @Override
    public void deleteFunc(Long id) {
        Optional<Function> optionalFunction = funcRepo.findById(id);
        if (optionalFunction.isPresent()) {
            Function function = optionalFunction.get();
            Optional<Group> grp = groupService.FindGroupByFunc(function.getId());
            if(grp.isPresent()){
                Group group = grp.get();
                List<Function> functions = group.getListe_function();
                functions.removeIf(f -> f.getId().equals(function.getId()));
                group.setListe_function(functions);
                grpRepo.save(group);
            }
            funcRepo.delete(function);
        }
    }

    @Override
    public Function updateFunction(long id, Function function) throws ResourceNotFoundException {
        Function existingFunction = funcRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("function not found for this id :: " + id));
        existingFunction.setFunctionName(function.getFunctionName());
        existingFunction.setGroup(function.getGroup());

        // Retrieve the sub-module from the database using the ID of the updated function's sub-module
        SubModule subModule = subRepo.findById(existingFunction.getSubModule().getId())
                .orElseThrow(() -> new ResourceNotFoundException("sub-module not found for this id :: " + function.getSubModule().getId()));
        existingFunction.setSubModule(subModule);

        final Function updatedFunction = funcRepo.saveAndFlush(existingFunction);

        return updatedFunction;
    }

    @Override
    public void assignRepRapportToFunction(Long functionId, Long repRapportId) {
        Function function = funcRepo.findById(functionId).orElseThrow(() -> new EntityNotFoundException("Function not found"));
        RepRapport repRapport = repRapportRepository.findById(repRapportId).orElseThrow(() -> new EntityNotFoundException("RepRapport not found"));

        function.getListreprapport().add(repRapport);
        funcRepo.save(function);
    }

    @Override
    public List<RepRapport> getListRapport(){

        return repRapportRepository.findAll();
    }

    public List<RepRapport> getRepRapportsByFunctionId(Long functionId) {
        Function function = funcRepo.findById(functionId).orElseThrow(() -> new EntityNotFoundException("Function not found"));
        return function.getListreprapport();
    }

    public void removeRepRapportFromFunction(Long functionId, Long repRapportId) {
        Function function = funcRepo.findById(functionId).orElseThrow(() -> new EntityNotFoundException("Function not found"));
        RepRapport repRapport = repRapportRepository.findById(repRapportId).orElseThrow(() -> new EntityNotFoundException("RepRapport not found"));

        function.getListreprapport().remove(repRapport);
        funcRepo.save(function);
    }

}
