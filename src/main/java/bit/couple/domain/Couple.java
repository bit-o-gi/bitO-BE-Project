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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Couple extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // ToDO. 추후 프론트에서 구별해서 줘야할듯 함
  private String sender;

  private String receiver;

  private CoupleStatus status;

  @Builder
  public static Couple of() {
    Couple couple = new Couple();
    couple.status = CoupleStatus.CREATING;
    return couple;
  }

  public void approve() {
    this.status = CoupleStatus.APPROVED;
  }
}
