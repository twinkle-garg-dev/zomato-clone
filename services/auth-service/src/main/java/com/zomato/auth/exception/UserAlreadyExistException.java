package com.zomato.auth.exception;

import org.springframework.stereotype.Component;

//@Component
public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String exception){
        super(exception);
    }

}
