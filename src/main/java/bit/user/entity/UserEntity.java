package bit.user.entity;

import bit.couple.domain.Couple;
import bit.schedule.domain.BaseEntity;
import bit.user.domain.User;
import bit.user.oauth.OauthPlatformStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
@Table(name = "APPUSER")
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

    public static List<UserEntity> fromList(List<User> users) {
        return users.stream()
                .map(UserEntity::from)
                .toList();
    }

    public User toModel() {
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
