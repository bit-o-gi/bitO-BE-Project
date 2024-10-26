package bit.dday.dto;

import bit.couple.domain.Couple;
import bit.dday.domain.Dday;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DdayCommand implements BaseCommand<Dday> {
    public final Long userId;
    public final String title;
    public final LocalDateTime targetDate;

    public DdayCommand(Long userId, String title, LocalDateTime targetDate) {
        this.userId = userId;
        this.title = title;
        this.targetDate = targetDate;
    }
}
