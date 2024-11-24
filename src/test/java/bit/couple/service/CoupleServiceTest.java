package bit.couple.service;

import static org.assertj.core.api.Assertions.assertThat;

import bit.couple.dto.CoupleRequest;
import bit.couple.testFixtures.CoupleFixtures;
import bit.user.domain.User;
import bit.user.dto.UserDto;
import bit.user.service.UserServiceImpl;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
class CoupleServiceTest {

    @Autowired
    CoupleService coupleService;

    @Autowired
    UserServiceImpl userService;


    @Transactional
    @DisplayName("커플이 저장될 때, 유저의 정보가 update 되는지 확인한다.")
    @Test
    void createCoupleAndUserUpdate() {
        // given
        List<User> users = CoupleFixtures.initialUsers();

        userService.create(UserDto.fromUser(users.get(0)));
        userService.create(UserDto.fromUser(users.get(1)));

        String senderEmail = users.get(0).getEmail();
        String receiverEmail = users.get(1).getEmail();

        // when
        CoupleRequest coupleRequest = new CoupleRequest(senderEmail, receiverEmail);
        coupleService.createCouple(coupleRequest.toCommand());
        User user = userService.getByEmail(senderEmail);

        // then
        assertThat(user.getCouple()).isNotNull();
    }

}