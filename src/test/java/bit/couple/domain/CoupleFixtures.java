package bit.couple.domain;

import bit.user.domain.User;
import bit.user.oauth.OauthPlatformStatus;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

public class CoupleFixtures {
    public static Couple initialCouple() throws Exception {

        Couple couple = Couple.of(initialUsers());

        Class<?> clazz = Couple.class;
        Field idField = clazz.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(couple, 3L);
        return couple;
    }

    public static List<User> initialUsers() {
        User user1 = User.builder()
                .id(1L)
                .email("email1@naver.com")
                .nickName("nickname1")
                .gender("남성")
                .platform(OauthPlatformStatus.KAKAO)
                .registerDate(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .id(2L)
                .email("email2@naver.com")
                .nickName("nickname2")
                .gender("여성")
                .platform(OauthPlatformStatus.KAKAO)
                .registerDate(LocalDateTime.now())
                .build();
        return List.of(user1, user2);
    }
}
