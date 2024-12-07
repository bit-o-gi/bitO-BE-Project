package bit.user.domain;

import bit.couple.domain.Couple;
import bit.user.dto.UserCreateRequest;
import bit.user.oauth.OauthPlatformStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private final Long id;
    private final String email;
    private final String nickName;
    private final String gender;
    private final OauthPlatformStatus platform;
    private final LocalDateTime registerDate;
    private final Couple couple;

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

    public static User from(UserCreateRequest userCreateRequest) {
        return User.builder()
                .email(userCreateRequest.getEmail())
                .nickName(userCreateRequest.getNickName())
                .gender(userCreateRequest.getGender())
                .platform(userCreateRequest.getPlatform())
                .registerDate(userCreateRequest.getRegisterDate())
                .build();
    }

    public User updateCouple(Couple couple) {
        return User.builder()
                .id(this.id)
                .email(this.getEmail())
                .nickName(this.getNickName())
                .gender(this.getGender())
                .platform(this.getPlatform())
                .registerDate(this.getRegisterDate())
                .couple(couple)
                .build();
    }

}
