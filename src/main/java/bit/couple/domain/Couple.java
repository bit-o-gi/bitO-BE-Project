package bit.couple.domain;

import bit.schedule.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Couple extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;

    private String receiver;

    @Enumerated(EnumType.STRING)
    private CoupleStatus status;

    public static Couple of(String sender, String receiver, CoupleStatus status) {
        return Couple.builder()
                .sender(sender)
                .receiver(receiver)
                .status(status)
                .build();
    }

    public void approve() {
        this.status = CoupleStatus.APPROVED;
    }
}
