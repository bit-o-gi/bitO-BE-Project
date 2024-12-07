package bit.user.oauth.kakao.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import bit.user.exception.KaKaoRestTemplateProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

class KaKaoLoginServiceImplTest {

    private final HttpHeaders headers = new HttpHeaders();
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private KaKaoLoginServiceImpl kaKaoLoginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String accessToken = "accessToken";
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
    }

    @DisplayName("Unauthorized 예외가 발생할 경우, HttpClientErrorException 발생한다.")
    @Test
    void throwHttpClientErrorExceptionExceptionOnUnauthorized() {
        String accessToken = "accessToken";

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        when(restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", httpEntity, String.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        assertThatThrownBy(() -> kaKaoLoginService.getUserInfo(accessToken))
                .isInstanceOf(HttpClientErrorException.class);
    }

    @DisplayName("response body가 null인 경우, KaKaoRestTemplateProcessingException 발생한다")
    @Test
    void throwKaKaoRestTempleProcessingExceptionOnNullResponseBody() {
        // given
        String accessToken = "accsssToken";

        // when
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        when(restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", httpEntity, String.class))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        // then
        assertThatThrownBy(() -> kaKaoLoginService.getUserInfo(accessToken))
                .isInstanceOf(KaKaoRestTemplateProcessingException.class)
                .hasMessage("카카오 로그인 중 응답이 없습니다.");
    }

    @DisplayName("Json 변환에 실패할 경우, KaKaoRestTemplateProcessingException 발생한다.")
    @Test
    void throwKaKaoRestTemplateProcessingExceptionWhenJsonProcessingIsNotWorking() throws Exception {
        String accessToken = "accessToken";
        String responseBody = "{\"success\":true}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", httpEntity, String.class))
                .thenReturn(response);
        when(objectMapper.readTree(responseBody)).thenThrow(new JsonProcessingException("JSON processing error") {
        });

        assertThatThrownBy(() -> kaKaoLoginService.getUserInfo(accessToken))
                .isInstanceOf(KaKaoRestTemplateProcessingException.class)
                .hasMessage("카카오 로그인 중 에러가 발생하였습니다.");
    }
}