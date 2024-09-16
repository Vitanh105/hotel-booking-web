import React from "react";
import "./HeaderClickUser.css";
import { Link, NavLink } from "react-router-dom";
import { useNavigate } from "react-router-dom";
function HeaderClickUser(props) {
  // let navigate = useNavigate();
  // let handleLogin = () => {
  //   navigate("/Cooperation");
  // };
  return (
    <div class="jumbotron text-center">
      <div class="container d-flex justify-content-between align-items-center">
        <a href="https://sweethome.vn" class="navbar-brand fs-3 fw-bold">
          <h1>
            <b>Sweet Home</b>
          </h1>
        </a>

        <nav className="navbar navbar-inverse">
          <div className="container-fluid">
            <ul className="nav navbar-nav navbar-right">
              <li>
                <NavLink to={"/Cooperation"}>
                  <button class="btn btn-primary me-2" onClick={handleLogin}>
                    Hợp tác với chúng tôi
                  </button>
                </NavLink>
              </li>

              <li>
                <NavLink to={"/login"}>
                  <span className="glyphicon glyphicon-user"></span> Login
                </NavLink>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    </div>
  );
}

export default HeaderClickUser;
