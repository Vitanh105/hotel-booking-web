import React from "react";
import "./HeaderClickUser.css";
function HeaderClickUser(props) {
  return (
    // <div class="jumbotron text-center">
    <div class="jumbotron text-center">
      <div class="container d-flex justify-content-between align-items-center">
        <a href="https://sweethome.vn" class="navbar-brand fs-3 fw-bold">
          <h1>
            <b>Sweet Home</b>
          </h1>
        </a>
        {/* <div class="d-flex"> */}
        <span>
          <button class="btn btn-primary me-2">Hợp tác với chúng tôi</button>
          {/* <div class="m-4"> */}
          <span>
          <i class="fa-solid  fa-user"></i>
          </span>
          <span>
            <button class="btn btn-primary">
              <i class="fa-solid fa-list-ul"></i>
            </button>
          </span>
          {/* </div> */}
        </span>
        {/* </div> */}
      </div>
      //{" "}
    </div>
  );
}

export default HeaderClickUser;
