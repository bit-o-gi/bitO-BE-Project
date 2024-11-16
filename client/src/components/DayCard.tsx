import React, {useState, useEffect} from "react";
import tw from "tailwind-styled-components";
import {
    Card,
    CardBody,
    CardFooter,
    Typography,
    Button,
} from "@material-tailwind/react";
import axios from "axios";

export function DayCard() {
    const [title, setTitle] = useState('우리는');

    useEffect(() => {
        axios.get('/api/v1/day/1').then(
            (response) => {
                console.log(response);
                setTitle(response.data.title);
            },
            (error) => {
                console.log(error);
            }
        )

    }, [])
    return (
        <Card className="mt-6 mb-4 w-96">
            <CardBody className="text-center">
                <Typography variant="h5" color="blue-gray" className="mb-2">
                    <Title>{title}</Title>
                </Typography>
                <Typography>
                    <Countdown>100일</Countdown>
                </Typography>
            </CardBody>
        </Card>
    );
}

const Title = tw.h1`
    text-4xl
    font-bold
    p-4
`;


const Countdown = tw.h3`
    text-2xl
    font-semibold
    text-pink-500
`;