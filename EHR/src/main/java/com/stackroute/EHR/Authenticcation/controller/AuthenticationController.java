package com.stackroute.EHR.Authenticcation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.EHR.Authenticcation.jwtConfig.SecurityTokenGenerator;
import com.stackroute.EHR.model.Authentication;


@RestController
@CrossOrigin("*")
public class AuthenticationController {
    //Autowire patient repository
    @Autowired
    private AuthenticationRepository authenticationRepository;
    //Autowire security token generator
    @Autowired
    private SecurityTokenGenerator tokenGenerator;
    
   

    //create api to login patient
    @PostMapping("/login")
    public Map<String,String> loginPatient(@RequestBody Authentication authentication) {
        Map<String,String> map=new HashMap<>();
        Authentication existinguser = authenticationRepository.findById(authentication.getEmail()).get();
        if(existinguser!=null){
          
            if(authentication.getPassword().equals(existinguser.getPassword()){
                return tokenGenerator.generateToken(existinguser);
            }
            else{
                map.put("message","Invalid Password");
                return map;
            }
        }
        else{
            map.put("message","Invalid Email");
            return map;
        }
    }

}
