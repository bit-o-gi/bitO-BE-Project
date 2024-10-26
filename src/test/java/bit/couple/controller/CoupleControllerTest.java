package bit.couple.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bit.couple.domain.Couple;
import bit.couple.domain.CoupleFixtures;
import bit.couple.dto.CoupleRequest;
import bit.couple.dto.CoupleRequestFixtures;
import bit.couple.service.CoupleService;
import bit.user.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = CoupleController.class)
class CoupleControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoupleService coupleService;

    @Test
    @DisplayName("커플 생성 성공")
    void createCouple() throws Exception {
        // given
        List<UserEntity> users = UserEntity.fromList(CoupleFixtures.initialUsers());
        CoupleRequest coupleRequest = CoupleRequestFixtures.of(users);
        doNothing().when(coupleService).createCouple(any());

        // when
        ResultActions result = mockMvc.perform(
                post(CoupleController.COUPLE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(coupleRequest))
        );

        // then
        result.andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("커플 승인 성공")
    void approveCouple() throws Exception {
        // given
        Couple couple = CoupleFixtures.initialCouple();
        doNothing().when(coupleService).approveCouple(any());

        // when
        ResultActions result = mockMvc.perform(put(CoupleController.COUPLE_PATH + "/" + couple.getId()));

        // then
        result.andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("커플 삭제 성공")
    void deleteCouple() throws Exception {
        // given
        Couple couple = CoupleFixtures.initialCouple();
        doNothing().when(coupleService).deleteCouple(any());

        // when
        ResultActions result = mockMvc.perform(delete(CoupleController.COUPLE_PATH + "/" + couple.getId()));

        // then
        result.andDo(print()).andExpect(status().isOk());
    }
}
