import React from "react";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../store";
import { toggleSidebar } from "../store/reducer/reducerSidebar";
import { FaBars, FaTimes } from "react-icons/fa";
import tw from "tailwind-styled-components";

const Sidebar = () => {
  const isSidebarOpen = useSelector((state: RootState) => state.sidebar.isOpen);
  const dispatch = useDispatch();

  return (
    <>
      <ToggleButton onClick={() => dispatch(toggleSidebar())}>{isSidebarOpen ? <FaTimes /> : <FaBars />}</ToggleButton>
      {isSidebarOpen && <Overlay onClick={() => dispatch(toggleSidebar())} />}
      {isSidebarOpen && (
        <SidebarContainer>
          <StyledLink to="/">Home</StyledLink>
          <StyledLink to="/">Anniversary</StyledLink>
          <StyledLink to="/Schedule">Schedule</StyledLink>
        </SidebarContainer>
      )}
    </>
  );
};

const ToggleButton = tw.button`
    absolute
    top-4
    left-4
    z-10
    bg-blue-500
    text-white
    rounded-full
    p-2
    shadow-lg
    hover:bg-blue-700
    transition
    duration-300
`;

const SidebarContainer = tw.div`
    w-64
    h-screen
    bg-gray-800
    text-white
    flex
    flex-col
    items-center
    pt-8
    fixed
    left-0
    top-0
    shadow-2xl
    transition-transform
    duration-300
    z-20
`;

const StyledLink = tw(Link)`
    my-4
    text-xl
    no-underline
    hover:text-orange-500
    transition
    duration-300
`;

const Overlay = tw.div`
    fixed
    inset-0
    bg-black
    opacity-50
    z-10
`;

export default Sidebar;
