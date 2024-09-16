import React from "react";
import Header from "../Components/Header";
import Footer from "../Components/Footer";


function HomeContainer(props) {
  return (
    <div class="wrapper">
      <Header />
      <div class="container content">
        <div>
        <h2>Nội dung trang web</h2>
        <p>
          Đây là nội dung của trang web. Bạn có thể thêm bất cứ nội dung gì ở
          đây.
        </p>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
      </div>
      </div>
      <Footer />
    </div>
  );
}

export default HomeContainer;
