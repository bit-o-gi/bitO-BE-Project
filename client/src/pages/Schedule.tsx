"use client";

import { useEffect, useState } from "react";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";

export default function Calendar() {
  const [events, setEvents] = useState<{ title: string; start: string; end: string; color: string }[]>([]);

  useEffect(() => {
    setEvents([
      {
        title: "공부하기",
        start: "2024-09-13",
        end: "2024-09-14",
        color: "#5302FF",
      },
      {
        title: "축구하기",
        start: "2024-09-15",
        end: "2024-09-19",
        color: "#5302FF",
      },
      {
        title: "빨래하기",
        start: "2024-09-28",
        end: "2024-09-30",
        color: "#5302FF",
      },
    ]);
  }, []);

  const renderDayCellContent = (renderProps: { dayNumberText: string }) => {
    const dayNumber = renderProps.dayNumberText.replace("일", "");
    return <span>{dayNumber}</span>;
  };

  return (
    <button type="button">
      <FullCalendar
        plugins={[dayGridPlugin, interactionPlugin]}
        initialView="dayGridMonth"
        locale="ko"
        events={events}
        headerToolbar={{ left: "prev", center: "title", right: "next" }}
        titleFormat={{ year: "numeric", month: "numeric" }}
        dayCellContent={renderDayCellContent}
        height="auto"
        z-index="0"
      />
    </button>
  );
}
