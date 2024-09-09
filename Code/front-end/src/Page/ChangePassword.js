import React from "react";
import "./ChangePassword.css";
function ChangePassword(props) {
  return (
    <div class="container">
      <div class="login-container">
      <div class="login-header">
        <h1>
          <b>Đổi mật khẩu</b>
        </h1>

        <form>
          <div class="form-group">
            <label for="password">Nhập mật khẩu</label>
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
            <div class="input-group">
              <label for="password">Xác nhận mật khẩu</label>
              <input
                type="password"
                class="form-control"
                id="password"
                placeholder="Nhập lại mật khẩu"
                required
              />
              <span class="input-group-addon">
                <i class="glyphicon glyphicon-eye-open"></i>
              </span>
            </div>
            <button type="button" class="btn btn-primary btn-block">
              Xác nhận
            </button>
            <button type="button" class="btn123 btn-danger">
              Đóng
            </button>
          </div>
          
        </form>
      </div>
      </div>
    </div>
  );
}

export default ChangePassword;
