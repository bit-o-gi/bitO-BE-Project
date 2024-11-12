package bit.day.dto;

import bit.day.domain.Day;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class DayRequest implements BaseRequest<Day> {
    private Long coupleId;
    private String title;
    private LocalDate startDate;

    @Override
    public DayCommand toCommand() {
        return new DayCommand(coupleId, title, startDate);
    }
}
