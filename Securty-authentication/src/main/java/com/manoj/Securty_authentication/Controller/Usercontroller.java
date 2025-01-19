package com.manoj.Securty_authentication.Controller;

import com.manoj.Securty_authentication.Model.Users;
import com.manoj.Securty_authentication.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.List;

@RestController
@CrossOrigin
public class Usercontroller {

    @Autowired
    UserService service;

    @GetMapping("/users")
    public List<Users> getuser(){
        return service.getallusers();
    }

    @PostMapping("/")
    public String dologin(@RequestBody Users user){
        String token=service.verify(user);
        return token;

    }

    @PostMapping("/signup")
    public Users signup(@RequestBody Users user){
        System.out.println(user);
        return service.adduser(user);
    }

    @GetMapping("/home")
    public String geteuser(){
        return "Welcome you are loged in !";
    }


}
