package bit.anniversary.sevice;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.anniversary.dto.AnDto;
import bit.anniversary.dto.AnResDto;
import bit.anniversary.entity.Anniversary;
import bit.anniversary.repository.AnRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnService {

	private final AnRepository anRepository;

	private final ModelMapper modelMapper;

	public AnDto saveAnniverSary(AnDto andto) {
		return anRepository.save(andto.creatAnniversary(modelMapper)).createAnniversary(modelMapper);
	}

	@Transactional
	public AnDto updateAnniverSary(AnDto anDto) {
		anRepository.findById(anDto.getId()).orElseThrow(EntityNotFoundException::new).update(anDto);
		return anDto;
	}

	// TODO: 추후 flyway 도입으로 crud에서 r 빼고 flyway에 책임을 넘길예정입니다.
	@Transactional
	public AnDto deleteAnniverSary(AnDto anDto) {
		anRepository.deleteById(anDto.getId());
		return anDto;
	}

	@Transactional(readOnly = true)
	public AnResDto getAnniverSary(AnDto anDto) {
		return AnResDto.of(
			anRepository.findById(anDto.getId()).orElseThrow(EntityNotFoundException::new).createAnniversary(modelMapper),modelMapper);
	}

	@Transactional(readOnly = true)
	public List<AnResDto> getAnniverSaryList() {
		List<AnResDto> anReqDtos = new ArrayList<>();
		anRepository.findAll().forEach(entity -> {
			anReqDtos.add(entity.createAnniversary(modelMapper).createAnReqDto(modelMapper));
		});
		return anReqDtos;
	}

	// NOTE: 현재 시간 <-> Dday 몇일 남았는지 계산하는 메서드
	private long getNowAnniverSary(LocalDateTime anniverSary) {
		LocalDateTime now = LocalDateTime.now();
		return ChronoUnit.DAYS.between(now, anniverSary);
	}

	// NOTE : 반복 기념일 계산
	private LocalDateTime calculateNextAnniversary(LocalDateTime anniversaryDate) {
		return anniversaryDate.plusYears(1);
	}

	// NOTE : 기념일 알림 기능
	public void sendAnniversaryNotifications() {
		List<AnResDto> anniversaryList = getAnniverSaryList();
		anniversaryList.forEach(anResDto -> {
			long daysLeft = getNowAnniverSary(anResDto.getAnniversaryDate());
			if (daysLeft == 7 || daysLeft == 1) {
				// 기념일 7일 전과 1일 전 알림 발송
				// TODO: WebScoket 활용해서 추가 예정
				// notificationService.sendNotification(anResDto);
			}
		});
	}

	// NOTE: 날짜 범위로 기념일 검색
	@Transactional(readOnly = true)
	public List<AnResDto> getAnniversariesBetween(LocalDateTime startDate, LocalDateTime endDate) {
		List<AnResDto> result = new ArrayList<>();
		anRepository.findAllByAnniversaryDateBetween(startDate, endDate)
			.forEach(entity -> result.add(entity.createAnniversary(modelMapper).createAnReqDto(modelMapper)));
		return result;
	}

	// NOTE:  사용자 정의 기념일 이벤트 처리
	public AnDto saveCustomAnniversary(AnDto anDto, String customEvent) {
		Anniversary entity = anRepository.save(anDto.creatAnniversary(modelMapper));
		// TODO: 커스텀 이벤트
		// entity.CustomEvent(customEvent);

		return entity.createAnniversary(modelMapper);
	}

	// NOTE: 이벤트 카운트 다운.
	@Transactional(readOnly = true)
	public Map<String, Long> getAnniversaryCountdown(Long anniversaryId) {
		Anniversary anniversary = anRepository.findById(anniversaryId)
			.orElseThrow(EntityNotFoundException::new);
		long daysRemaining = getNowAnniverSary(anniversary.createAnniversary(modelMapper).getAnniversaryDate());
		return Map.of("daysRemaining", daysRemaining);
	}

}
