// client/src/App.tsx
import React, { Suspense, lazy } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import "./App.css";
import { useSelector } from "react-redux";
import { getAccessToken } from "./store/reducer/reducerUser";
import SideBarOutlet from "./outlet/SideBarOutlet";

const Login = lazy(() => import("./pages/Login"));
const Main = lazy(() => import("./pages/Main"));
const Couple = lazy(() => import("./pages/Couple"));
const Schedule = lazy(() => import("./pages/Schedule"));

const isAuthenticated = () => {
  // Todo: 로그인 여부를 확인하는 로직을 작성합니다.
  const accessToken = sessionStorage.getItem("accessToken");
  return accessToken !== null;
};

function App() {
  return (
    <Router>
      <Suspense fallback={<div>Loading...</div>}>
        <Routes>
          <Route element={<SideBarOutlet />}>
            <Route path="/" element={isAuthenticated() ? <Main /> : <Navigate to="/login" />} />
            <Route path="/couple" element={isAuthenticated() ? <Couple /> : <Navigate to="/login" />} />
            <Route path="/schedule" element={isAuthenticated() ? <Schedule /> : <Navigate to="/login" />} />
          </Route>
          <Route path="/login" element={<Login />} />
          <Route path="*" element={<Navigate to="/login" />} />
        </Routes>
      </Suspense>
    </Router>
  );
}

export default App;
