# Personal Business Tool —— Tech Explanation

## Project URL
    https://personalbusiness.herokuapp.com


## Overall Architecture
![Alt text](/images/OverallArchitecture.png?raw=true "Overall Architecture")

#### Front end
The front end contains the web pages, where users directly operate. React components communicate with the back end through the `actions`. They send HTTP requests to the back end and receive the responses and use those responses to update the `Redux store`, which manages all the `states`, or to say, the global information that every component authorized will be able to access. 

#### Back end
The back end mainly contains **entities**, **controllers**, **services**, and **repositorie**. 
![Alt text](/images/Serverend.png?raw=true "Server End Architecture")

The **controllers** directly handle the requests from the web. They analyze the request type and give the corresponding response, intercept the input errors, or post back the prompts. They cannot do the data manipulation directly, but have to through the *services*. 

The **services** handle the details of the business logic, calling the methods from the *repository* interfaces to do the CRUD operations, or coping with the errors from the server end or database. The *exceptions* package is who actually deal with the server-end/database exceptions, and  `CustomResponseEntityExceptionHandler`, annotated with `@ControllerAdvice` and `@RestController`, who communicates directly with the front end, supporting other exception handling service.

The **repositories** directly get along with the database. They have a one-to-one correspondence with *entities* and have various operation methods for the entities together with their attributes, like find a business by a given identification, update a task by a given sequence, etc. 

The **Hibernate** framework will automatically map those entity objects to the tables of the relational database, the object's class name as the relation's name, the object's member variables as the table's attributes. 


## React + Redux Architecture — Create a business
![Alt text](/images/React+ReduxCreatebusiness.png?raw=true "React + Redux - Create business")

### Action
When the **submit** button clicked, the React component `CreateForm` calls the corresponding action —— `createBusiness`. The action then will send an HTTP Request to the back end, or to say, call the RESTful API, and hopefully will get an OK-Response. 

If everything goes well, the action will take users back to the `Dashboard` page according to the `history` passed into the action. 

If there are some errors, the action will catch those errors and prepare to dispatch the information to the **Redux Store**. The to-be-dispatched object usually contains two members: the `type`, and the `payload`. The type is a signal or an identifier, whose function will be talked about later. The payload is the actual content that the action wants to dispatch to the Store. 

### React + Redux
When the store receives the dispatched object, it cannot directly cope with it, because what stored inside the Redux Store is **states**. That means it needs a translator to turn the **object** into **states**. Here comes the **Reducer**. It will recognize the `type` of a dispatched object and take the corresponding steps to translate the object into the states and finally the states inside the store can be updated. 

### Life Cycle
At the time the React component `CreateForm` called the action `createBusiness`, this component has already connected to the **Redux Store**. As soon as the states in the store get updated, the component will pick up the states it needs from those updated states, then use `mapStateToProps` to map the received states into its own `props`. After that, the method `componentWillReceiveProps` will be called to recognize whether the new props —— `nextprops` contains the errors. If it is, the method will instantly update the state of the component —— i.e. assign the received errors into the member variable `errors` —— and trigger the re-render. At last, the `CreateForm` will show the user-friendly input-error prompts. 
![Alt text](/images/CreateBusinessErrors.png "Create Business Errors"){ width=50% }


## Spring Security + JWT — Log In
![Alt text](/images/Authentication.png?raw=true "Spring Security + JWT — Log In")

#### Back end
When a user is trying to log in, the input username and password are encapsulated into an implementing class of `Authentication`, called `UsernamePasswordAuthenticationToken`. `AuthenticationManager` takes `UsernamePasswordAuthenticationToken` and tries to authenticate the corresponding user.

If this process fails, the `AuthenticationEntryPoint` will handle this and post the `InvalidLoginResponse` back to the front end.

If this process succeeds, it returns an authentication instance that contains all relevant information on the current user. Then, the `SecurityContextHolder` will store that authentication instance for potential future use. 

The `JwtTokenProvider` then takes this information to generate a token. It will compact user id, username, password, expiration date, etc, into a string, with a prefix —— bearer. A completed JWT is done. The controller will send this JWT back to the front end. 

#### Front end
When the action receives JWT, it will first store it in the `localStorage`. 
![Alt text](/images/Headers.png?raw=true "Headers")

Then, it will set the JWT into the headers, i.e. assign the "Authorization" with this JWT.
![Alt text](/images/localStorage.png?raw=true "Local Storage")

After that, the action will decode the JWT back to user information —— like user id, username, password, expiration date, etc —— and update Redux store by it. At last, the page will be re-directed to the `dashboard`.

If the action receives `InvalidLoginResponse`, it will update the errors in the Redux store and render them back to the Login Page.






## Authorization — Update a business
![Alt text](/images/Autorization.png?raw=true "Authorization — Update a business")

