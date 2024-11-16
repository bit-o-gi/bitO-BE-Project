package bit.anniversary.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import bit.anniversary.dto.AnReqDto;
import bit.anniversary.dto.AnResDto;
import bit.anniversary.service.AnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AnController {

	private final AnService anniversaryService;

	// 기념일 생성
	@MutationMapping
	public AnResDto createAnniversary(@Argument("anDto") AnReqDto anReqDto) {
		log.info("anReqDto ==> {}",anReqDto);
		return anniversaryService.createAnniversary(anReqDto);
	}

	// 기념일 업데이트
	@MutationMapping
	public AnResDto updateAnniversary(@Argument("id") Long id, @Argument("anDto") AnReqDto anReqDto) {
		return anniversaryService.updateAnniversary(id, anReqDto);
	}

	// 기념일 삭제
	@MutationMapping
	public Boolean deleteAnniversary(@Argument("id") Long id) {
		anniversaryService.deleteAnniversary(id);
		return true;
	}

	// 특정 기념일 조회
	@QueryMapping
	public AnResDto getAnniversary(@Argument Long id) {
		return anniversaryService.getAnniversary(id);
	}

	// 날짜 범위로 기념일 목록 조회
	@QueryMapping
	public List<AnResDto> getAnniversariesInRange(@Argument LocalDateTime startDate, @Argument LocalDateTime endDate) {
		return anniversaryService.findAnniversariesInRange(startDate, endDate);
	}

	// 한 달 기념일 목록 조회
	@QueryMapping
	public List<AnResDto> getMonthlyAnniversaries() {
		return anniversaryService.findAnniversariesInRange(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
	}

	// 1년 기념일 목록 조회
	@QueryMapping
	public List<AnResDto> getYearlyAnniversaries() {
		return anniversaryService.findAnniversariesInRange(LocalDateTime.now(), LocalDateTime.now().plusYears(1));
	}
}
