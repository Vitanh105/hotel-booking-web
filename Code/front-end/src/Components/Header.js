import React from "react";
import { Link, NavLink } from "react-router-dom";
function Header(props) {
  return (
    <div className="row">
      <nav
        className="navbar navbar-inverse"
        style={{ backgroundColor: "transparent", color: "blue" }}
      >
        <div class="container-fluid">
          <div class="navbar-header">
            <NavLink>
              <h1 >
                <b>Sweet Home</b>
              </h1>
            </NavLink>
          </div>
          <ul class="nav navbar-nav navbar-right">
            <li>
              <NavLink to={"/cooperation"}>
                <button class="btn btn-primary me-2" >
                  Hợp tác với chúng tôi
                </button>
              </NavLink>
            </li>
            <li>
              <NavLink
                to={"/signup"}
                style={{ color: "blue" }}
              >
                <span className="glyphicon glyphicon-user"></span> Sign Up
              </NavLink>
            </li>
            <li>
              <NavLink
                to={"/login"}
                style={{ color: "blue"}}
              >
                <span className="glyphicon glyphicon-log-in"></span> Login
              </NavLink>
            </li>
          </ul>
        </div>
      </nav>
    </div>
  );
}

export default Header;
