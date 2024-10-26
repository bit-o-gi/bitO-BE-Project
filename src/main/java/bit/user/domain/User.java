package bit.user.domain;

import bit.couple.domain.Couple;
import bit.user.dto.UserDto;
import bit.user.oauth.OauthPlatformStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
    private final Long id;
    private final String email;
    private final String nickName;
    private final String gender;
    private final OauthPlatformStatus platform;
    private final LocalDateTime registerDate;
    private Couple couple;

    @Builder
    public User(Long id, String email, String nickName, String gender, OauthPlatformStatus platform,
                LocalDateTime registerDate, Couple couple) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.gender = gender;
        this.platform = platform;
        this.registerDate = registerDate;
        this.couple = couple;
    }

    public static User from(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .nickName(userDto.getNickName())
                .gender(userDto.getGender())
                .platform(userDto.getPlatform())
                .registerDate(userDto.getRegisterDate())
                .build();
    }

    public void updateCouple(Couple couple) {
        this.couple = couple;
    }

}
