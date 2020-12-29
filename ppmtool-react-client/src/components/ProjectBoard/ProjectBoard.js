import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import Backlog from "./Backlog";

class ProjectBoard extends Component {
  render() {
    const { id } = this.props.match.params;
    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        <Backlog></Backlog>
      </div>
    );
  }
}

export default ProjectBoard;
