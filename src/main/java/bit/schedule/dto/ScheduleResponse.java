package bit.schedule.dto;

import bit.schedule.domain.Schedule;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
public class ScheduleResponse {
    private Long id;

    private String nickName;

    private String title;

    private String content;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    public ScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.nickName = schedule.getUser().getNickName();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.startDateTime = schedule.getStartDateTime();
        this.endDateTime = schedule.getEndDateTime();
    }
}
