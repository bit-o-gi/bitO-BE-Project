package bit.anniversary.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bit.anniversary.dto.AnDto;
import bit.anniversary.dto.AnReqDto;
import bit.anniversary.entity.Anniversary;
import bit.user.domain.User;
import bit.user.entity.UserEntity;
import bit.user.oauth.OauthPlatformStatus;
import bit.user.repository.UserJpaRepository;

@DataJpaTest
class AnRepositoryTest {

	@Autowired
	private AnRepository anRepository;

	@Autowired
	private UserJpaRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();
	private UserEntity writer;
	private UserEntity withPeople;

	private UserEntity writerEntity;
	private UserEntity withPeopleEntity;
	private AnReqDto anReqDto;
	private AnDto anDto;
	private Anniversary anniversary;

	@BeforeEach
	void setUp() {
		// 사용자 엔티티 생성
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

	@DisplayName("기념일 저장 및 조회 테스트")
	@Test
	void saveAndFindAnniversaryTest() {
		// AnReqDto -> AnDto -> Anniversary 변환
		AnReqDto anReqDto = AnReqDto.builder()
			.writerEmail(writer.getEmail())
			.withPeopleEmail(withPeople.getEmail())
			.title("Test Anniversary")
			.anniversaryDate("2023-12-25T00:00")
			.build();

		AnDto anDto = anReqDto.toAnDto();
		Anniversary anniversary = anDto.toEntity(modelMapper);
		anniversary.updateAnniversary(anDto, writer, withPeople);

		// 기념일 저장
		Anniversary savedAnniversary = anRepository.save(anniversary);

		// 기념일 조회
		Anniversary foundAnniversary = anRepository.findById(savedAnniversary.getId()).orElse(null);

		// 검증
		assertThat(foundAnniversary).isNotNull();
		assertThat(foundAnniversary.getTitle()).isEqualTo("Test Anniversary");
		assertThat(foundAnniversary.getWriter().getEmail()).isEqualTo("writer@example.com");
		assertThat(foundAnniversary.getWithPeople().getEmail()).isEqualTo("withpeople@example.com");
	}

	@DisplayName("특정 날짜 범위의 기념일 조회 테스트")
	@Test
	void findAllByAnniversaryDateBetweenTest() {
		// 테스트용 기념일 저장
		Anniversary anniversary1 = saveTestAnniversary("2023-12-25T00:00");
		Anniversary anniversary2 = saveTestAnniversary("2024-01-10T00:00");

		LocalDateTime startDate = LocalDateTime.of(2023, 12, 24, 0, 0);
		LocalDateTime endDate = LocalDateTime.of(2024, 1, 5, 23, 59);

		// 특정 날짜 범위 내의 기념일 검색
		List<Anniversary> anniversaries = anRepository.findAllByAnniversaryDateBetween(startDate, endDate);

		// 검증
		assertThat(anniversaries).hasSize(1);
		assertThat(anniversaries.get(0)).isEqualTo(anniversary1);
	}

	@DisplayName("특정 날짜 이후의 기념일 조회 테스트")
	@Test
	void findAllByAnniversaryDateAfterTest() {
		// 테스트용 기념일 저장
		Anniversary anniversary1 = saveTestAnniversary("2023-12-25T00:00");
		Anniversary anniversary2 = saveTestAnniversary("2024-01-10T00:00");

		LocalDateTime date = LocalDateTime.of(2023, 12, 26, 0, 0);

		// 특정 날짜 이후의 기념일 검색
		List<Anniversary> anniversaries = anRepository.findAllByAnniversaryDateAfter(date);

		// 검증
		assertThat(anniversaries).hasSize(1);
		assertThat(anniversaries.get(0)).isEqualTo(anniversary2);
	}

	@DisplayName("특정 날짜 이전의 기념일 조회 테스트")
	@Test
	void findAllByAnniversaryDateBeforeTest() {
		// 테스트용 기념일 저장
		Anniversary anniversary1 = saveTestAnniversary("2023-12-25T00:00");
		Anniversary anniversary2 = saveTestAnniversary("2024-01-10T00:00");

		LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0);

		// 특정 날짜 이전의 기념일 검색
		List<Anniversary> anniversaries = anRepository.findAllByAnniversaryDateBefore(date);

		// 검증
		assertThat(anniversaries).hasSize(1);
		assertThat(anniversaries.get(0)).isEqualTo(anniversary1);
	}

	// 테스트 기념일 저장 헬퍼 메서드
	private Anniversary saveTestAnniversary(String anniversaryDate) {
		AnReqDto anReqDto = AnReqDto.builder()
			.writerEmail(writer.getEmail())
			.withPeopleEmail(withPeople.getEmail())
			.title("Test Anniversary")
			.anniversaryDate(anniversaryDate)
			.build();

		AnDto anDto = anReqDto.toAnDto();
		Anniversary anniversary = anDto.toEntity(modelMapper);
		anniversary.updateAnniversary(anDto, writer, withPeople);

		return anRepository.save(anniversary);
	}
}
