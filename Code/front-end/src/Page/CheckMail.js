import React from "react";
import ImageGmail from "../Asset/ImageGmail/ImageGmail.jpg";

function CheckMail(props) {
  return (
    <div class="container">
      <div class="signin-container">
        <form>
          <div class="signin-header">
            <img
              src="https://cdn.pixabay.com/photo/2019/10/19/17/24/gmail-4561841_960_720.png"
              alt="Ảnh minh họa"
              width="200"
              height="150"
            />
            <h1>
              <b>Kiểm tra email của bạn</b>
            </h1>
            <div class="form-group">
              <label>
                Chúng tôi vừa gửi cho bạn email hướng dẫn và liên kết đặt lại
                mật khẩu tới email của bạn. Có thể mất vài phút để đến nơi.
              </label>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default CheckMail;
