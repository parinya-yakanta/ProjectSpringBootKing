package com.parinya.backend.exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super("user. " + code);
    }

    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    public static UserException createEmailNull() {
        return new UserException("create.CreateEmail.null");
    }

    public static UserException createPasswordNull() {
        return new UserException("create.Password.null");
    }

    public static UserException createNameNull() {
        return new UserException("create.Name.null");
    }

    public static UserException createEmailDuplicated() {
        return new UserException("create.CreateEmail.Duplicated");
    }
}
