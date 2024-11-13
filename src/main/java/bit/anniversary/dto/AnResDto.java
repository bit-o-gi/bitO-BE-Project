package bit.anniversary.dto;

import java.time.LocalDateTime;

import bit.user.dto.UserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnResDto {

	private Long id;
	private String writeTime;
	private String title;
	private String updateTime;
	private String content;
	private LocalDateTime anniversaryDate;
	private UserResponse writer;
	private UserResponse withPeople;
	private long daysToAnniversary;

	public static AnResDto from(AnDto anDto, UserResponse writer, UserResponse withPeople, long daysToAnniversary) {
		AnResDto resDto = new AnResDto();
		resDto.id = anDto.getId();
		resDto.writeTime = anDto.getWriteTime();
		resDto.title = anDto.getTitle();
		resDto.updateTime = anDto.getUpdateTime();
		resDto.content = anDto.getContent();
		resDto.anniversaryDate = anDto.getAnniversaryDate();
		resDto.writer = writer;
		resDto.withPeople = withPeople;
		resDto.daysToAnniversary = daysToAnniversary;
		return resDto;
	}
}
