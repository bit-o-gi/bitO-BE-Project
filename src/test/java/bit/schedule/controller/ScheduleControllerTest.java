package bit.schedule.controller;

import bit.schedule.domain.Schedule;
import bit.schedule.dto.ScheduleRequest;
import bit.schedule.dto.ScheduleResponse;
import bit.schedule.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static bit.schedule.util.ScheduleFixture.getNewSchedule;
import static bit.schedule.util.ScheduleRequestFixture.getNewScheduleRequest;
import static bit.schedule.util.ScheduleRequestFixture.getNewScheduleRequests;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ScheduleController.class)
class ScheduleControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @DisplayName("스케줄 ID로 조회 테스트")
    @Test
    void getByScheduleIdTest() throws Exception {
        //Given
        Schedule schedule = getNewSchedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        ScheduleResponse response = new ScheduleResponse(schedule);
        when(scheduleService.getSchedule(0L)).thenReturn(response);
        //When
        //Then
        mockMvc.perform(
                        get("/api/v1/schedule/0")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @DisplayName("스케줄 유저 ID로 조회 테스트")
    @Test
    void getByUserIdTest() throws Exception {
        //Given
        Schedule schedule = getNewSchedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        List<ScheduleResponse> response = IntStream.range(0, 10)
                .mapToObj(i -> new ScheduleResponse(schedule))
                .toList();
        when(scheduleService.getSchedulesByUserId(0L)).thenReturn(response);
        //When
        //Then
        mockMvc.perform(
                        get("/api/v1/schedule/user/0")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @DisplayName("스케줄 저장 테스트")
    @Test
    void saveTest() throws Exception {
        //Given
        Schedule schedule = getNewSchedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        ScheduleRequest request = getNewScheduleRequest(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        ScheduleResponse response = new ScheduleResponse(schedule);
        when(scheduleService.saveSchedule(any(ScheduleRequest.class))).thenReturn(response);
        //When
        //Then
        mockMvc.perform(
                        post("/api/v1/schedule")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }


    @DisplayName("스케줄 저장시 필수 요소가 없으면 에러가 발생한다.")
    @Test
    void saveValidTest() {
        //Given
        Schedule schedule = getNewSchedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        ScheduleResponse response = new ScheduleResponse(schedule);
        when(scheduleService.saveSchedule(any(ScheduleRequest.class))).thenReturn(response);
        List<ScheduleRequest> scheduleRequests = getNewScheduleRequests();
        //When
        //Then
        scheduleRequests.forEach((request) -> {
            try {
                mockMvc.perform(
                                post("/api/v1/schedule")
                                        .contentType("application/json")
                                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isBadRequest())
                        .andDo(print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @DisplayName("스케줄 수정 테스트")
    @Test
    void patchTest() throws Exception {
        //Given
        Schedule schedule = getNewSchedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        ScheduleRequest request = getNewScheduleRequest(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        ScheduleResponse response = new ScheduleResponse(schedule);
        when(scheduleService.updateSchedule(eq(0L), any(ScheduleRequest.class))).thenReturn(response);
        //When
        //Then
        mockMvc.perform(
                        put("/api/v1/schedule/0")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @DisplayName("스케줄 삭제 테스트")
    @Test
    void deleteTest() throws Exception {
        //Given
        Schedule schedule = getNewSchedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        ScheduleResponse response = new ScheduleResponse(schedule);
        when(scheduleService.deleteSchedule(0L)).thenReturn(response);
        //When
        //Then
        mockMvc.perform(
                        delete("/api/v1/schedule/0")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}