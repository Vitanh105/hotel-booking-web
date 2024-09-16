import React from "react";
import "./Footer.css";
import { Link, NavLink } from "react-router-dom";

function Footer(props) {
  return (
    <div class="footer">
      <nav
        class="navbar navbar-inverse"
        style={{ backgroundColor: "transparent", color: "blue" }}
      >
        <div class="container-fluid">
         

          <ul class="nav navbar-nav">
          <li>
            <NavLink  style={{ color: "blue" }}>Copyright © 2024 Sweet Home</NavLink>
          </li>
            <li>
              <NavLink to={"/privacy"} style={{ color: "blue" }}>
                Quyền riêng tư
              </NavLink>
            </li>
            <li>
              <NavLink to={"/terms"} style={{ color: "blue" }}>
                Điều khoản
              </NavLink>
            </li>
            <li>
              <NavLink to={"/contact"} style={{ color: "blue" }}>
                Liên hệ
              </NavLink>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li>
              <NavLink
                to={"https://www.facebook.com/"}
                style={{ color: "black" }}
              >
                <i class="fa-brands fa-facebook"></i>
              </NavLink>
            </li>
            <li>
              <NavLink
                to={"https://www.instagram.com/"}
                style={{ color: "black" }}
              >
                <i class="fa-brands fa-instagram"></i>
              </NavLink>
            </li>
            <li>
              <NavLink to={"https://x.com"} style={{ color: "black" }}>
                <i class="fa-brands fa-square-x-twitter"></i>
              </NavLink>
            </li>
            <li>
              <NavLink
                to={"https://www.youtube.com/"}
                style={{ color: "black" }}
              >
                <i class="fa-brands fa-youtube"></i>
              </NavLink>
            </li>
          </ul>
        </div>
      </nav>
    </div>
  );
}

export default Footer;
