package bit.anniversary.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import bit.anniversary.dto.AnDto;
import bit.anniversary.dto.AnResDto;
import bit.anniversary.sevice.AnService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AnController {

	private final AnService anniversaryservice;

	private final ModelMapper modelMapper;
	// 	TODO: 기념일 설정 기능
	// 	TODO: 함께 하는 사람 , 글쓴이 회원가입 이후 구현.
	@MutationMapping
	public AnResDto createAnniversary(@Argument("anDto") AnDto anDto) {
		System.out.println("Received anDto: " + anDto);
		if (anDto == null) {
			throw new IllegalArgumentException("anDto cannot be null");
		}
		return anniversaryservice.saveAnniverSary(anDto).createAnReqDto(modelMapper);
	}

	//	TODO: 기념일 업데이트 기능
	// 	Input : 기념일 시간 , 입력 시간 , 수정 시간 , 기념일 제목 ,글쓴이 , 함께 하는 사람 ,기념일 내용
	// 	TODO: 함께 하는 사람 , 글쓴이 회원가입 이후 구현
	@MutationMapping
	public AnResDto updateAnniversary(@Argument AnDto andto) {
		return anniversaryservice.updateAnniverSary(andto).createAnReqDto(modelMapper);
	}

	//	TODO: 기념일 삭제 기능
	//  Input : 기념일 id
	//  TODO:
	@MutationMapping
	public AnResDto deleteAnniversary(@Argument AnDto andto) {
		anniversaryservice.deleteAnniverSary(andto);
		return andto.createAnReqDto(modelMapper);
	}

	//  TODO: 기념일 가져오는기능
	@QueryMapping
	public AnResDto getAnniversary(@Argument AnDto andto) {
		return anniversaryservice.getAnniverSary(andto);
	}

	// TODO : 기념일 리스트 가져오는기능
	@QueryMapping
	public List<AnResDto> getListAnniversary() {
		return anniversaryservice.getAnniverSaryList();
	}

}
