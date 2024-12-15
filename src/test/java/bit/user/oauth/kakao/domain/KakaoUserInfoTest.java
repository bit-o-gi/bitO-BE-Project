package bit.user.oauth.kakao.domain;

import static bit.user.oauth.kakao.fixture.JsonNodeTestFixture.createSampleJsonNode;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KakaoUserInfoTest {

    @DisplayName("카카오 서비스에서 주는 response body로부터 KakaoUserInfo 객체를 생성할 수 있다.")
    @Test
    void createKakaoUserInfoByResponseBody() throws Exception {
        // given
        JsonNode jsonNodeSample = createSampleJsonNode();

        // when
        KakaoUserInfo kakaoUserInfo = KakaoUserInfo.of(jsonNodeSample);

        // then
        assertThat(kakaoUserInfo).extracting(
                "connectedAt",
                "email",
                "nickname"
        ).containsExactly(
                OffsetDateTime.parse(jsonNodeSample.get("connected_at").asText()).toLocalDateTime(),
                jsonNodeSample.get("kakao_account").get("email").asText(),
                jsonNodeSample.get("properties").get("nickname").asText()
        );
    }


}