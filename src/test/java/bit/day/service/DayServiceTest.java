package bit.day.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import bit.config.FixtureMonkeyConfig;
import bit.day.domain.Day;
import bit.day.repository.DayRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.type.TypeReference;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(FixtureMonkeyConfig.class)
@SpringBootTest
public class DayServiceTest {

    @Autowired
    private FixtureMonkey fixtureMonkey;
    @Mock
    private DayRepository dayRepository;
    @InjectMocks
    private DayService dayService;

    @DisplayName("디데이 조회 성공")
    @Test
    void getDaySuccessTest() {
        // given
        Optional<Day> optionalDday = fixtureMonkey.giveMeBuilder(new TypeReference<Optional<Day>>() {
        }).set("id", 1L).sample();
        when(dayRepository.findById(any())).thenReturn(optionalDday);

        // when
        Day day = dayService.getDay(1L);

        // then
        assertThat(day).usingRecursiveComparison().isEqualTo(optionalDday.get());
    }

//    @DisplayName("디데이 생성 성공")
//    @Test
//    void createDdaySuccessTest() throws Exception {
//        //given
//        Day dday = DdayFixtures.initialDday();
//        DayRequest ddayRequest = DdayRequestFixtures.of();
//        when(ddayRepository.save(any())).thenReturn(dday);
//
//        // when
//        Day newDday = ddayService.createDday(ddayRequest.toCommand());
//
//        // then
//        assertThat(newDday).usingRecursiveComparison().isEqualTo(dday);
//    }
//
//    @DisplayName("디데이 수정 성공")
//    @Test
//    void updateDdaySuccessTest() throws Exception {
//        // given
//        Day dday1 = DdayFixtures.initialDday();
//        Day dday2 = DdayFixtures.newDday();
//        DayRequest ddayRequest = DdayRequestFixtures.from(dday2);
//        when(ddayRepository.findById(any())).thenReturn(Optional.of(dday1));
//        when(ddayRepository.save(any())).thenReturn(dday2);
//
//        // when
//        Day newDday = ddayService.updateDday(dday1.getId(), ddayRequest.toCommand());
//
//        // then
//        assertThat(newDday)
//                .usingRecursiveComparison()
//                .isEqualTo(dday2);
//    }
//
//    @DisplayName("디데이 삭제 성공")
//    @Test
//    void deleteDdaySuccessTest() throws Exception {
//        // given
//        when(ddayRepository.existsById(any())).thenReturn(true);
//        // when
//        ddayService.deleteDday(1L);
//
//        // then
//        then(ddayRepository).should().existsById(any());
//        then(ddayRepository).should().deleteById(any());
//    }
}
