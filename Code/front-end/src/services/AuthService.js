import axios from 'axios';

const API_URL = 'http://localhost:8080/auth'; // Adjust based on your backend URL

const AuthService = {
  signUp: async (userData) => {
    const response = await axios.post(`${API_URL}/signup`, userData);
    return response.data;
  },

  signIn: async (loginData) => {
    const response = await axios.post(`${API_URL}/signin`, loginData);
    return response.data;
  },
};

export default AuthService;
