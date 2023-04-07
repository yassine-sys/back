package com.example.backend.Controllers;

import com.example.backend.entities.Group;
import com.example.backend.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;



    @RequestMapping(value="/add",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addGroup(@RequestBody Group group) {
        if(group==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            groupService.addGroup(group);
            return new ResponseEntity<>(group,HttpStatus.OK);
        }
    }

    @RequestMapping(value="/edit/{id}",method= RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> editGroup(@RequestBody Group group,@PathVariable Long id) {
        if(group==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            groupService.editGroup(group,id);
            return new ResponseEntity<>(group,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/list")
    public List<Group> getGroup(){
        return groupService.getListGroup();
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        groupService.deleteGroup(id);

    }

    @RequestMapping(value = "/{gId}",method=RequestMethod.GET)
    public Group findById(@PathVariable Long gId){
        return groupService.findById(gId);
    }


    @RequestMapping(value = "/module/{Id}",method=RequestMethod.GET)
    public List<Group> findGroupByModule(@PathVariable Long Id){
        return groupService.findGroupByModule(Id);
    }










}
