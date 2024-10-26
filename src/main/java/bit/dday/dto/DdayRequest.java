package bit.dday.dto;

import bit.dday.domain.Dday;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class DdayRequest implements BaseRequest<Dday> {
    private Long userId;
    private String title;
    private LocalDateTime targetDate;

    @Override
    public DdayCommand toCommand() {
        return new DdayCommand(userId, title, targetDate);
    }
}
