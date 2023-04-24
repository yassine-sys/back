package com.example.backend.Controllers;

import com.example.backend.entities.RepRapport;
import com.example.backend.entities.User;
import com.example.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(method= RequestMethod.GET)
    public List<User> getList() {
        return userService.getListUser();
    }

    @RequestMapping(value="/add",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUtilisateur(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setAdmin(user.isAdmin());
        userService.addUser(user);
    }
    @RequestMapping(value="/edit",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editUtilisateur(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setAdmin(user.isAdmin());
        userService.editUser(user);
    }

    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
    @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
    public User findbyId(@PathVariable Long id) {
        System.out.println("find by id d5al lel fonction c bon ");
        return userService.findById(id);
    }
    @RequestMapping(value="/username/{username}",method=RequestMethod.GET)
    public Optional<User> findbyEmail(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @RequestMapping(value="/assign/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void assignFunction(@PathVariable Long id, @RequestBody List<RepRapport> rap) {
        userService.assignFunc(id, rap);
    }

    @RequestMapping(value="/detach/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void detachRep(@PathVariable Long id, @RequestBody RepRapport rep) {
        userService.detachRep(id,rep);
    }


}

