import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import Backlog from "./Backlog";
import { getBacklog } from "../../actions/backlogActions";

class ProjectBoard extends Component {
  componentDidMount() {
    this.props.getBacklog(this.props.match.params.id);
  }

  render() {
    const { id } = this.props.match.params;
    const { projectTasks } = this.props.backlog;
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

getBacklog.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  backlog: state.backlog,
});

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
