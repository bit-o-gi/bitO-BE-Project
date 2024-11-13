package bit.anniversary.entity;

import lombok.*;
import org.modelmapper.ModelMapper;
import bit.anniversary.dto.AnDto;
import bit.user.entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anniversary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String writeTime;
	private String updateTime;
	private String title;
	private String content;
	private LocalDateTime anniversaryDate;

	@ManyToOne
	@JoinColumn(name = "writer_id")
	private UserEntity writer;

	@ManyToOne
	@JoinColumn(name = "with_people_id")
	private UserEntity withPeople;

	public AnDto toDto(ModelMapper modelMapper) {
		return modelMapper.map(this, AnDto.class);
	}

	public void updateAnniversary(AnDto anDto, UserEntity writer, UserEntity withPeople) {
		this.title = anDto.getTitle();
		this.content = anDto.getContent();
		this.updateTime = anDto.getUpdateTime();
		this.writer = writer;
		this.withPeople = withPeople;
	}

	public long calculateDaysToAnniversary() {
		return ChronoUnit.DAYS.between(LocalDateTime.now(), this.anniversaryDate);
	}

	public LocalDateTime calculateNextAnniversary() {
		return this.anniversaryDate.plusYears(1);
	}
}
