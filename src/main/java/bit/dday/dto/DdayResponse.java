package bit.dday.dto;

import bit.dday.domain.Dday;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DdayResponse {
    private Long id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime targetDate;

    public static DdayResponse from(Dday dday) {
        return DdayResponse.builder()
                .id(dday.getId())
                .title(dday.getTitle())
                .targetDate(dday.getTargetDate())
                .build();
    }
}
