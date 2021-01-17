import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";

class Landing extends Component {
  componentDidMount() {
    if (this.props.security.validToken) {
      this.props.history.push("/dashboard");
    }
  }

  render() {
    return (
      <div className="landing">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-7 text-center">
                <h1 className="BigHead1">Business</h1>
                <h1 className="BigHead2">Management</h1>
                <h1 className="BigHead3">System</h1>
              </div>

              <div className="col-4 text-center">
                <div className="LoginButton">
                  <Link to="/login" className="btn btn-dark btn-lg btn-block">
                    Log in
                  </Link>
                </div>
                <div className="SigninButton">
                  <Link
                    to="/register"
                    className="btn btn-dark btn-lg btn-block"
                  >
                    Sign Up
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Landing.propTypes = {
  security: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  security: state.security,
});

export default connect(mapStateToProps)(Landing);
