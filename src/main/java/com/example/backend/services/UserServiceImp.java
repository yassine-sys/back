package com.example.backend.services;

import com.example.backend.dao.GroupRepository;
import com.example.backend.dao.UserRepository;
import com.example.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository grpRep;

    public UserServiceImp() {
    }

    @Override
    public User addUser(User user) {
        user.setDateCreation(new Date());
        return userRepository.save(user);
    }

    @Override
    public User editUser(User user) {
        User u = findById(user.getuId());
        if(u!=null){
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());
            u.setDateCreation(user.getDateCreation());
            u.setDateModif(user.getDateModif());
            u.setEtat(user.getEtat());
            u.setuLogin(user.getuLogin());
            u.setuMail(user.getuMail());
            u.setuMatricule(user.getuMatricule());
            u.setuDepart(user.getuDepart());
            u.setIdCreateur(user.getIdCreateur());
            u.setNomUtilisateur(user.getNomUtilisateur());
            u.setUser_group(user.getUser_group());

        }
        return userRepository.save(u);
    }

    @Override
    public List<User> getListUser() {
        return userRepository.findAll();
    }


    @Override
    public void deleteUser(Long id) {
        User user = findById(id);
        if (user != null)
        {
            userRepository.delete(user);
        }
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> findUser = userRepository.findByUsername(username);
        return findUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.print(username);
        Optional<User> optionalUsers = userRepository.findByUsername(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        //return optionalUsers
        //      .map(CustomUserDetails::new).get();
        return new org.springframework.security.core.userdetails.User(optionalUsers.get().getUsername(), optionalUsers.get().getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("GROUP_ADMIN"));
    }

}
