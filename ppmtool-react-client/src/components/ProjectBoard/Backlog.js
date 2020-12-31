import React, { Component } from "react";
import ProjectTask from "./ProjectTasks/ProjectTask";

class Backlog extends Component {
  render() {
    const { projectTasks_prop } = this.props;
    const tasks = projectTasks_prop.map((projectTask) => (
      <ProjectTask key={projectTask.id} projectTask={projectTask} />
    ));

    // Organize Project Tasks by status
    let todoTasks = [],
      inprogressTasks = [],
      doneTasks = [];
    for (let i = 0; i < tasks.length; i++) {
      if (tasks[i].props.projectTask.status === "TO_DO") {
        todoTasks.push(tasks[i]);
      } else if (tasks[i].props.projectTask.status === "IN_PROGRESS") {
        inprogressTasks.push(tasks[i]);
      } else {
        doneTasks.push(tasks[i]);
      }
    }

    // Sort Project Tasks by priority
    // function compare(task1, task2) {
    //   if (task1.priority > task2.priority) return 1;
    //   if (task1.priority === task2.priority) return 0;
    //   return -1;
    // }
    // todoTasks.sort(compare);
    // inprogressTasks.sort(compare);
    // doneTasks.sort(compare);

    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {todoTasks}
          </div>

          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inprogressTasks}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {doneTasks}
          </div>
        </div>
      </div>
    );
  }
}

export default Backlog;
