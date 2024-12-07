package bit.user.dto;

import bit.user.domain.User;
import bit.user.oauth.OauthPlatformStatus;
import bit.user.oauth.kakao.domain.KakaoUserInfo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateRequest {
    private final Long id;

    private final String email;

    private final String nickName;

    private final String gender;

    private final OauthPlatformStatus platform;

    private final LocalDateTime registerDate;

    @Builder
    public UserCreateRequest(Long id, String email, String nickName, String gender, OauthPlatformStatus platform,
                             LocalDateTime registerDate) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.gender = gender;
        this.platform = platform;
        this.registerDate = registerDate;
    }

    public static UserCreateRequest fromKakaoUser(KakaoUserInfo info) {
        return UserCreateRequest.builder()
                .email(info.getEmail())
                .nickName(info.getNickname())
                .platform(OauthPlatformStatus.KAKAO)
                .registerDate(info.getConnectedAt())
                .build();
    }

    public static UserCreateRequest fromUser(User userInfo) {
        return UserCreateRequest.builder()
                .id(userInfo.getId())
                .email(userInfo.getEmail())
                .nickName(userInfo.getNickName())
                .build();
    }
}
