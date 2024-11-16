package bit.day.dto;

import bit.day.domain.Day;
import java.time.LocalDate;

public class DayCommand implements BaseCommand<Day> {
    public final Long coupleId;
    public final String title;
    public final LocalDate startDate;

    public DayCommand(Long coupleId, String title, LocalDate startDate) {
        this.coupleId = coupleId;
        this.title = title;
        this.startDate = startDate;
    }
}
