function Footer() {
  return (
    <div className=" bg-black flex justify-center text-gray-400 ">
      <div className="footer">
        <div className="flex flex-col gap-4 first-child">
          <a href="/" className="">
            <img className=" w-36 h-24 " src="../logo.png" alt="" />
          </a>
          <div className=" ">
            <p className="">Công ty TNHH du lịch và dịch vụ ViVu</p>
            <p className="">
              125 P. Phùng Hưng, Cửa Đông, Hoàn Kiếm, Hà Nội, Việt Nam
            </p>
          </div>
        </div>
        <div className=" flex flex-grow gap-8 justify-between ">
          <div className="flex flex-col gap-4">
            <span className="text-white font-bold">Giới thiệu</span>
            <div className="flex gap-2 flex-col">
              <a href="/">Về chúng tôi</a>
              <a href="/">Điều khoản và điều kiện</a>
              <a href="/">Hướng dẫn sử dụng</a>
              <a href="/">Hướng dẫn thanh toán</a>
              <a href="/">Liên hệ</a>
              <a href="/">HotLine:09999999999 </a>
              <a href="/">Email:vivu@gmail.com</a>
            </div>
          </div>
          <div className="flex flex-col gap-4">
            <span className="text-white font-bold">Sản phẩm</span>
            <div className="flex gap-2 flex-col">
              <a href="/">Đặt khách sạn</a>
              <a href="/">Quản lý khách hàng</a>
            </div>
          </div>
          <div className="flex flex-col gap-4">
            <span className="text-white font-bold">Khách sạn</span>
            <div className="flex gap-2 flex-col">
              <a href="/thingsToDo">Blog</a>
              <a href="/">Điều khoản điều khiện chung</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Footer;
