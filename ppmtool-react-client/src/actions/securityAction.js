import axios from "axios";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async (dispatch) => {
  try {
    await axios.post("/api/users/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const login = (LoginRequest) => async (dispatch) => {
  try {
    // post -> LoginRequest, the Java object contains username and password
    const res = await axios.post();

    // extract token from res.data
    const { token } = res.data;

    // store the token locally
    localStorage.setItem("jwtToken", token);

    // set token in header (look at the headers in postman,
    // we just added a header called "Authorization" when coding the server end)
    setJWTToken(token);

    // decode the token
    const decoded = jwt_decode(token);

    // dispatch to the securityReducer
    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
