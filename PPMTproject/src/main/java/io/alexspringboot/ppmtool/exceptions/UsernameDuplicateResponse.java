package io.alexspringboot.ppmtool.exceptions;

public class UsernameDuplicateResponse {

    private String username;

    public UsernameDuplicateResponse(String usernameDuplicatedMessage) {
        this.username = usernameDuplicatedMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usernameDuplicated) {
        username = usernameDuplicated;
    }
}
