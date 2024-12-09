package bit.day.dto;

import bit.base.BaseRequest;
import bit.day.domain.Day;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DayRequest implements BaseRequest<Day> {
    private Long coupleId;
    private String title;
    private LocalDate startDate;

    @Override
    public DayCommand toCommand() {
        return new DayCommand(coupleId, title, startDate);
    }

}
