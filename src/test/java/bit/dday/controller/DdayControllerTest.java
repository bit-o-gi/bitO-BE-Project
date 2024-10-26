package bit.dday.controller;

import static bit.dday.controller.DdayController.DDAY_PATH;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bit.config.FixtureMonkeyConfig;
import bit.dday.domain.Dday;
import bit.dday.dto.DdayRequest;
import bit.dday.service.DdayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FailoverIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jackson.introspector.JacksonObjectArbitraryIntrospector;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
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

@WebMvcTest(controllers = DdayController.class)
@Import(FixtureMonkeyConfig.class)
class DdayControllerTest {

    @Autowired
    private FixtureMonkey fixtureMonkey;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DdayService ddayService;

    @DisplayName("디데이 조회 성공")
    @Test
    void getDdaySuccessTest() throws Exception {
        // given
        Dday dday = fixtureMonkey.giveMeBuilder(Dday.class).set("id", 1L).sample();
        when(ddayService.getDday(any())).thenReturn(dday);

        // when
        ResultActions result = mockMvc.perform(get(DDAY_PATH + "/" + dday.getId()));

        // then
        result.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(dday.getId()))
                .andExpect(jsonPath("$.title").value(dday.getTitle()))
                .andExpect(jsonPath("$.targetDate").value(dday.getTargetDate().toString()));
    }

    @DisplayName("디데이 생성 성공")
    @Test
    void createDdaySuccessTest() throws Exception {
        // given
        DdayRequest ddayRequest = fixtureMonkey.giveMeOne(DdayRequest.class);
        Dday newDday = fixtureMonkey.giveMeBuilder(Dday.class).set("id", null).sample();
        when(ddayService.createDday(any())).thenReturn(newDday);

        // when
        ResultActions result = mockMvc.perform(post(DDAY_PATH + "/new").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(ddayRequest)));

        // then
        result.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(newDday.getId()))
                .andExpect(jsonPath("$.title").value(newDday.getTitle()))
                .andExpect(jsonPath("$.targetDate").value(newDday.getTargetDate().toString()));
    }

    @DisplayName("디데이 수정 성공")
    @RepeatedTest(50)
    void updateDdaySuccessTest() throws Exception {
        // given
        DdayRequest ddayRequest = fixtureMonkey.giveMeOne(DdayRequest.class);
        Dday updatedDday = fixtureMonkey.giveMeBuilder(Dday.class).setNotNull("id")
                .sample();
        when(ddayService.updateDday(any(), any())).thenReturn(updatedDday);

        //when
        ResultActions result = mockMvc.perform(
                put(DDAY_PATH + "/" + updatedDday.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatedDday)));

        //then
        result.andDo(print())
                .andExpect(jsonPath("$.id").value(updatedDday.getId()))
                .andExpect(jsonPath("$.title").value(updatedDday.getTitle()))
                .andExpect(jsonPath("$.targetDate").value(updatedDday.getTargetDate().toString()));
    }
}
