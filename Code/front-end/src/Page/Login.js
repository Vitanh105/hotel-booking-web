import React from "react";
import "./Login.css";

function Login(props) {
  return (
    <div class="container">
      <div class="login-container">
        <div class="login-header">
        <h1>
            <b>Đăng Nhập</b>
          </h1>
        </div>

        <form>
          <div class="form-group">
            <label for="email">Email</label>
            <input
              type="email"
              class="form-control"
              id="email"
              placeholder="Nhập email"
              required
            />
          </div>
          <div class="form-group">
            <label for="password">Mật khẩu</label>
            <div class="input-group">
              <input
                type="password"
                class="form-control"
                id="password"
                placeholder="Nhập mật khẩu"
                required
              />
              <span class="input-group-addon">
                <i class="glyphicon glyphicon-eye-open"></i>
              </span>
            </div>
          </div>

          <div class="form-group">
            <div class="checkbox remember-me">
              <label>
                <input type="checkbox" /> Duy trì đăng nhập
              </label>
            </div>
            <a href="#" class="forgot-password">
              Quên mật khẩu?
            </a>
          </div>

          <button type="submit" class="btn btn-primary btn-block">
            Đăng nhập
          </button>
        </form>

        <div class="login-footer">
          <p>
            Bạn chưa có tài khoản?{" "}
            <a href="#" class="login-link">
              Đăng ký
            </a>
          </p>
        </div>

        <div class="separator">
          <p>hoặc sử dụng một trong các lựa chọn này</p>
        </div>

        <div class="other-options">
          <button class="btn btn-default">
            <i class="fa-brands fa-facebook"></i>
          </button>
          <button class="btn btn-default">
            <i class="fa-brands fa-google"></i>
          </button>
        </div>
      </div>
    </div>
  );
}

export default Login;
