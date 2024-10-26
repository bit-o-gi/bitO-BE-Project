package bit.board.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Component
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardReqDto {
	// TODO : 글쓴 날짜 , 유저 정보 , 필요
	private Long id;

	private String title;

	private String content;

	private static ModelMapper modelMapper;

	@Autowired
	public BoardReqDto(ModelMapper modelMapper) {
		BoardReqDto.modelMapper = modelMapper; // 수정된 부분
	}

	public static BoardReqDto of(BoardDto boardDto) {
		return modelMapper.map(boardDto, BoardReqDto.class);
	}
}
