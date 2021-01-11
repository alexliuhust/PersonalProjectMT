import axios from "axios";

const setJETToken = (token) => {
  if (token) {
    // look at the headers in postman,
    // we just added a header called "Authorization" when coding the server end
    axios.defaults.headers.common["Authorization"] = token;
  } else {
    delete axios.defaults.headers.common["Authorization"];
  }
};

export default setJETToken;
