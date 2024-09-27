package ku.user.client;

import ku.user.domain.user.service.TestService;
import ku.user.global.client.GameServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestClientException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest(properties = "spring.profiles.active=local")
class GameServiceClientTest {

    @MockBean
    private GameServiceClient gameServiceClient;

    @Autowired
    private TestService testService;

    @Test
    void 요청_시간이_초과되면_예외를_발생시킨다() {
        // Given: 예외를 던지도록 Mock 설정
        given(gameServiceClient.getGameResults(anyString()))
                .willThrow(new RestClientException("Timeout"));

        // When & Then
        try {
            gameServiceClient.getGameResults("1234");
        } catch (RestClientException e) {
            assertThat(e.getMessage()).contains("Timeout");
        }
    }

    @Test
    void CircuitBreaker가_실패_fallback이_호출된다() {
        // Given
        given(gameServiceClient.getGameResults(anyString()))
                .willThrow(new RuntimeException("Service unavailable"));


        // When
        String result = testService.getResult("testUser");

        // Then
        assertThat(result).isEqualTo("1234"); // Fallback 결과를 확인
    }



}
