package io.alexspringboot.ppmtool.web;

import io.alexspringboot.ppmtool.domain.User;
import io.alexspringboot.ppmtool.payload.JWTLoginSuccessResponse;
import io.alexspringboot.ppmtool.payload.LoginRequest;
import io.alexspringboot.ppmtool.security.JwtTokenProvider;
import io.alexspringboot.ppmtool.services.MapValidationErrorService;
import io.alexspringboot.ppmtool.services.UserService;
import io.alexspringboot.ppmtool.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static io.alexspringboot.ppmtool.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        /*
        UsernamePasswordAuthenticationToken: when a user is trying to log in,
            the username and password are encapsulated into UsernamePasswordAuthenticationToken.
            It's an implementClass of Authentication.

        AuthenticationManager.authenticate(authentication): AuthenticationManager takes UsernamePasswordAuthenticationToken
            and try to authenticate the corresponding user.
            If this process succeeds, it returns an authentication instance
            that contains all relevant information on the current user.
            The JwtTokenProvider will take this information to generate a token
         */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        /*
        SecurityContextHolder.getContext().setAuthentication: it save the authentication information in the system
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        // Validate password length and match
        userValidator.validate(user, result);


        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
