import React from "react";
import styled from "styled-components";

interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  event: {
    title: string;
    content: string;
    writeUser: string;
  } | null;
}

const Modal: React.FC<ModalProps> = ({ isOpen, onClose, event }) => {
  if (!isOpen) return null;

  return (
    <PopupContainer>
      <PopupContent>
        {event && (
          <>
            <Title>{event.title}</Title>
            <UserName>
              <strong>작성자:</strong> {event.writeUser}
            </UserName>
            <Content>
              <strong>내용:</strong> {event.content}
            </Content>
          </>
        )}
        <CloseButton onClick={onClose}>닫기</CloseButton>
      </PopupContent>
    </PopupContainer>
  );
};

const PopupContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
`;

const PopupContent = styled.div`
  background: white;
  padding: 30px;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
  position: relative;
  width: 90%;
  max-width: 500px;
  animation: fadeIn 0.3s ease;

  @keyframes fadeIn {
    from {
      opacity: 0;
      transform: translateY(-20px);
    }
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }
`;

const Title = styled.h2`
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
`;

const UserName = styled.p`
  font-size: 1rem;
  color: #555;
  margin-bottom: 15px;
`;

const Content = styled.p`
  font-size: 1rem;
  color: #444;
  margin-bottom: 20px;
`;

const CloseButton = styled.button`
  background: #007bff;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background 0.3s;

  &:hover {
    background: #0056b3;
  }
`;

export default Modal;
