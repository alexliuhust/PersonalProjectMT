import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import Backlog from "./Backlog";
import { getBacklog } from "../../actions/backlogActions";

class ProjectBoard extends Component {
  constructor() {
    super();
    this.state = {
      errors: {},
    };
  }

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBacklog(id);
  }
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  render() {
    const { id } = this.props.match.params;
    const { projectTasks } = this.props.backlog;
    const { errors } = this.props;

    let BoardContent;
    let HeaderWithNoErrors;

    const headerWithNoErrorsAlgorithm = () => {
      return (
        <div>
          <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
            <i className="fas fa-plus-circle"> Create Task</i>
          </Link>
          <br />
          <hr />
        </div>
      );
    };

    HeaderWithNoErrors = headerWithNoErrorsAlgorithm();

    const boardAlgorithm = (errors, projectTasks) => {
      if (errors.projectNotFound) {
        return (
          <h1 className="bg-danger text-light text-center">
            {errors.projectNotFound}
          </h1>
        );
      } else if (errors.projectIdentifier) {
        return (
          <h1 className="bg-danger text-light text-center">
            {errors.projectIdentifier}
          </h1>
        );
      } else {
        if (projectTasks.length === 0) {
          return (
            <div>
              {HeaderWithNoErrors}
              <h1 className="bg-info text-light text-center">
                No Tasks on this board Yet
              </h1>
            </div>
          );
        } else {
          return (
            <div>
              {HeaderWithNoErrors}
              <Backlog projectTasks_prop={projectTasks} />
            </div>
          );
        }
      }
    };

    BoardContent = boardAlgorithm(errors, projectTasks);

    return <div className="container">{BoardContent}</div>;
  }
}

getBacklog.propTypes = {
  backlog: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  backlog: state.backlog,
  errors: state.errors,
});

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
