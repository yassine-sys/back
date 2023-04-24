package com.example.backend.services;

import com.example.backend.dao.GroupRepository;
import com.example.backend.dao.UserRepository;
import com.example.backend.entities.RepRapport;
import com.example.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.*;

@Service(value = "userService")
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository grpRep;

    @PersistenceContext
    private EntityManager em;

    public UserServiceImp() {
    }

    @Override
    public Object customResponse(Long id){

        String sql = "SELECT u_id, nom_utilisateur, u_pwd, u_login, u_mail FROM management.user WHERE u_id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", id);
        List<Object[]> resultList = query.getResultList();
        Object[] row = resultList.get(0);

        Map<String, Object> result = new HashMap<>();
        result.put("u_id", row[0]);
        result.put("nom_utilisateur", row[1]);
        result.put("u_pwd", row[2]);
        result.put("u_login", row[3]);
        result.put("u_mail", row[4]);
        return result;

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

    @Override
    public void assignFunc(Long id, List<RepRapport> rap) {
        User existingUser = findById(id);
        List<RepRapport> rapports = existingUser.getListreprapport();
        if (rap.isEmpty()) {
            System.err.println("Error: no rapports to add");
            return;
        }
        boolean found = false;
        for (RepRapport r : rap) {
            if (!rapports.contains(r)) {
                rapports.add(r);
                found = true;
            }
        }
        if (found) {
            existingUser.setListreprapport(rapports);
            userRepository.save(existingUser);
        }
    }

    @Override
    public void detachRep(Long id,RepRapport rep){
        User existingUser = findById(id);
        List<RepRapport> rapports = existingUser.getListreprapport();
        if (rep==null) {
            System.err.println("Error: no rapports to add");
            return;
        }
        if(rapports.contains(rep)){
            rapports.remove(rep);
            existingUser.setListreprapport(rapports);
            userRepository.save(existingUser);
        }
    }


}
