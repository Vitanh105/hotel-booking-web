import React, { useRef, useState } from "react";
import "./HomePage.css";

const cities = [
  { name: "Hồ Chí Minh", number: "127 lưu trú", image: "1" },
  { name: "Hà Nội", number: "111 lưu trú", image: "2" },
  { name: "Hạ Long", number: "99 lưu trú", image: "3" },
  { name: "Đà Lạt", number: "73 lưu trú", image: "4" },
  { name: "Hội An", number: "62 lưu trú", image: "5" },
  { name: "Phan Thiết", number: "78 lưu trú", image: "6" },
  { name: "Vũng Tàu", number: "92 lưu trú", image: "7" },
];

const hotels = [
  {
    name: "Khách sạn 1",
    location: "Địa điểm 1",
    image: "1.png",
    price: "###VND",
  },
  {
    name: "Khách sạn 2",
    location: "Địa điểm 2",
    image: "2.png",
    price: "###VND",
  },
  {
    name: "Khách sạn 3",
    location: "Địa điểm 3",
    image: "3.png",
    price: "###VND",
  },
  {
    name: "Khách sạn 4",
    location: "Địa điểm 4",
    image: "4.png",
    price: "###VND",
  },
  {
    name: "Khách sạn 5",
    location: "Địa điểm 5",
    image: "5.png",
    price: "###VND",
  },
  {
    name: "Khách sạn 6",
    location: "Địa điểm 6",
    image: "6.png",
    price: "###VND",
  },
  {
    name: "Khách sạn 7",
    location: "Địa điểm 7",
    image: "7.png",
    price: "###VND",
  },
  {
    name: "Khách sạn 8",
    location: "Địa điểm 8",
    image: "8.png",
    price: "###VND",
  },
];

const FeaturedHotels = () => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const cityListRef = useRef(null);

  const scrollLeft = () => {
    setCurrentIndex((prev) => Math.max(prev - 1, 0));
    if (cityListRef.current) {
      cityListRef.current.scrollBy({ left: -200, behavior: "smooth" });
    }
  };

  const scrollRight = () => {
    setCurrentIndex((prev) => Math.min(prev + 1, cities.length - 6));
    if (cityListRef.current) {
      cityListRef.current.scrollBy({ left: 200, behavior: "smooth" });
    }
  };

  return (
    <section className="featured-hotels">
      <div>
        <h2>Khám Phá Việt Nam</h2>
        <p>Tìm và đặt phòng khách sạn nhanh chóng!</p>
        <div className="arrow-container">
          <button
            className="arrow left-arrow"
            onClick={scrollLeft}
            disabled={currentIndex === 0}
          >
            ❮
          </button>
          <div className="city-list" ref={cityListRef}>
            {cities.slice(currentIndex, currentIndex + 6).map((city, index) => (
              <div key={index} className="city-item">
                <div className="city-card">
                  <img
                    src={`../Asset/HotelImage2/${city.image}`}
                    alt={city.name}
                  />
                </div>
                <div>
                  <p>{city.name}</p>
                  <p>{city.number}</p>
                </div>
              </div>
            ))}
          </div>
          <button
            className="arrow right-arrow"
            onClick={scrollRight}
            disabled={currentIndex >= cities.length - 6}
          >
            ❯
          </button>
        </div>
      </div>
      <div>
        <h2>Ưu đãi</h2>
        <p>Khuyến mãi, giảm giá và ưu đãi đặc biệt dành riêng cho bạn</p>
        <div className="image-container">
          <img
            src="https://dulichvn.org.vn/nhaptin/uploads/images/2023/Thang7/207-Phat-trien-DL-dem-tao-gia-tri-gia-tang-cao-cho-DL-Viet.jpg"
            alt="Hai người trò chuyện bên hồ bơi"
            className="promotion-image"
          />
          <img
            src="https://baobinhduong.vn/image/fckeditor/upload/2023/20230826/images/13.jpg"
            alt="Hai người trò chuyện bên hồ bơi"
            className="promotion-image"
          />
          <div className="text-overlay">
            <h2>Đặt liền tay, bắt ngay ưu đãi</h2>
            <p>Tiết kiệm từ 15% trở lên khi đặt và lưu trú trước 1/10/2024</p>
            <button className="btn">Tìm Ưu Đãi Mùa Du Lịch</button>
          </div>
          <div className="text-overlay1">
            <h2>Vui là chính, không cần dài</h2>
            <p>
              Kết năm với kỳ nghỉ ngắn. Tiết kiệm từ 15% trở lên khi đặt và lưu
              trú đến hết 7/1/2025.{" "}
            </p>
            <button className="btn">Tìm Ưu Đãi Cuối Năm</button>
          </div>
        </div>
      </div>
      <div>
        <h2>Top Khách Sạn</h2>
        <div className="hotel-list">
          {hotels.map((hotel, index) => (
            <div key={index} className="hotel-card">
              <img
                src={`../Asset/HotelImage/${hotel.image}`}
                alt={hotel.name}
              />
              <h3>{hotel.name}</h3>
              <p>{hotel.location}</p>
              <p>{hotel.price}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default FeaturedHotels;
