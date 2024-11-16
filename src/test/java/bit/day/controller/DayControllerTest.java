package bit.day.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bit.config.FixtureMonkeyConfig;
import bit.day.domain.Day;
import bit.day.dto.DayRequest;
import bit.day.service.DayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = DayController.class)
@Import(FixtureMonkeyConfig.class)
class DayControllerTest {

    private final String DDAY_PATH = "/api/v1/day";
    @Autowired
    private FixtureMonkey fixtureMonkey;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DayService dayService;

    @DisplayName("디데이 조회 성공")
    @Test
    void getDdaySuccessTest() throws Exception {
        // given
        Day day = fixtureMonkey.giveMeBuilder(Day.class).set("id", 1L).sample();
        when(dayService.getDay(any())).thenReturn(day);

        // when
        ResultActions result = mockMvc.perform(get(DDAY_PATH + "/" + day.getId()));

        // then
        result.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(day.getId()))
                .andExpect(jsonPath("$.title").value(day.getTitle()))
                .andExpect(jsonPath("$.targetDate").value(day.getStartDate().toString()));
    }

    @DisplayName("디데이 생성 성공")
    @Test
    void createDdaySuccessTest() throws Exception {
        // given
        DayRequest dayRequest = fixtureMonkey.giveMeOne(DayRequest.class);
        Day newDay = fixtureMonkey.giveMeBuilder(Day.class).set("id", null).sample();
        when(dayService.createDay(any())).thenReturn(newDay);

        // when
        ResultActions result = mockMvc.perform(post(DDAY_PATH + "/new").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dayRequest)));

        // then
        result.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(newDay.getId()))
                .andExpect(jsonPath("$.title").value(newDay.getTitle()))
                .andExpect(jsonPath("$.targetDate").value(newDay.getStartDate().toString()));
    }

    @DisplayName("디데이 수정 성공")
    @RepeatedTest(50)
    void updateDdaySuccessTest() throws Exception {
        // given
        DayRequest dayRequest = fixtureMonkey.giveMeOne(DayRequest.class);
        Day updatedDay = fixtureMonkey.giveMeBuilder(Day.class).setNotNull("id")
                .sample();
        when(dayService.updateDay(any(), any())).thenReturn(updatedDay);

        //when
        ResultActions result = mockMvc.perform(
                put(DDAY_PATH + "/" + updatedDay.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatedDay)));

        //then
        result.andDo(print())
                .andExpect(jsonPath("$.id").value(updatedDay.getId()))
                .andExpect(jsonPath("$.title").value(updatedDay.getTitle()))
                .andExpect(jsonPath("$.targetDate").value(updatedDay.getStartDate().toString()));
    }
}
