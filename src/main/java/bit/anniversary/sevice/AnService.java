package bit.anniversary.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.anniversary.dto.AnDto;
import bit.anniversary.dto.AnReqDto;
import bit.anniversary.dto.AnResDto;
import bit.anniversary.entity.Anniversary;
import bit.anniversary.repository.AnRepository;
import bit.user.dto.UserResponse;
import bit.user.entity.UserEntity;
import bit.user.repository.UserJpaRepository;
import bit.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnService {

	private final AnRepository anRepository;
	private final UserJpaRepository userRepository;
	private final ModelMapper modelMapper;

	// 기념일 생성
	public AnResDto createAnniversary(AnReqDto anReqDto) {
		UserEntity writer = userRepository.findByEmail(anReqDto.getWriterEmail())
			.orElseThrow(() -> new EntityNotFoundException("Writer not found"));
		UserEntity withPeople = userRepository.findByEmail(anReqDto.getWithPeopleEmail())
			.orElseThrow(() -> new EntityNotFoundException("WithPeople not found"));

		AnDto anDto = modelMapper.map(anReqDto, AnDto.class);
		Anniversary anniversary = anDto.createAnniversary(modelMapper);
		anniversary.updateAnniversary(anDto, writer, withPeople);
		anRepository.save(anniversary);

		UserResponse writerRes = UserResponse.from(writer.toModel());
		UserResponse withPeopleRes = UserResponse.from(withPeople.toModel());

		return AnResDto.from(anDto, writerRes, withPeopleRes, anniversary.calculateDaysToAnniversary());
	}

	// 기념일 업데이트
	@Transactional
	public AnResDto updateAnniversary(Long id, AnReqDto anReqDto) {
		Anniversary anniversary = anRepository.findById(id).orElseThrow(EntityNotFoundException::new);

		UserEntity writer = userRepository.findByEmail(anReqDto.getWriterEmail())
			.orElseThrow(() -> new EntityNotFoundException("Writer not found"));
		UserEntity withPeople = userRepository.findByEmail(anReqDto.getWithPeopleEmail())
			.orElseThrow(() -> new EntityNotFoundException("WithPeople not found"));

		AnDto anDto = modelMapper.map(anReqDto, AnDto.class);
		anniversary.updateAnniversary(anDto, writer, withPeople);

		UserResponse writerRes = UserResponse.from(writer.toModel());
		UserResponse withPeopleRes = UserResponse.from(withPeople.toModel());

		return AnResDto.from(anDto, writerRes, withPeopleRes, anniversary.calculateDaysToAnniversary());
	}

	// 기념일 삭제
	@Transactional
	public void deleteAnniversary(Long id) {
		anRepository.deleteById(id);
	}

	// 특정 기념일 조회
	@Transactional(readOnly = true)
	public AnResDto getAnniversary(Long id) {
		Anniversary anniversary = anRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return AnResDto.from(
			anniversary.toDto(modelMapper),
			UserResponse.from(anniversary.getWriter().toModel()),
			UserResponse.from(anniversary.getWithPeople().toModel()),
			anniversary.calculateDaysToAnniversary()
		);
	}

	// 날짜 범위로 기념일 검색
	@Transactional(readOnly = true)
	public List<AnResDto> findAnniversariesInRange(LocalDateTime startDate, LocalDateTime endDate) {
		return anRepository.findAllByAnniversaryDateBetween(startDate, endDate)
			.stream()
			.map(anniversary -> AnResDto.from(
				anniversary.toDto(modelMapper),
				UserResponse.from(anniversary.getWriter().toModel()),
				UserResponse.from(anniversary.getWithPeople().toModel()),
				anniversary.calculateDaysToAnniversary()
			))
			.collect(Collectors.toList());
	}

	// 사용자 정의 이벤트 추가
	@Transactional
	public void addCustomEvent(Long id, String customEvent) {
		Anniversary anniversary = anRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		// Logic to add custom event based on customEvent
	}

	// 알림 전송 (UDP)
	public void sendAnniversaryNotification(AnResDto anniversary, String message) throws Exception {
		try (DatagramSocket socket = new DatagramSocket()) {
			byte[] buffer = message.getBytes();
			InetAddress address = InetAddress.getByName("localhost");
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9876);
			socket.send(packet);
		}
	}

	// 반복 기념일 계산 (다음 기념일)
	public LocalDateTime calculateNextAnniversary(Long id) {
		Anniversary anniversary = anRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return anniversary.calculateNextAnniversary();
	}
}
