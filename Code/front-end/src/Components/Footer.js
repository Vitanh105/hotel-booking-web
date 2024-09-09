import React from "react";

function Footer(props) {
  return (
    <footer class="footer">
      <div class="container">
        <div class="row">
          <div class="col-md-8">
            <p class="copyright">
              Copyright © 2024 Sweet Home
              <span class="divider">●</span>
              <a href="#">Quyền riêng tư</a>
              <span class="divider">●</span>
              <a href="#">Điều khoản</a>
              <span class="divider">●</span>
              <a href="#">Liên hệ</a>
            </p>
          </div>

          <div class="col-md-4 text-right social-icons">
            <a href="#">
              <i class="fa-brands fa-facebook"></i>
            </a>
            <a href="#">
              <i class="fa-brands fa-instagram"></i>
            </a>
            <a href="#">
              <i class="fa-brands fa-square-x-twitter"></i>
            </a>
            <a href="#">
              <i class="fa-brands fa-youtube"></i>
            </a>
          </div>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
