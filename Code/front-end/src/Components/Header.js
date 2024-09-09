import React from "react";

function Header(props) {
  return (
    <div class="jumbotron text-center">
      <div class="container d-flex justify-content-between align-items-center">
        <a href="https://sweethome.vn" class="navbar-brand fs-3 fw-bold">
          <h1>
            <b>Sweet Home</b>
          </h1>
        </a>
        <div class="d-flex">
          <button class="btn btn-primary me-2">Hợp tác với chúng tôi</button>
          <button class="btn btn-primary me-2">Đăng ký</button>
          <button class="btn btn-primary">Đăng nhập</button>
        </div>
      </div>
    </div>
  );
}

export default Header;
