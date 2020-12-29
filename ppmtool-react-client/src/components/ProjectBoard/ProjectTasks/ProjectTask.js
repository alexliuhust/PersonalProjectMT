import React, { Component } from "react";

class ProjectTask extends Component {
  render() {
    const { projectTask } = this.props;

    // Colorize the priority
    let priorityString, priorityClass;
    if (projectTask.priority === 1) {
      priorityString = "HIGH";
      priorityClass = "bg-danger text-light";
    } else if (projectTask.priority === 2) {
      priorityString = "MEDIUM";
      priorityClass = "bg-warning text-light";
    } else {
      priorityString = "LOW";
      priorityClass = "bg-info text-light";
    }

    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClass}`}>
          ID: {projectTask.projectSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{projectTask.summary}</h5>
          <p className="card-text text-truncate ">
            {projectTask.acceptanceCriteria}
          </p>
          <a href="/" className="btn btn-primary">
            View / Update
          </a>

          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}

export default ProjectTask;
