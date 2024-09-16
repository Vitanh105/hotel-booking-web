import "./App.css";
import { Route, Routes } from "react-router-dom";
import HomePage from "./Page/HomePage";
import Cooperation from "./Page/Cooperation";
import SignUp from "./Page/SignUp";
import Login from "./Page/Login";
import Privacy from "./Page/Privacy";
import Terms from "./Page/Terms";
import Contact from "./Page/Contact";

import { useLocation } from "react-router-dom";
import { routes } from "./Router/Routes";
function App() {
  const location = useLocation();
  // Đặt các đường dẫn  không muốn hiển thị homepage
  const hideMenuOnPaths = [
    "/homepage",
    "/cooperation",
    "/signup",
    "/login",
    "/privacy",
    "/terms",
    "/contact",
    "/forgetpassword",
  ];
  return (
    <div>
      {!hideMenuOnPaths.includes(location.pathname) && <HomePage />}
      {routes}
     
    </div>
  );
}

export default App;
