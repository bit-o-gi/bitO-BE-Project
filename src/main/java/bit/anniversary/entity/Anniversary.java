package bit.anniversary.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.modelmapper.ModelMapper;

import bit.anniversary.dto.AnDto;
import bit.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

	// 기념일 DTO 생성 메서드
	public AnDto toDto(ModelMapper modelMapper) {
		return modelMapper.map(this, AnDto.class);
	}

	// 기념일 업데이트
	public void updateAnniversary(AnDto anDto, UserEntity writer, UserEntity withPeople) {
		this.title = anDto.getTitle();
		this.content = anDto.getContent();
		this.updateTime = anDto.getUpdateTime();
		this.writer = writer;
		this.withPeople = withPeople;
	}

	// D-Day 계산
	public long calculateDaysToAnniversary() {
		return ChronoUnit.DAYS.between(LocalDateTime.now(), this.anniversaryDate);
	}

	// 다음 반복 기념일 계산 (예: 매년 반복되는 기념일)
	public LocalDateTime calculateNextAnniversary() {
		return this.anniversaryDate.plusYears(1);
	}
}
