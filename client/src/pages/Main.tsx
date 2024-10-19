import React, {useEffect} from "react";
import styled from "styled-components";
import Sidebar from "../components/Sidebar";
import {useDispatch} from 'react-redux';
import {setAccessToken} from "../store/reducer/reducerUser";
import tw from "tailwind-styled-components";
import {AnniversaryCard} from "../components/AnniversaryCard";
import {DdayCard} from "../components/DdayCard";

const Main = () => {
    return (
        <MainContainer>
            <Sidebar/>
            <Content>
                {/*<Section>*/}
                <DdayCard/>
                <AnniversaryCard/>
                {/*</Section>*/}
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


const Section = tw.section`
    mt-8
    p-8
    bg-white
    rounded-lg
    shadow-lg
    text-center
    max-w-md
    w-full
`;


export default Main;