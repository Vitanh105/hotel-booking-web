import React, { useState, useRef, useEffect } from 'react';
import { ReactComponent as UserIcon } from '../assets/icons/user.svg';
import { ReactComponent as CloseIcon } from '../assets/icons/close.svg';
import { ReactComponent as GoogleIcon } from '../assets/icons/google.svg';
import { ReactComponent as FacebookIcon } from '../assets/icons/facebook.svg';

const Header = () => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [isPopupOpen, setIsPopupOpen] = useState(false);
  const [isLoginPopupOpen, setIsLoginPopupOpen] = useState(false);
  const [isRegisterPopupOpen, setIsRegisterPopupOpen] = useState(false);
  const [isForgotPasswordPopupOpen, setIsForgotPasswordPopupOpen] = useState(false);
  const [isConfirmationPopupOpen, setIsConfirmationPopupOpen] = useState(false);
  const [isResetPasswordPopupOpen, setIsResetPasswordPopupOpen] = useState(false);
  const [forgotPasswordEmail, setForgotPasswordEmail] = useState('');
  const [isFormSubmitted, setIsFormSubmitted] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [agreeTerms, setAgreeTerms] = useState(false);
  const [showTermsError, setShowTermsError] = useState(false);

  const dropdownRef = useRef(null);

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

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  const togglePopup = () => {
    setIsPopupOpen(!isPopupOpen);
    setIsFormSubmitted(false);
  };

  const toggleLoginPopup = () => {
    setIsLoginPopupOpen(!isLoginPopupOpen);
  };

  const toggleRegisterPopup = () => {
    setIsRegisterPopupOpen(!isRegisterPopupOpen);
  };

  const toggleForgotPasswordPopup = () => {
    setIsForgotPasswordPopupOpen(!isForgotPasswordPopupOpen);
    setIsLoginPopupOpen(false);
  };

  const toggleConfirmationPopup = () => {
    setIsConfirmationPopupOpen(!isConfirmationPopupOpen);
    setIsForgotPasswordPopupOpen(false);
  };

  const toggleResetPasswordPopup = () => {
    setIsResetPasswordPopupOpen(!isResetPasswordPopupOpen);
    setIsConfirmationPopupOpen(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setIsFormSubmitted(true);
  };

  const handleRegisterSubmit = (e) => {
    e.preventDefault();
    if (!agreeTerms) {
      setShowTermsError(true);
    } else {
      setShowTermsError(false);
      setIsFormSubmitted(true);
    }
  };

  const handleForgotPasswordSubmit = (e) => {
    e.preventDefault();
    toggleConfirmationPopup();
  };

  const handleResetPasswordSubmit = (e) => {
    e.preventDefault();
    setIsResetPasswordPopupOpen(false);
    setIsLoginPopupOpen(true);
  };

  const switchToRegister = () => {
    setIsLoginPopupOpen(false);
    setIsRegisterPopupOpen(true);
  };

  const switchToLogin = () => {
    setIsRegisterPopupOpen(false);
    setIsLoginPopupOpen(true);
  };

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const toggleConfirmPasswordVisibility = () => {
    setShowConfirmPassword(!showConfirmPassword);
  };

  return (
    <header className="bg-white text-black flex items-center justify-between px-4 py-4 shadow-md relative sticky top-0 z-50">
      <div className="flex items-center space-x-4">
        <div className="text-black font-bold text-xl">SWEET HOME</div>
      </div>
      <nav className="flex-grow flex justify-end space-x-8 text-lg font-medium mr-10">
        <a onClick={togglePopup} className="hover:text-gray-500 mt-[1px] cursor-pointer">Hợp tác với chúng tôi</a>
      </nav>
      <div className="relative flex items-center space-x-4" ref={dropdownRef}>
        <UserIcon className="w-6 h-6 hover:text-gray-500 cursor-pointer mr-5" onClick={toggleDropdown} />
        {isDropdownOpen && (
          <div className="absolute right-0 top-10 mt-2 w-32 bg-white border border-gray-200 shadow-lg rounded-md">
            <a onClick={toggleLoginPopup} className="block px-4 py-2 text-gray-700 hover:bg-gray-100 cursor-pointer">Đăng nhập</a>
            <a onClick={switchToRegister} className="block px-4 py-2 text-gray-700 hover:bg-gray-100 cursor-pointer">Đăng ký</a>
          </div>
        )}
      </div>

      {/* Hợp tác Popup */}
      {isPopupOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50 z-50">
          <div className="relative bg-white p-8 rounded-lg shadow-lg w-96">
            <button
              className="absolute top-2 right-2 text-gray-500 hover:text-gray-700"
              onClick={togglePopup}
            >
              <CloseIcon className="w-6 h-6" />
            </button>

            {/* Check if form is submitted */}
            {isFormSubmitted ? (
              <div className="text-center">
                <h2 className="text-2xl font-bold mb-4">Đã gửi yêu cầu</h2>
                <p className="mb-4">Cảm ơn bạn đã gửi yêu cầu hợp tác, chúng tôi sẽ phản hồi lại yêu cầu của bạn sớm nhất có thể.</p>
                <button
                  onClick={togglePopup}
                  className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-yellow-300"
                >
                  Ok
                </button>
              </div>
            ) : (
              <div>
                <h2 className="text-2xl font-bold mb-4">Đăng ký trở thành đối tác</h2>
                <form onSubmit={handleSubmit}>
                  <div className="mb-4 flex flex-col items-start">
                    <label htmlFor="hotelName" className="block text-sm font-medium text-gray-700">Tên khách sạn</label>
                    <input
                      id="hotelName"
                      type="text"
                      className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                      placeholder="Nhập tên khách sạn"
                      required
                    />
                  </div>
                  <div className="mb-4 flex flex-col items-start">
                    <label htmlFor="hotelAddress" className="block text-sm font-medium text-gray-700">Địa chỉ khách sạn</label>
                    <input
                      id="hotelAddress"
                      type="text"
                      className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                      placeholder="Nhập địa chỉ khách sạn"
                      required
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
            )}
          </div>
        </div>
      )}

      {/* Login Popup */}
      {isLoginPopupOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50 z-50">
          <div className="relative bg-white p-8 rounded-lg shadow-lg w-96">
            <button className="absolute top-2 right-2 text-gray-500 hover:text-gray-700" onClick={toggleLoginPopup}>
              <CloseIcon className="w-6 h-6" />
            </button>
            <h2 className="text-2xl font-bold mb-4">Đăng nhập</h2>
            <form>
              <div className="mb-4 flex flex-col items-start">
                <label htmlFor="loginEmail" className="block text-sm font-medium text-gray-700">Email:</label>
                <input
                  id="loginEmail"
                  type="email"
                  className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                  placeholder="Nhập email"
                  required
                />
              </div>
              <div className="mb-4 flex flex-col items-start">
                <label htmlFor="loginPassword" className="block text-sm font-medium text-gray-700">Mật khẩu:</label>
                <div className="relative w-full">
                  <input
                    id="loginPassword"
                    type={showPassword ? "text" : "password"}
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                    placeholder="Nhập mật khẩu"
                    required
                  />
                  <button
                    type="button"
                    onClick={togglePasswordVisibility}
                    className="absolute right-2 top-2 text-gray-500 hover:text-gray-700"
                  >
                    {showPassword ? "Ẩn" : "Hiện"}
                  </button>
                </div>
              </div>
              <div className="flex items-center mb-4">
                <input
                  id="keepLoggedIn"
                  type="checkbox"
                  className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                />
                <label htmlFor="keepLoggedIn" className="ml-2 block text-sm text-gray-900">Duy trì đăng nhập</label>
              </div>
              <div className="flex justify-between items-center mb-4">
                <a onClick={toggleForgotPasswordPopup} className="text-sm text-blue-500 hover:text-blue-700 cursor-pointer">Quên mật khẩu?</a>
                <button type="submit" className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-yellow-300">Đăng nhập</button>
              </div>
              <p className="text-center text-sm mb-4">Chưa có tài khoản? <a onClick={switchToRegister} className="text-blue-500 hover:text-blue-700 cursor-pointer">Đăng ký</a></p>
              <div className="flex justify-center space-x-4">
                <button type="button" className="flex items-center space-x-2 px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-200">
                  <GoogleIcon className="w-5 h-5" />
                  <span>Google</span>
                </button>
                <button type="button" className="flex items-center space-x-2 px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-200">
                  <FacebookIcon className="w-5 h-5" />
                  <span>Facebook</span>
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Register Popup */}
      {isRegisterPopupOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50 z-50">
          <div className="relative bg-white p-8 rounded-lg shadow-lg w-96">
            <button className="absolute top-2 right-2 text-gray-500 hover:text-gray-700" onClick={toggleRegisterPopup}>
              <CloseIcon className="w-6 h-6" />
            </button>
            <h2 className="text-2xl font-bold mb-4">Đăng ký</h2>
            <form onSubmit={handleRegisterSubmit}>
              <div className="mb-4 flex flex-col items-start">
                <label htmlFor="fullName" className="block text-sm font-medium text-gray-700">Họ và tên:</label>
                <input
                  id="fullName"
                  type="text"
                  className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                  placeholder="Nhập họ và tên"
                  required
                />
              </div>
              <div className="mb-4 flex flex-col items-start">
                <label htmlFor="registerEmail" className="block text-sm font-medium text-gray-700">Email:</label>
                <input
                  id="registerEmail"
                  type="email"
                  className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                  placeholder="Nhập email"
                  required
                />
              </div>
              <div className="mb-4 flex flex-col items-start">
                <label htmlFor="registerPassword" className="block text-sm font-medium text-gray-700">Mật khẩu:</label>
                <div className="relative w-full">
                  <input
                    id="registerPassword"
                    type={showPassword ? "text" : "password"}
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                    placeholder="Nhập mật khẩu"
                    required
                  />
                  <button
                    type="button"
                    onClick={togglePasswordVisibility}
                    className="absolute right-2 top-2 text-gray-500 hover:text-gray-700"
                  >
                    {showPassword ? "Ẩn" : "Hiện"}
                  </button>
                </div>
              </div>
              <div className="mb-4 flex flex-col items-start">
                <label htmlFor="confirmPassword" className="block text-sm font-medium text-gray-700">Nhập lại mật khẩu:</label>
                <div className="relative w-full">
                  <input
                    id="confirmPassword"
                    type={showConfirmPassword ? "text" : "password"}
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                    placeholder="Nhập lại mật khẩu"
                    required
                  />
                  <button
                    type="button"
                    onClick={toggleConfirmPasswordVisibility}
                    className="absolute right-2 top-2 text-gray-500 hover:text-gray-700"
                  >
                    {showConfirmPassword ? "Ẩn" : "Hiện"}
                  </button>
                </div>
              </div>
              <div className="flex items-center mb-4">
                <input
                  id="agreeTerms"
                  type="checkbox"
                  className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                  checked={agreeTerms}
                  onChange={() => setAgreeTerms(!agreeTerms)}
                />
                <label htmlFor="agreeTerms" className="ml-2 block text-sm text-gray-900">
                  Bạn đồng ý với điều khoản của chúng tôi
                </label>
              </div>
              {showTermsError && (
                <p className="text-red-500 text-sm mb-4">Bạn chưa đồng ý với điều khoản của chúng tôi</p>
              )}
              <div className='flex flex-col items-end'>
                <button type="submit" className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-yellow-300">Đăng ký</button>
              </div>
              <p className="text-center text-sm mt-4">Bạn đã có tài khoản? <a onClick={switchToLogin} className="text-blue-500 hover:text-blue-700 cursor-pointer">Đăng nhập</a></p>
              <div className="flex justify-center space-x-4 mt-4">
                <button type="button" className="flex items-center space-x-2 px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-200">
                  <GoogleIcon className="w-5 h-5" />
                  <span>Google</span>
                </button>
                <button type="button" className="flex items-center space-x-2 px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-200">
                  <FacebookIcon className="w-5 h-5" />
                  <span>Facebook</span>
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Forgot Password Popup */}
      {isForgotPasswordPopupOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50 z-50">
          <div className="relative bg-white p-8 rounded-lg shadow-lg w-96">
            <button className="absolute top-2 right-2 text-gray-500 hover:text-gray-700" onClick={toggleForgotPasswordPopup}>
              <CloseIcon className="w-6 h-6" />
            </button>
            <h2 className="text-2xl font-bold mb-4">Quên mật khẩu</h2>
            <form onSubmit={handleForgotPasswordSubmit}>
              <div className="mb-4 flex flex-col items-start">
                <label htmlFor="forgotPasswordEmail" className="block text-sm font-medium text-gray-700">Email:</label>
                <input
                  id="forgotPasswordEmail"
                  type="email"
                  value={forgotPasswordEmail}
                  onChange={(e) => setForgotPasswordEmail(e.target.value)}
                  className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                  placeholder="Nhập email"
                  required
                />
              </div>
              <button type="submit" className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-yellow-300" onClick={toggleConfirmationPopup}>Xác thực</button>
            </form>
          </div>
        </div>
      )}

      {/* Confirmation Popup */}
      {isConfirmationPopupOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50 z-50">
          <div className="relative bg-white p-8 rounded-lg shadow-lg w-96">
            <button className="absolute top-2 right-2 text-gray-500 hover:text-gray-700" onClick={toggleResetPasswordPopup}>
              <CloseIcon className="w-6 h-6" />
            </button>
            <h2 className="text-2xl font-bold mb-4">Kiểm tra email của bạn</h2>
            <p className="text-center">Chúng tôi vừa gửi email hướng dẫn và liên kết đặt lại mật khẩu tới {forgotPasswordEmail}.</p>
          </div>
        </div>
      )}

      {/* Reset Password Popup */}
      {isResetPasswordPopupOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50 z-50">
          <div className="relative bg-white p-8 rounded-lg shadow-lg w-96">
            <button className="absolute top-2 right-2 text-gray-500 hover:text-gray-700" onClick={toggleResetPasswordPopup}>
              <CloseIcon className="w-6 h-6" />
            </button>
            <h2 className="text-2xl font-bold mb-4">Đổi mật khẩu</h2>
            <form onSubmit={handleResetPasswordSubmit}>
              <div className="mb-4 flex flex-col items-start">
                <label htmlFor="resetPassword" className="block text-sm font-medium text-gray-700">Mật khẩu mới:</label>
                <div className="relative w-full">
                  <input
                    id="resetPassword"
                    type={showPassword ? "text" : "password"}
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                    placeholder="Nhập mật khẩu mới"
                    required
                  />
                  <button
                    type="button"
                    onClick={togglePasswordVisibility}
                    className="absolute right-2 top-2 text-gray-500 hover:text-gray-700"
                  >
                    {showPassword ? "Ẩn" : "Hiện"}
                  </button>
                </div>
              </div>
              <div className="mb-4 flex flex-col items-start">
                <label htmlFor="resetConfirmPassword" className="block text-sm font-medium text-gray-700">Nhập lại mật khẩu:</label>
                <div className="relative w-full">
                  <input
                    id="resetConfirmPassword"
                    type={showConfirmPassword ? "text" : "password"}
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                    placeholder="Nhập lại mật khẩu"
                    required
                  />
                  <button
                    type="button"
                    onClick={toggleConfirmPasswordVisibility}
                    className="absolute right-2 top-2 text-gray-500 hover:text-gray-700"
                  >
                    {showConfirmPassword ? "Ẩn" : "Hiện"}
                  </button>
                </div>
              </div>
              <button type="submit" className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-yellow-300">Xác nhận</button>
            </form>
          </div>
        </div>
      )}
    </header>

  );
};

export default Header;