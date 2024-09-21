package ku.user.user.service;

import ku.user.client.GameServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final GameServiceClient gameServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;


    public String getResult(String memberId) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        String result = circuitBreaker.run(()->gameServiceClient.getGameResults("1234"), throwable -> "1234");
        return result;
    }

}
