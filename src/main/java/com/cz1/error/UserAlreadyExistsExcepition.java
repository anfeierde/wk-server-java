package com.cz1.error;

/**
 * Created by wkchen on 2017/4/14.
 */
public class UserAlreadyExistsExcepition extends RuntimeException{
    private static final long serialVersionUID = -5360231047827764253L;


    public UserAlreadyExistsExcepition(String message) {
        super(message);
    }
}
