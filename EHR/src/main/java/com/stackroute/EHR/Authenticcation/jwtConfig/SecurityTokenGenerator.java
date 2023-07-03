package com.stackroute.EHR.Authenticcation.jwtConfig;

import java.util.Map;



public interface SecurityTokenGenerator {
    Map<String,String> generateToken(Authentication patient);
}
