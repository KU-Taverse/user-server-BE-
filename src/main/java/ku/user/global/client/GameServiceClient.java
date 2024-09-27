package ku.user.global.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="game-service")
public interface GameServiceClient {
    @GetMapping("/game-service/{userId}/games")
    String getGameResults(@PathVariable String userId);
}
