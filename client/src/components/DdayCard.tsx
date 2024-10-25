import React from "react";
import tw from "tailwind-styled-components";
import {
    Card,
    CardBody,
    CardFooter,
    Typography,
    Button,
} from "@material-tailwind/react";

export function DdayCard() {
    return (
        <Card className="mt-6 mb-4 w-96">
            <CardBody className="text-center">
                <Typography variant="h5" color="blue-gray" className="mb-2">
                    <Title>우리는</Title>
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