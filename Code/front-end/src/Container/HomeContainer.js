import React from "react";
import Header from "../Components/Header";
import Footer from "../Components/Footer";
import HeaderClickUser from "../Components/HeaderClickUser";


function HomeContainer(props) {
  return (
    <div class="wrapper">
      {/* <Header /> */}
      <HeaderClickUser/>
      <div class="container content">
        <h2>Nội dung trang web</h2>
        <p>
          Đây là nội dung của trang web. Bạn có thể thêm bất cứ nội dung gì ở
          đây.
        </p>
        <Footer />
      </div>
    </div>
  );
}

export default HomeContainer;
