package bit.couple.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bit.couple.domain.Couple;
import bit.couple.dto.CoupleRequest;
import bit.couple.testFixtures.CoupleFixtures;
import bit.couple.service.CoupleService;
import bit.user.domain.User;
import bit.user.dto.UserDto;
import bit.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = CoupleController.class)
@ActiveProfiles({"test", "secret"})
class CoupleControllerTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CoupleService coupleService;

  @MockBean
  private UserService userService;

  @Test
  @DisplayName("커플 생성 성공")
  void createCouple() throws Exception {
    // given
    List<User> users = CoupleFixtures.initialUsers();

    userService.create(UserDto.fromUser(users.get(0)));
    userService.create(UserDto.fromUser(users.get(1)));

    String receiverEmail = users.get(0).getEmail();
    String senderEmail = users.get(1).getEmail();

    CoupleRequest coupleRequest = makeCoupleRequest(receiverEmail, senderEmail);

    // when
    ResultActions result = mockMvc.perform(
        post(CoupleController.COUPLE_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(coupleRequest))
    );

    // then
    result.andDo(print())
        .andExpect(status().isCreated());
  }

  private CoupleRequest makeCoupleRequest(String receiverEmail, String senderEmail) {
    return CoupleRequest.builder()
        .receiverEmail(receiverEmail)
        .senderEmail(senderEmail)
        .build();
  }

  @Test
  @DisplayName("커플 승인 성공")
  void approveCouple() throws Exception {
    // given
    Couple couple = CoupleFixtures.initialCouple();
    doNothing().when(coupleService).approveCouple(any());

    // when
    ResultActions result = mockMvc.perform(
        put(CoupleController.COUPLE_PATH + "/" + couple.getId()));

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
    ResultActions result = mockMvc.perform(
        delete(CoupleController.COUPLE_PATH + "/" + couple.getId()));

    // then
    result.andDo(print()).andExpect(status().isOk());
  }
}
