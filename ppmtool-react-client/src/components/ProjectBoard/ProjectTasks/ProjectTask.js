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
      if (projectTask.status === "TO_DO") {
        priorityID = "HIGHpriority1";
      } else if (projectTask.status === "IN_PROGRESS") {
        priorityID = "HIGHpriority2";
      } else {
        priorityID = "HIGHpriority3";
      }
    } else if (projectTask.priority === 2) {
      priorityString = "MEDIUM";
      if (projectTask.status === "TO_DO") {
        priorityID = "MEDIUMpriority1";
      } else if (projectTask.status === "IN_PROGRESS") {
        priorityID = "MEDIUMpriority2";
      } else {
        priorityID = "MEDIUMpriority3";
      }
    } else {
      priorityString = "LOW";
      if (projectTask.status === "TO_DO") {
        priorityID = "LOWpriority1";
      } else if (projectTask.status === "IN_PROGRESS") {
        priorityID = "LOWpriority2";
      } else {
        priorityID = "LOWpriority3";
      }
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
