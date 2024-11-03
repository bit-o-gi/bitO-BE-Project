import React from "react";
import {Link} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {RootState} from "../store";
import {closeSidebar, openSidebar} from "../store/reducer/reducerSidebar";
import {
  Button,
  Drawer,
  IconButton,
  List,
  ListItem,
  ListItemPrefix,
  Typography
} from "@material-tailwind/react";

const Sidebar = () => {
  const isSidebarOpen = useSelector((state: RootState) => state.sidebar.isOpen);
  const dispatch = useDispatch();

  return (
      <>
        <div className="fixed top-4 left-4 ">
          <Button className=" p-2 bg-blue-500 text-white rounded-full shadow-lg"
                  onClick={() => dispatch(openSidebar())}>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={2}
                 stroke="currentColor" className="h-6 w-6">
              <path strokeLinecap="round" strokeLinejoin="round" d="M4 6h16M4 12h16m-7 6h7"/>
            </svg>
          </Button>
        </div>
        {isSidebarOpen && (
            <Drawer open={isSidebarOpen} onClose={() => dispatch(closeSidebar())}>
              <div className="mb-2 flex items-center justify-between p-4">
                <Typography variant="h5" color="blue-gray">
                  Menu
                </Typography>
                <IconButton variant="text" color="blue-gray"
                            onClick={() => dispatch(closeSidebar())}>
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                       strokeWidth={2} stroke="currentColor" className="h-5 w-5">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12"/>
                  </svg>
                </IconButton>
              </div>
              <List>
                <Link to={"/"} onClick={() => dispatch(closeSidebar())}>
                  <ListItem>
                    <ListItemPrefix>
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                           fill="currentColor" className="h-5 w-5">
                        <path
                            fillRule="evenodd"
                            d="M2.25 2.25a.75.75 0 000 1.5H3v10.5a3 3 0 003 3h1.21l-1.172 3.513a.75.75 0 001.424.474l.329-.987h8.418l.33.987a.75.75 0 001.422-.474l-1.17-3.513H18a3 3 0 003-3V3.75h.75a.75.75 0 000-1.5H2.25zm6.04 16.5l.5-1.5h6.42l.5 1.5H8.29zm7.46-12a.75.75 0 00-1.5 0v6a.75.75 0 001.5 0v-6zm-3 2.25a.75.75 0 00-1.5 0v3.75a.75.75 0 001.5 0V9zm-3 2.25a.75.75 0 00-1.5 0v1.5a.75.75 0 001.5 0v-1.5z"
                            clipRule="evenodd"
                        />
                      </svg>
                    </ListItemPrefix>
                    Home
                  </ListItem>
                </Link>
                <Link to={"/couple"} onClick={() => dispatch(closeSidebar())}>
                  <ListItem>
                    <ListItemPrefix>
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                           fill="currentColor"
                           className="h-5 w-5">
                        <path
                            fillRule="evenodd"
                            d="M7.5 6v.75H5.513c-.96 0-1.764.724-1.865 1.679l-1.263 12A1.875 1.875 0 004.25 22.5h15.5a1.875 1.875 0 001.865-2.071l-1.263-12a1.875 1.875 0 00-1.865-1.679H16.5V6a4.5 4.5 0 10-9 0zM12 3a3 3 0 00-3 3v.75h6V6a3 3 0 00-3-3zm-3 8.25a3 3 0 106 0v-.75a.75.75 0 011.5 0v.75a4.5 4.5 0 11-9 0v-.75a.75.75 0 011.5 0v.75z"
                            clipRule="evenodd"
                        />
                      </svg>
                    </ListItemPrefix>
                    커플 연결하기
                  </ListItem>
                </Link>
                <Link to={"/schedule"} onClick={() => dispatch(closeSidebar())}>
                  <ListItem>
                    <ListItemPrefix>
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                           fill="currentColor" className="h-5 w-5">
                        <path
                            d="M12 11.993a.75.75 0 0 0-.75.75v.006c0 .414.336.75.75.75h.006a.75.75 0 0 0 .75-.75v-.006a.75.75 0 0 0-.75-.75H12ZM12 16.494a.75.75 0 0 0-.75.75v.005c0 .414.335.75.75.75h.005a.75.75 0 0 0 .75-.75v-.005a.75.75 0 0 0-.75-.75H12ZM8.999 17.244a.75.75 0 0 1 .75-.75h.006a.75.75 0 0 1 .75.75v.006a.75.75 0 0 1-.75.75h-.006a.75.75 0 0 1-.75-.75v-.006ZM7.499 16.494a.75.75 0 0 0-.75.75v.005c0 .414.336.75.75.75h.005a.75.75 0 0 0 .75-.75v-.005a.75.75 0 0 0-.75-.75H7.5ZM13.499 14.997a.75.75 0 0 1 .75-.75h.006a.75.75 0 0 1 .75.75v.005a.75.75 0 0 1-.75.75h-.006a.75.75 0 0 1-.75-.75v-.005ZM14.25 16.494a.75.75 0 0 0-.75.75v.006c0 .414.335.75.75.75h.005a.75.75 0 0 0 .75-.75v-.006a.75.75 0 0 0-.75-.75h-.005ZM15.75 14.995a.75.75 0 0 1 .75-.75h.005a.75.75 0 0 1 .75.75v.006a.75.75 0 0 1-.75.75H16.5a.75.75 0 0 1-.75-.75v-.006ZM13.498 12.743a.75.75 0 0 1 .75-.75h2.25a.75.75 0 1 1 0 1.5h-2.25a.75.75 0 0 1-.75-.75ZM6.748 14.993a.75.75 0 0 1 .75-.75h4.5a.75.75 0 0 1 0 1.5h-4.5a.75.75 0 0 1-.75-.75Z"/>
                        <path
                            fill-rule="evenodd"
                            d="M18 2.993a.75.75 0 0 0-1.5 0v1.5h-9V2.994a.75.75 0 1 0-1.5 0v1.497h-.752a3 3 0 0 0-3 3v11.252a3 3 0 0 0 3 3h13.5a3 3 0 0 0 3-3V7.492a3 3 0 0 0-3-3H18V2.993ZM3.748 18.743v-7.5a1.5 1.5 0 0 1 1.5-1.5h13.5a1.5 1.5 0 0 1 1.5 1.5v7.5a1.5 1.5 0 0 1-1.5 1.5h-13.5a1.5 1.5 0 0 1-1.5-1.5Z"
                            clip-rule="evenodd"
                        />
                      </svg>
                    </ListItemPrefix>
                    Schedule
                  </ListItem>
                </Link>
              </List>
            </Drawer>
        )}
      </>
  );
};

export default Sidebar;
