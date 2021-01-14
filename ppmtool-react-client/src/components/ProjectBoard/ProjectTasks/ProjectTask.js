import React, { Component } from "react";
import { Link } from "react-router-dom";
import { deleteProjectTask } from "../../../actions/backlogActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";

class ProjectTask extends Component {
  onDeleteClick(backlog_id, pt_id) {
    this.props.deleteProjectTask(backlog_id, pt_id);
  }

  render() {
    const { projectTask } = this.props;

    // Colorize the priority
    let priorityString, priorityID;
    if (projectTask.priority === 1) {
      priorityString = "HIGH";
      priorityID = "HIGHpriority";
    } else if (projectTask.priority === 2) {
      priorityString = "MEDIUM";
      priorityID = "MEDIUMpriority";
    } else {
      priorityString = "LOW";
      priorityID = "LOWpriority";
    }

    return (
      <div className="card mb-1 bg-light">
        <div
          className="card-header text-primary text-light"
          id={`${priorityID}`}
        >
          {priorityString} Priority
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">
            Summary of {projectTask.projectSequence}
          </h5>
          <p className="card-text text-truncate ">{projectTask.summary}</p>
          <hr></hr>
          <h6 className="text-right DueDate">
            Due Date: {projectTask.dueDate}
          </h6>
          <Link
            to={`/updateTask/${projectTask.projectIdentifier}/${projectTask.projectSequence}`}
            className="btn btn-primary"
          >
            View / Update
          </Link>

          <button
            className="btn btn-danger ml-4"
            onClick={this.onDeleteClick.bind(
              this,
              projectTask.projectIdentifier,
              projectTask.projectSequence
            )}
          >
            Delete
          </button>
        </div>
      </div>
    );
  }
}

ProjectTask.propTypes = {
  deleteProjectTask: PropTypes.func.isRequired,
};

export default connect(null, { deleteProjectTask })(ProjectTask);
