import React from "react";
import { Link } from "react-router-dom";

const CreateProjectButton = () => {
  return (
    <React.Fragment>
      <div className="CreateButton">
        <Link to="/addBusiness" className="btn btn-lg btn-dark">
          <i className="fas fa-plus-circle"> Create a Business</i>
        </Link>
      </div>
    </React.Fragment>
  );
};
export default CreateProjectButton;
