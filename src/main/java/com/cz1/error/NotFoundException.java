package com.cz1.error;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by wkchen on 2017/4/14.
 */
public class NotFoundException extends AuthenticationException {

    private static final long serialVersionUID = -8955749489338192620L;

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
