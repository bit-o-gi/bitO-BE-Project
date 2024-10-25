import {createSlice} from '@reduxjs/toolkit';

interface SidebarState {
    isOpen: boolean;
}

const initialState: SidebarState = {
    isOpen: false,
};

const sidebar = createSlice({
    name: 'sidebar',
    initialState,
    reducers: {
        openSidebar: (state) => {
            state.isOpen = true;
        },
        closeSidebar: (state) => {
            state.isOpen = false;
        }
    },
});

export const {openSidebar, closeSidebar} = sidebar.actions;
export default sidebar.reducer;