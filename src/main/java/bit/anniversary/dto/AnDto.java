package bit.anniversary.dto;

import lombok.Builder;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import bit.anniversary.entity.Anniversary;
import lombok.Setter;

@Getter
@Builder
public class AnDto {

	private final Long id;
	private final String writerEmail;
	private final String withPeopleEmail;
	private final String writeTime;
	private final String title;
	private final String updateTime;
	private final String content;
	private final String anniversaryDate;

	public Anniversary toEntity(ModelMapper modelMapper) {
		return modelMapper.map(this, Anniversary.class);
	}

	public static AnDto fromEntity(Anniversary anniversary, ModelMapper modelMapper) {
		return modelMapper.map(anniversary, AnDto.class);
	}
}
