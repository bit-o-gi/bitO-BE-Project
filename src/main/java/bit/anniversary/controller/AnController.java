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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AnController {

	private final AnService anniversaryService;

	//NOTE: 기념일 생성
	@Operation(summary = "기념일 생성", description = "새로운 기념일을 생성합니다.")
	@MutationMapping
	public AnResDto createAnniversary(@Parameter(description = "기념일 요청 데이터") @Argument("anDto") AnReqDto anReqDto) {
		log.info("anReqDto ==> {}",anReqDto);
		return anniversaryService.createAnniversary(anReqDto);
	}

	//NOTE: 기념일 업데이트
	@Operation(summary = "기념일 수정", description = "특정 ID를 가진 기념일을 수정합니다.")
	@MutationMapping
	public AnResDto updateAnniversary(@Parameter(description = "수정할 기념일 ID") @Argument("id") Long id, @Parameter(description = "수정된 기념일 데이터")  @Argument("anDto") AnReqDto anReqDto) {
		return anniversaryService.updateAnniversary(id, anReqDto);
	}

	//NOTE: 기념일 삭제
	@Operation(summary = "기념일 삭제", description = "특정 ID를 가진 기념일을 삭제합니다.")
	@MutationMapping
	public Boolean deleteAnniversary(@Parameter(description = "삭제할 기념일 ID") @Argument("id") Long id) {
		anniversaryService.deleteAnniversary(id);
		return true;
	}

	//NOTE: 특정 기념일 조회
	@Operation(summary = "특정 기념일 조회", description = "특정 ID를 가진 기념일 정보를 조회합니다.")
	@QueryMapping
	public AnResDto getAnniversary(@Argument Long id) {
		return anniversaryService.getAnniversary(id);
	}

	//NOTE: 날짜 범위로 기념일 목록 조회
	@Operation(summary = "날짜 범위로 기념일 조회", description = "특정 날짜 범위 내의 기념일 목록을 조회합니다.")
	@QueryMapping
	public List<AnResDto> getAnniversariesInRange(@Parameter(description = "조회 시작 날짜")  @Argument LocalDateTime startDate, @Parameter(description = "조회 종료 날짜") @Argument LocalDateTime endDate) {
		return anniversaryService.findAnniversariesInRange(startDate, endDate);
	}

	//NOTE: 한 달 기념일 목록 조회
	@Operation(summary = "한 달 기념일 조회", description = "앞으로 한 달 동안의 기념일 목록을 조회합니다.")
	@QueryMapping
	public List<AnResDto> getMonthlyAnniversaries() {
		return anniversaryService.findAnniversariesInRange(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
	}

	//NOTE: 1년 기념일 목록 조회
	@Operation(summary = "1년 기념일 조회", description = "앞으로 1년 동안의 기념일 목록을 조회합니다.")
	@QueryMapping
	public List<AnResDto> getYearlyAnniversaries() {
		return anniversaryService.findAnniversariesInRange(LocalDateTime.now(), LocalDateTime.now().plusYears(1));
	}
}
