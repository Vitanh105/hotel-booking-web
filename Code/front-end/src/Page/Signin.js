import React from "react";
import "./Signin.css";

function SignIn(props) {
  return (
    <div class="container">
      <div class="signin-container">
        <div class="signin-header">
          <h1>
            <b>Đăng Ký</b>
          </h1>
        </div>
        <form>
          <div class="form-group">
            <label for="fullName">Họ tên:</label>
            <input
              type="text"
              class="form-control"
              id="fullName"
              name="fullName"
              placeholder="Nhập Họ Tên"
              required
            />
          </div>
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
            <label for="phone">Số điện thoại:</label>
            <input
              type="tel"
              class="form-control"
              id="phone"
              name="phone"
              placeholder="Nhập số điện thoại"
              required
            />
          </div>
          <div class="form-group">
            <label for="password">Mật khẩu:</label>
            <input
              type="password"
              class="form-control"
              id="password"
              name="password"
              placeholder="Nhập mật khẩu"
              required
            />
          </div>
          <div class="form-group">
            <label for="confirmPassword">Xác nhận mật khẩu:</label>
            <input
              type="password"
              class="form-control"
              id="confirmPassword"
              name="confirmPassword"
              placeholder="Nhập lại mật khẩu"
              required
            />
          </div>
          <div class="form-group">
            <label>
              <input type="checkbox" id="terms" name="terms" />
              Bạn đồng ý với điều khoản của chúng tôi
            </label>
            <div id="termsError" class="error-message">
              Bạn chưa đồng ý với điều khoản của chúng tôi
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block">
            Đăng ký
          </button>
        </form>
        <div class="signin-footer">
          <p>
            Bạn đã có tài khoản?{" "}
            <a href="#" class="signin-link">
              Đăng nhập
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

export default SignIn;
