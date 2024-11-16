package bit.anniversary.controller;

import bit.anniversary.dto.AnDto;
import bit.anniversary.dto.AnReqDto;
import bit.anniversary.dto.AnResDto;
import bit.anniversary.service.AnService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnControllerTest {

	@InjectMocks
	private AnController anController;

	@Mock
	private AnService anService;

	private AnReqDto anReqDto;
	private AnDto anDto;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);

		anReqDto = AnReqDto.builder()
			.writerEmail("writer@example.com")
			.withPeopleEmail("withpeople@example.com")
			.title("Test Anniversary")
			.anniversaryDate("2023-12-25T00:00")
			.build();

		// Create AnDto object for conversion
		anDto = AnDto.builder()
			.id(1L)
			.title("Test Anniversary")
			.writerEmail("writer@example.com")
			.withPeopleEmail("withpeople@example.com")
			.anniversaryDate("2023-12-25T00:00")
			.build();
	}

	@DisplayName("Create Anniversary Test")
	@Test
	void createAnniversaryTest() {
		when(anService.createAnniversary(anReqDto)).thenReturn(AnResDto.from(anDto, null, null, 0));
		AnResDto result = anController.createAnniversary(anReqDto);
		assertEquals(anDto.getTitle(), result.getTitle());
	}

	@DisplayName("Update Anniversary Test")
	@Test
	void updateAnniversaryTest() {
		when(anService.updateAnniversary(1L, anReqDto)).thenReturn(AnResDto.from(anDto, null, null, 0));
		AnResDto result = anController.updateAnniversary(1L, anReqDto);
		assertEquals(anDto.getTitle(), result.getTitle());
	}
}
