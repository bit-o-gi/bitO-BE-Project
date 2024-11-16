package bit.day.dto;

import bit.day.domain.Day;
import java.time.LocalDate;
import java.util.Objects;

public class DayCommand implements BaseCommand<Day> {
    public final Long coupleId;
    public final String title;
    public final LocalDate startDate;

    public DayCommand(Long coupleId, String title, LocalDate startDate) {
        this.coupleId = coupleId;
        this.title = title;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        
        if (this == o) {
            return true;
        }
        if (!(o instanceof DayCommand that)) {
            return false;
        }
        return Objects.equals(coupleId, that.coupleId) && Objects.equals(title, that.title)
                && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coupleId, title, startDate);
    }
}
