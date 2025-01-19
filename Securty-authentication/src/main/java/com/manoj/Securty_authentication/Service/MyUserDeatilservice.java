package com.manoj.Securty_authentication.Service;

import com.manoj.Securty_authentication.Controller.UserPrincipal;
import com.manoj.Securty_authentication.Model.Users;
import com.manoj.Securty_authentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDeatilservice implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user=repository.findByUsername(email);
        if(user==null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }


}
