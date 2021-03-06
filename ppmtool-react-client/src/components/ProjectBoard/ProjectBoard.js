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
          <h1 className="text-center">My Tasks of Business {id}</h1>

          <div className="CreateButton">
            <Link to={`/addTask/${id}`} className="btn btn-lg btn-primary">
              <i className="fas fa-plus-circle"> Create Task for {id}</i>
            </Link>
          </div>

          <br />
          <hr />
        </div>
      );
    };

    HeaderWithNoErrors = headerWithNoErrorsAlgorithm();

    const boardAlgorithm = (errors, projectTasks) => {
      if (errors.businessNotFound) {
        return (
          <h1 className="bg-danger text-light text-center">
            {errors.businessNotFound}
          </h1>
        );
      } else if (errors.identifier) {
        return (
          <h1 className="bg-danger text-light text-center">
            {errors.identifier}
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
