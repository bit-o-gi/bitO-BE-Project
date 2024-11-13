package bit.anniversary.service;

import bit.anniversary.dto.AnDto;
import bit.anniversary.dto.AnReqDto;
import bit.anniversary.dto.AnResDto;
import bit.anniversary.entity.Anniversary;
import bit.anniversary.repository.AnRepository;
import bit.user.domain.User;
import bit.user.entity.UserEntity;
import bit.user.oauth.OauthPlatformStatus;
import bit.user.repository.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnServiceTest {

	@InjectMocks
	private AnService anService;

	@Mock
	private AnRepository anRepository;

	@Mock
	private UserJpaRepository userRepository;

	@Mock
	private ModelMapper modelMapper;

	private UserEntity writerEntity;
	private UserEntity withPeopleEntity;
	private AnReqDto anReqDto;
	private AnDto anDto;
	private Anniversary anniversary;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// User 객체 생성 후 UserEntity로 변환
		User writer = User.builder()
			.id(1L)
			.email("writer@example.com")
			.nickName("Writer")
			.platform(OauthPlatformStatus.KAKAO)
			.registerDate(LocalDateTime.now())
			.build();
		User withPeople = User.builder()
			.id(2L)
			.email("withpeople@example.com")
			.nickName("WithPeople")
			.platform(OauthPlatformStatus.KAKAO)
			.registerDate(LocalDateTime.now())
			.build();

		// UserEntity로 변환
		writerEntity = UserEntity.from(writer);
		withPeopleEntity = UserEntity.from(withPeople);

		anReqDto = AnReqDto.builder()
			.writerEmail("writer@example.com")
			.withPeopleEmail("withpeople@example.com")
			.title("Test Anniversary")
			.anniversaryDate("2023-12-25T00:00")
			.build();

		anDto = AnDto.builder()
			.id(1L)
			.title("Test Anniversary")
			.writerEmail("writer@example.com")
			.withPeopleEmail("withpeople@example.com")
			.anniversaryDate("2023-12-25T00:00")
			.build();

		anniversary = Anniversary.builder()
			.id(1L)
			.title("Test Anniversary")
			.anniversaryDate(LocalDateTime.parse("2023-12-25T00:00"))
			.writer(writerEntity)
			.withPeople(withPeopleEntity)
			.build();
	}

	@DisplayName("기념일 생성 테스트")
	@Test
	void createAnniversaryTest() {
		when(userRepository.findByEmail(anReqDto.getWriterEmail())).thenReturn(Optional.of(writerEntity));
		when(userRepository.findByEmail(anReqDto.getWithPeopleEmail())).thenReturn(Optional.of(withPeopleEntity));
		when(modelMapper.map(anReqDto, AnDto.class)).thenReturn(anDto);
		when(anRepository.save(any(Anniversary.class))).thenReturn(modelMapper.map(anDto, Anniversary.class));

		AnResDto result = anService.createAnniversary(anReqDto);

		assertEquals(anDto.getTitle(), result.getTitle());
		assertEquals(anDto.getWriterEmail(), result.getWriter().getEmail());
		assertEquals(anDto.getWithPeopleEmail(), result.getWithPeople().getEmail());
	}

	@DisplayName("기념일 ID로 조회 테스트")
	@Test
	void getAnniversaryTest() {
		when(anRepository.findById(1L)).thenReturn(Optional.of(anniversary));
		when(modelMapper.map(anniversary, AnDto.class)).thenReturn(anDto);

		AnResDto result = anService.getAnniversary(1L);

		assertEquals(anDto.getTitle(), result.getTitle());
		assertEquals(anDto.getWriterEmail(), result.getWriter().getEmail());
	}

	@DisplayName("기념일 업데이트 테스트")
	@Test
	void updateAnniversaryTest() {
		when(anRepository.findById(1L)).thenReturn(Optional.of(anniversary));
		when(userRepository.findByEmail(anReqDto.getWriterEmail())).thenReturn(Optional.of(writerEntity));
		when(userRepository.findByEmail(anReqDto.getWithPeopleEmail())).thenReturn(Optional.of(withPeopleEntity));
		when(modelMapper.map(anReqDto, AnDto.class)).thenReturn(anDto);

		AnResDto result = anService.updateAnniversary(1L, anReqDto);

		assertEquals(anDto.getTitle(), result.getTitle());
		verify(anRepository).save(anniversary);
	}

	@DisplayName("기념일 삭제 테스트")
	@Test
	void deleteAnniversaryTest() {
		when(anRepository.findById(1L)).thenReturn(Optional.of(anniversary));

		anService.deleteAnniversary(1L);

		verify(anRepository).deleteById(1L);
	}

	@DisplayName("특정 날짜 범위 내 기념일 조회 테스트")
	@Test
	void findAnniversariesInRangeTest() {
		LocalDateTime startDate = LocalDateTime.now().minusDays(1);
		LocalDateTime endDate = LocalDateTime.now().plusDays(10);

		when(anRepository.findAllByAnniversaryDateBetween(startDate, endDate)).thenReturn(List.of(anniversary));

		List<AnResDto> results = anService.findAnniversariesInRange(startDate, endDate);

		assertEquals(1, results.size());
		assertEquals("Test Anniversary", results.get(0).getTitle());
	}

	@DisplayName("다음 반복 기념일 계산 테스트")
	@Test
	void calculateNextAnniversaryTest() {
		when(anRepository.findById(1L)).thenReturn(Optional.of(anniversary));

		LocalDateTime nextAnniversary = anService.calculateNextAnniversary(1L);

		assertEquals(anniversary.getAnniversaryDate().plusYears(1), nextAnniversary);
	}
}
