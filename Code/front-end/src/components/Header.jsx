import React, { useState, useRef, useEffect } from 'react';
import { ReactComponent as UserIcon } from '../assets/icons/user.svg';
import { ReactComponent as CloseIcon } from '../assets/icons/close.svg';

const Header = () => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [isPopupOpen, setIsPopupOpen] = useState(false);
  const dropdownRef = useRef(null);

  // Toggle dropdown menu
  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  // Close the dropdown if clicking outside of it
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsDropdownOpen(false);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  // Toggle popup visibility
  const togglePopup = () => {
    setIsPopupOpen(!isPopupOpen);
  };

  return (
    <header className="bg-white text-black flex items-center justify-between px-4 py-4 shadow-md relative">
      <div className="flex items-center space-x-4">
        <div className="text-black font-bold text-xl">SWEET HOME</div>
      </div>
      <nav className="flex-grow flex justify-end space-x-8 text-lg font-medium mr-10">
        {/* <button
          className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
          onClick={togglePopup}
        >
          Hợp tác với chúng tôi
        </button> */}
        <a onClick={togglePopup} className="hover:text-gray-500 mt-[1px] cursor-pointer">Hợp tác với chúng tôi</a>
      </nav>
      <div className="relative flex items-center space-x-4" ref={dropdownRef}>
        <UserIcon
          className="w-6 h-6 hover:text-gray-500 cursor-pointer mr-5"
          onClick={toggleDropdown}
        />
        {isDropdownOpen && (
          <div className="absolute right-0 top-10 mt-2 w-48 bg-white border border-gray-200 shadow-lg rounded-md">
            <a href="#login" className="block px-4 py-2 text-gray-700 hover:bg-gray-100">Đăng nhập</a>
            <a href="#register" className="block px-4 py-2 text-gray-700 hover:bg-gray-100">Đăng ký</a>
          </div>
        )}
      </div>

      {/* Popup */}
      {isPopupOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50 z-50">
          <div className="relative bg-white p-8 rounded-lg shadow-lg w-96">
            <button
              className="absolute top-2 right-2 text-gray-500 hover:text-gray-700"
              onClick={togglePopup}
            >
              <CloseIcon className="w-6 h-6" />
            </button>
            <h2 className="text-2xl font-bold mb-4">Đăng ký trở thành đối tác</h2>
            <form>
              <div className="mb-4">
                <label htmlFor="hotelName" className="block text-sm font-medium text-gray-700">Tên khách sạn</label>
                <input
                  id="hotelName"
                  type="text"
                  className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                  placeholder="Nhập tên khách sạn"
                />
              </div>
              <div className="mb-4">
                <label htmlFor="hotelAddress" className="block text-sm font-medium text-gray-700">Địa chỉ khách sạn</label>
                <input
                  id="hotelAddress"
                  type="text"
                  className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                  placeholder="Nhập địa chỉ khách sạn"
                />
              </div>
              <button
                type="submit"
                className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-yellow-300"
              >
                Gửi
              </button>
            </form>
          </div>
        </div>
      )}

    </header>
  );
};

export default Header;