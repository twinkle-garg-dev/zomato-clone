package com.zomato.auth.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class AppConfig {


//    public  static void main(String args[]){
//        Key key = Jwts.SIG.HS256.key().build();
//        String secret = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println(secret);
//    }
}
