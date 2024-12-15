package bit.day.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

import bit.couple.domain.Couple;
import bit.couple.repository.CoupleRepository;
import bit.day.domain.Day;
import bit.day.dto.DayRequest;
import bit.day.fixture.DayTestFixture;
import bit.day.repository.DayRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DayServiceTest {
    @Mock
    private DayRepository dayRepository;
    @Mock
    private CoupleRepository coupleRepository;
    @InjectMocks
    private DayService dayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("디데이 조회 성공")
    @Test
    void getDaySuccessTest() {
        // given
        Day targetDay = DayTestFixture.createSampleDay();
        when(dayRepository.findById(any())).thenReturn(Optional.ofNullable(targetDay));

        // when
        Day day = dayService.getDay(1L);

        // then
        assertThat(day).usingRecursiveComparison().isEqualTo(targetDay);
    }

    @DisplayName("디데이 생성 성공")
    @Test
    void createDaySuccessTest() throws Exception {
        //given
        Day targetDay = DayTestFixture.createSampleDay();
        DayRequest testRequest = new DayRequest(1L, "testRequest", LocalDate.of(2024, 11, 12));
        when(coupleRepository.findById(any())).thenReturn(Optional.of(new Couple()));
        when(dayRepository.save(any())).thenReturn(targetDay);

        // when
        Day newDday = dayService.createDay(testRequest.toCommand());

        // then
        assertThat(newDday).usingRecursiveComparison().isEqualTo(targetDay);
    }

    @DisplayName("디데이 수정 성공")
    @Test
    void updateDdaySuccessTest() {
        // given
        Day targetDay = DayTestFixture.createSampleDay();

        Day updateDay = Day.builder()
                .id(1L)
                .title("업데이트된 디데이 타이틀")
                .couple(new Couple())
                .startDate(LocalDate.of(2024, 11, 12))
                .build();

        DayRequest testRequest = new DayRequest(1L, "testRequest", LocalDate.of(2024, 11, 12));

        when(dayRepository.findById(any())).thenReturn(Optional.of(targetDay));
        when(dayRepository.save(any())).thenReturn(updateDay);

        // when
        Day newDday = dayService.updateDay(targetDay.getId(), testRequest.toCommand());

        // then
        assertThat(newDday)
                .usingRecursiveComparison()
                .isEqualTo(updateDay);
    }

    @DisplayName("디데이 삭제 성공")
    @Test
    void deleteDdaySuccessTest() {
        // given
        when(dayRepository.existsById(any())).thenReturn(true);
        // when
        dayService.deleteDay(1L);

        // then
        then(dayRepository).should().existsById(any());
        then(dayRepository).should().deleteById(any());
    }

}
