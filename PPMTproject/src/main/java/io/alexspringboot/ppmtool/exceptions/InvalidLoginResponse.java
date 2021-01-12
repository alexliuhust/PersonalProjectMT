package io.alexspringboot.ppmtool.exceptions;

/**
 * It tells user the login operation fails.
 * When the authentication fails after a user's login request,
 * the AuthenticationEntryPoint will encapsulate the prompts into this class.
 */
public class InvalidLoginResponse {

    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
