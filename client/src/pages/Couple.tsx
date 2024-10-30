import React, {useState} from "react";
import styled from "styled-components";
import tw from "tailwind-styled-components";
import Sidebar from "../components/Sidebar";
import axios from "axios";

const Couple = () => {
  const [receiverEmail, setReceiverEmail] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    let senderEmail = sessionStorage.getItem('myEmail');
    axios.post('api/v1/couples', {senderEmail, receiverEmail}, {
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then((res) => {
      console.log(res);
      alert('연결 요청이 전송되었습니다.');
    })
    .catch((err) => {
      console.log(err);
      alert('연결 요청에 실패했습니다.');
    });
  }

  return (
      <Container>
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label htmlFor="receiverEmail">연결할 상대방의 이메일을 입력해주세요.</Label>
            <Input
                type="email"
                id="receiverEmail"
                value={receiverEmail}
                onChange={(e) => setReceiverEmail(e.target.value)}
                placeholder="Enter receiver's email"
            />
          </FormGroup>
          <Button type="submit">Connect</Button>
        </Form>
      </Container>
  );
};

export default Couple;

const Container = tw.div`
  flex
  justify-center
  items-center
  bg-gray-100
`;

const Form = tw.form`
  bg-white
  p-8
  rounded-lg
  shadow-lg
  w-full
  max-w-md
`;

const FormGroup = tw.div`
  mb-6
`;

const Label = tw.label`
  block
  text-gray-700
  font-bold
  mb-2
`;

const Input = tw.input`
  shadow
  appearance-none
  border
  rounded
  w-full
  py-2
  px-3
  text-gray-700
  leading-tight
  focus:outline-none
  focus:shadow-outline
`;

const Button = tw.button`
  bg-blue-500
  hover:bg-blue-700
  text-white
  font-bold
  py-2
  px-4
  rounded
  focus:outline-none
  focus:shadow-outline
`;