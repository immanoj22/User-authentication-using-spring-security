package com.manoj.Securty_authentication.Service;

import com.manoj.Securty_authentication.Model.Users;
import com.manoj.Securty_authentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<Users> getallusers() {
        return repository.findAll();
    }

    @Autowired
    AuthenticationManager authmanager;

    @Autowired
    JWTfilterService jwtservice;

    public String verify(Users user){
        Authentication authentication=authmanager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        System.out.println(user.toString());
        if(authentication.isAuthenticated()){
            return jwtservice.genratetoken(user.getUsername());
        }
        return "failed";
    }

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);

    public Users adduser(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
        return user;
    }

}
