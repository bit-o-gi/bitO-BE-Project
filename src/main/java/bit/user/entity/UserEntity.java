package bit.user.entity;

import bit.base.BaseEntity;
import bit.couple.domain.Couple;
import bit.user.domain.User;
import bit.user.oauth.enums.OauthPlatformStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "bit_o_user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String nickName;

    private String gender;

    @Enumerated(EnumType.STRING)
    private OauthPlatformStatus platform;

    private LocalDateTime registerDate;

    @ManyToOne
    @JoinColumn(name = "couple_id")
    private Couple couple;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.email = user.getEmail();
        userEntity.nickName = user.getNickName();
        userEntity.platform = user.getPlatform();
        userEntity.registerDate = user.getRegisterDate();
        userEntity.couple = user.getCouple();
        return userEntity;
    }

    public User toDomain() {
        return User.builder()
                .id(id)
                .email(email)
                .nickName(nickName)
                .gender(gender)
                .platform(platform)
                .registerDate(registerDate)
                .couple(couple)
                .build();
    }
}
