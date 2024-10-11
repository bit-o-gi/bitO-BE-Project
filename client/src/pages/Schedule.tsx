"use client";

import { useEffect, useState } from "react";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import Modal from "../components/popup/SchdulePopup"; // 모달 컴포넌트 임포트
import styled from "styled-components";

const getRandomColor = () => {
  const letters = "0123456789ABCDEF";
  let color = "#";
  for (let i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
};

export default function Calendar() {
  const [events, setEvents] = useState<{ title: string; start: string; end: string; color: string; content: string; writeUser: string }[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedEvent, setSelectedEvent] = useState<{ title: string; content: string; writeUser: string } | null>(null);

  useEffect(() => {
    setEvents([
      {
        title: "공부하기",
        start: "2024-10-13",
        end: "2024-10-14",
        color: getRandomColor(),
        content: "수학과 과학 공부",
        writeUser: "사용자1",
      },
      {
        title: "축구하기",
        start: "2024-10-15",
        end: "2024-10-19",
        color: getRandomColor(),
        content: "친구들과 축구 경기",
        writeUser: "사용자2",
      },
      {
        title: "빨래하기",
        start: "2024-09-28",
        end: "2024-09-30",
        color: getRandomColor(),
        content: "주말 빨래",
        writeUser: "사용자3",
      },
      {
        title: "프로젝트 회의",
        start: "2024-10-10",
        end: "2024-10-10",
        color: getRandomColor(),
        content: "팀과 함께 프로젝트 진행상황 점검",
        writeUser: "사용자4",
      },
      {
        title: "친구와 저녁",
        start: "2024-10-12",
        end: "2024-10-12",
        color: getRandomColor(),
        content: "친구와 함께 저녁 먹기",
        writeUser: "사용자5",
      },
      {
        title: "영화 관람",
        start: "2024-10-20",
        end: "2024-10-20",
        color: getRandomColor(),
        content: "새로운 영화를 관람하기",
        writeUser: "사용자6",
      },
      {
        title: "운동하기",
        start: "2024-10-21",
        end: "2024-10-21",
        color: getRandomColor(),
        content: "헬스장 가기",
        writeUser: "사용자7",
      },
      {
        title: "가족 모임",
        start: "2024-10-23",
        end: "2024-10-23",
        color: getRandomColor(),
        content: "가족들과 함께 모임",
        writeUser: "사용자8",
      },
      {
        title: "독서 시간",
        start: "2024-10-25",
        end: "2024-10-25",
        color: getRandomColor(),
        content: "좋아하는 책 읽기",
        writeUser: "사용자9",
      },
      {
        title: "독서 시간2",
        start: "2024-10-25",
        end: "2024-10-25",
        color: getRandomColor(),
        content: "좋아하는 책 읽기",
        writeUser: "사용자9",
      },
      {
        title: "주말 여행",
        start: "2024-10-27",
        end: "2024-10-29",
        color: getRandomColor(),
        content:
          "가족과 함께 주말 여행을 계획하고 있습니다. 우리는 바다 근처의 아름다운 리조트에 묵을 예정이며, 다양한 해양 스포츠를 즐기고, 맛있는 해산물 요리를 맛볼 계획입니다. 또한, 저녁에는 해변에서 모닥불을 피우고, 가족과 함께 재미있는 이야기를 나누며 소중한 시간을 보낼 것입니다. 이번 여행은 가족 간의 유대감을 더욱 강화하고, 새로운 추억을 만들 수 있는 좋은 기회가 될 것입니다. 여행 중에는 사진도 많이 찍어서 나중에 함께 보며 추억을 되새기고 싶습니다.",
        writeUser: "사용자10",
      },
    ]);
  }, []);

  const handleEventClick = (info: any) => {
    const event = events.find((e) => e.title === info.event.title);
    if (event) {
      setSelectedEvent(event);
      setIsModalOpen(true);
    }
  };

  const renderDayCellContent = (renderProps: { dayNumberText: string }) => {
    const dayNumber = renderProps.dayNumberText.replace("일", "");
    return <span>{dayNumber}</span>;
  };

  return (
    <Section>
      <FullCalendar
        plugins={[dayGridPlugin, interactionPlugin]}
        initialView="dayGridMonth"
        locale="ko"
        events={events}
        headerToolbar={{ left: "prev", center: "title", right: "next" }}
        titleFormat={{ year: "numeric", month: "numeric" }}
        dayCellContent={renderDayCellContent}
        height="auto"
        eventClick={handleEventClick} // 이벤트 클릭 핸들러 추가
      />
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} event={selectedEvent} />
    </Section>
  );
}

const Section = styled.section`
  margin-top: 2rem; /* mt-8 (32px) */
  background: white;
  border-radius: 0.5rem; /* rounded-lg */
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* shadow-lg */
  text-align: center;
  width: 90%;
  margin-left: 1rem; /* mx-4 (16px) */
  margin-right: 1rem; /* mx-4 (16px) */
`;
