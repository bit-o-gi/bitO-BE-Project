package bit.anniversary.dto;

import lombok.*;
import org.modelmapper.ModelMapper;

import bit.anniversary.entity.Anniversary;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class AnDto {

	private Long id;
	private String writeTime;
	private String title;
	private String updateTime;
	private String content;
	private LocalDateTime anniversaryDate;

	// DTO에서 Entity로 변환하는 메서드
	public Anniversary createAnniversary(ModelMapper modelMapper) {
		return modelMapper.map(this, Anniversary.class);
	}
}
