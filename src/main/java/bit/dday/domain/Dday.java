package bit.dday.domain;

import bit.couple.domain.Couple;
import bit.dday.dto.DdayCommand;
import bit.schedule.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Dday extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    private Couple couple;
    @NotNull
    private String title;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime targetDate;

    @Builder
    private Dday(Couple couple, String title, LocalDateTime targetDate) {
        this.couple = couple;
        this.title = title;
        this.targetDate = targetDate;
    }

    public void update(DdayCommand ddayCommand) {
        this.title = ddayCommand.title;
        this.targetDate = ddayCommand.targetDate;
    }
}
