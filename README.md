# Personal Business Tool —— Tech Explanation

## Project URL
    https://personalbusiness.herokuapp.com


## Overall Architecture
![Alt text](/images/OverallArchitecture.png?raw=true "Overall Architecture")


## React + Redux Architecture — Create a business
![Alt text](/images/React+ReduxCreatebusiness.png?raw=true "React + Redux - Create business")


## Spring Security + JWT — Log In
![Alt text](/images/Authentication.png?raw=true "Spring Security + JWT — Log In")

#### Back end
When a user is trying to log in, the input username and password are encapsulated into UsernamePasswordAuthenticationToken. It's an implementing class of Authentication. 

AuthenticationManager takes UsernamePasswordAuthenticationToken and tries to authenticate the corresponding user.

If this process fails, the AuthenticationEntryPoint will handle this and post the InvalidLoginResponse back to the front end.

If this process succeeds, it returns an authentication instance that contains all relevant information on the current user. Then, the SecurityContextHolder will store that authentication instance for potential future use. 

The JwtTokenProvider then takes this information to generate a token. It will compact user id, username, password, expiration date, etc, into a string, with a prefix —— bearer. A completed JWT is done. The controller will send this JWT back to the front end. 

#### Front end
When the action receives JWT, it will first store it in the local storage. Then, it will set the JWT into the headers, i.e. assign the "Authorization" with this JWT.

After that, the action will decode the JWT back to user information —— like user id, username, password, expiration date, etc —— and update Redux store by it. 

If the action receives InvalidLoginResponse, it will update the errors in the Redux store and render them back to the Login Page.


## Authorization — Update a business
![Alt text](/images/Autorization.png?raw=true "Authorization — Update a business")

