import React from "react";
import "./ForgetPassword.css";
import { Link, NavLink } from "react-router-dom";
function ForgetPassword(props) {
  return (
    <div class="container">
      <div class="login-container">
        <div class="login-header">
          <h1>
            <b>Quên mật khẩu</b>
          </h1>

          <form>
            <div class="form-group">
              <label for="email">Nhập email của bạn</label>
              <input
                type="email"
                class="form-control"
                id="email"
                placeholder="Nhập email"
                required
              />
              <button type="button" class="btn btn-primary btn-block">
                Xác thực
              </button>

              <NavLink to={"/homepage"}>
                <button type="button" class="btn123 btn-danger">
                  Đóng
                </button>
              </NavLink>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default ForgetPassword;
