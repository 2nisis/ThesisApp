package com.ThesisApp.service.impl;

import com.ThesisApp.model.Professor;
import com.ThesisApp.model.Role;
import com.ThesisApp.model.Student;
import com.ThesisApp.model.User;
import com.ThesisApp.repository.UserRepository;
import com.ThesisApp.service.ProfessorService;
import com.ThesisApp.service.StudentService;
import com.ThesisApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService  {


    private UserRepository userDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.save(user);
        return userDAO.save(user);
    }

    @Override
    public boolean isUserPresent(User user) {
        return userDAO.findByUsername(user.getUsername()) != null;
    }

    @Override
    public User findById(String id) {
        return userDAO.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if (!isUserPresent(user)) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


}



