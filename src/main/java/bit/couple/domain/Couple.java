package bit.couple.domain;

import bit.dday.domain.Dday;
import bit.schedule.domain.BaseEntity;
import bit.user.domain.User;
import bit.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Couple extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "couple")
    private List<UserEntity> users;

    @OneToOne(mappedBy = "couple")
    private Dday dday;

    private CoupleStatus status;

    public static Couple of(List<User> users) {
        Couple couple = new Couple();
        couple.users = UserEntity.fromList(users);
        couple.status = CoupleStatus.CREATING;
        return couple;
    }

    public void approve() {
        this.status = CoupleStatus.APPROVED;
    }
}
