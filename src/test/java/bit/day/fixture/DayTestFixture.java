package bit.day.fixture;

import bit.couple.domain.Couple;
import bit.day.domain.Day;
import java.time.LocalDate;

public class DayTestFixture {

    public static Day createSampleDay() {
        return Day.builder()
                .id(1L)
                .title("디데이 테스트 타이틀")
                .couple(new Couple())
                .startDate(LocalDate.of(2024, 11, 12))
                .build();
    }

}
