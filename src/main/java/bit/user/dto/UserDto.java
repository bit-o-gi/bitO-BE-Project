package bit.user.dto;

import bit.user.domain.User;
import bit.user.oauth.OauthPlatformStatus;
import bit.user.oauth.kakao.domain.KakaoUserInfo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private final Long id;

    private final String email;

    private final String nickName;

    private final String gender;

    private final OauthPlatformStatus platform;

    private final LocalDateTime registerDate;

    @Builder
    public UserDto(Long id, String email, String nickName, String gender, OauthPlatformStatus platform,
                   LocalDateTime registerDate) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.gender = gender;
        this.platform = platform;
        this.registerDate = registerDate;
    }

    public static UserDto fromKakaoUser(KakaoUserInfo info) {
        return UserDto.builder()
                .email(info.getEmail())
                .nickName(info.getNickname())
                .platform(OauthPlatformStatus.KAKAO)
                .registerDate(info.getConnectedAt())
                .build();
    }

    public static UserDto fromUser(User userInfo) {
        return UserDto.builder()
                .id(userInfo.getId())
                .email(userInfo.getEmail())
                .nickName(userInfo.getNickName())
                .build();
    }
}
