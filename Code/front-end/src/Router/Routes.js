import { Route, Routes } from "react-router-dom";
import Cooperation from "../Page/Cooperation";
import HomePage from "../Page/HomePage";
import SignUp from "../Page/SignUp";
import Login from "../Page/Login";
import Privacy from "../Page/Privacy";
import Terms from "../Page/Terms";
import Contact from "../Page/Contact";
import ForgetPassword  from "../Page/ForgetPassword";


export const routes = (
  <Routes>
    <Route path="/homepage" element={<HomePage />} />
    <Route path="/cooperation" element={<Cooperation />} />
    <Route path="/signup" element={<SignUp />} />
    <Route path="/login" element={<Login />} />
    <Route path="/privacy" element={<Privacy />} />
    <Route path="/terms" element={<Terms />} />
    <Route path="/contact" element={<Contact />} />
    <Route path="/forgetpassword" element={<ForgetPassword />} />
  </Routes>
);
