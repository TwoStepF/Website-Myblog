package com.thisWebSite.myWebsite.Exeption;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
