package bit.user.oauth.kakao.service;

import bit.user.oauth.port.OAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KaKaoLoginServiceImpl implements OAuthService {

    @Override
    public String getToken(String code, String clientId, String redirectUri, String clientSecret)
            throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();
        httpBody.add("grant_type", "authorization_code");
        httpBody.add("client_id", clientId);
        httpBody.add("redirect_uri", redirectUri);
        httpBody.add("code", code);
        httpBody.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(httpBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://kauth.kakao.com/oauth/token", httpEntity, String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        return jsonNode.get("access_token").asText();
    }

    @Override
    public String getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://kapi.kakao.com/v2/user/me", httpEntity, String.class
        );
        return response.getBody();
    }
}