package bit.day.domain;

import bit.couple.domain.Couple;
import bit.day.dto.DayCommand;
import bit.schedule.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Day extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    private Couple couple;
    @NotNull
    private String title;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;

    @Builder
    private Day(Long id, Couple couple, String title, LocalDate startDate) {
        this.id = id;
        this.couple = couple;
        this.title = title;
        this.startDate = startDate;
    }

    public void update(DayCommand dayCommand) {
        this.title = dayCommand.title;
        this.startDate = dayCommand.startDate;
    }
}
