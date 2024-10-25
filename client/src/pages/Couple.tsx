import React from "react";
import styled from "styled-components";
import tw from "tailwind-styled-components";
import Sidebar from "../components/Sidebar";

const Couple = () => {
    return (
        <MainContainer>
            <Sidebar/>
            <Content>
                커플 페이지
            </Content>
        </MainContainer>
    );
};

const MainContainer = styled.div`
    display: flex;
    flex-direction: column;
    min-height: 100vh;
`;

const Content = styled.div`
    flex: 1;
    display: flex;
    background-color: #f9fafb;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;


export default Couple;