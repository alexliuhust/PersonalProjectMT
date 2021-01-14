package io.alexspringboot.ppmtool.exceptions;

public class UsernameDuplicateResponse {

    private String username;

    public UsernameDuplicateResponse(String message) {
        this.username = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usernameDuplicated) {
        username = usernameDuplicated;
    }
}
