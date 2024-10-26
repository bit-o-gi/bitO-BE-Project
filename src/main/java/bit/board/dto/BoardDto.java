package bit.board.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bit.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BoardDto {
	// TODO : 글쓴 날짜 , 유저 정보 , 필요
	private Long id;

	private String title;

	private String content;

	private String writer;

	private static ModelMapper modelMapper;

	@Autowired
	private BoardDto(ModelMapper modelMapper) {
		BoardDto.modelMapper = modelMapper;
	}

	public Board creatBoard() {
		return modelMapper.map(this, Board.class);
	}

	public static BoardDto of(Board board) {
		return modelMapper.map(board, BoardDto.class);
	}
}
