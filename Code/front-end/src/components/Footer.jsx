import React from 'react';
import { ReactComponent as FacebookIcon } from '../assets/icons/facebook.svg';
import { ReactComponent as InstagramIcon } from '../assets/icons/instagram.svg';
import { ReactComponent as XIcon } from '../assets/icons/twitter-x.svg';

const Footer = () => {
  return (
    <footer className="bg-white py-4 px-6 border-t border-gray-200 sticky bottom-0 left-0 w-full">
      <div className="container mx-auto flex flex-wrap justify-between items-center">
        <div className="flex items-center space-x-4">
          <span className="text-gray-500">Copyright © 2024 Sweet Home</span>
        </div>
        <div className="flex flex-col space-y-2 text-center">
          <span>Phương thức liên hệ</span>
          <div className="flex justify-center space-x-4">
            <a href="https://facebook.com" target="_blank" rel="noopener noreferrer">
              <FacebookIcon className="w-6 h-6 text-gray-600 hover:text-blue-800" />
            </a>
            <a href="https://instagram.com" target="_blank" rel="noopener noreferrer">
              <InstagramIcon className="w-6 h-6 text-gray-600 hover:text-pink-800" />
            </a>
            <a href="https://x.com" target="_blank" rel="noopener noreferrer">
              <XIcon className="w-6 h-6 text-gray-800 hover:text-gray-900" />
            </a>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;