package bit.anniversary.entity;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import bit.anniversary.dto.AnDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	private String writeTime;  // DB의 write_time 필드와 일치

	private String updateTime; // DB의 update_time 필드와 일치

	private String title;

	private String writer;

	private String withPeople;

	private String content;

	private LocalDateTime anniversaryDate;  // DB의 anniversary_date 필드와 일치

	// 엔티티를 DTO로 변환하는 메서드
	public AnDto createAnniversary(ModelMapper modelMapper) {
		return modelMapper.map(this, AnDto.class);
	}

	// DTO에서 엔티티를 업데이트하는 메서드
	public void update(AnDto anDto) {
		this.writer = anDto.getWriter();
		this.title = anDto.getTitle();
		this.withPeople = anDto.getWithPeople();
		this.content = anDto.getContent();
	}
}
