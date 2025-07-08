package victor.training.cleancode.app;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Slf4j
@RequiredArgsConstructor
@Service // Adapter pattern
public class RapexApiClient {
  private final RestTemplate restTemplate;
  @Value("${rapex.service.url.base}")
  private URL baseUrl;

  @SuppressWarnings("DataFlowIssue")
  public boolean isSafe(@NotNull String barcode) {
    SafetyResponse response = restTemplate.getForEntity(
            baseUrl + "/product/{barcode}/safety",
            SafetyResponse.class, barcode.toLowerCase())
        .getBody();
    return "SAFE".equals(response.safetyClass());
  }

  public record SafetyResponse(String safetyClass, String detailsUrl) {
  }

}
