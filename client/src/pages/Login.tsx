import React, {useEffect} from 'react';
import kakaoLoginImage from '../assets/images/kakao_login.png';
import axios from "axios";
import tw from "tailwind-styled-components";
import {useDispatch} from "react-redux";
import {useLocation, useNavigate} from "react-router-dom";
import {
    Card,
    CardHeader,
    CardBody,
    CardFooter,
    Typography,
    Input,
    Checkbox,
    Button,
} from "@material-tailwind/react";

function Login() {
    const dispatch = useDispatch();
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const navigate = useNavigate();

    const handleKakaoLoginBtn = () => {
        axios.get('api/v1/oauth/kakao').then((res) => {
            window.location.href = res.data;
        }).catch((err) => {
            console.log(err);
        });
    }

    useEffect(() => {
        if (queryParams?.get("code")) {
            const params = new URLSearchParams;
            params.append('code', queryParams.get("code") || "");

            axios.post('/api/v1/oauth/kakao/token', params)
                .then((res) => {
                    console.log('token', res.data);
                    sessionStorage.setItem('accessToken', res.data.access_token);
                    handleGetUserInfo(res.data.access_token);
                })
                .catch((err) => {
                    console.log(err);
                })
        }
    }, []);

    const handleGetUserInfo = (accessToken: string) => {
        axios.post('/api/v1/oauth/kakao/access', accessToken, {
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then((res) => {
                console.log('userInfo', res);
                navigate('/');
            })
            .catch((err) => {
                console.log(accessToken);
                console.log(err);
            })
    }

    return (

        <Container>
            <Card className="w-96">
                <Content>
                    <Title>Welcome, Login!</Title>
                    <ImgButton src={kakaoLoginImage} alt="kakaoLogin" onClick={handleKakaoLoginBtn}/>
                </Content>
            </Card>
        </Container>
    );
}

const ImgButton = tw.img`
    cursor-pointer
    items-center
    justify-center
    mx-auto
`;

const Container = tw.div`
    flex
    items-center
    justify-center
    min-h-screen
    bg-gray-100
`;

const Content = tw.div`
    text-center
    bg-white
    p-8
    rounded-lg
    shadow-lg
`;

const Title = tw.h2`
    text-2xl
    font-bold
    mb-4
`;

export default Login;