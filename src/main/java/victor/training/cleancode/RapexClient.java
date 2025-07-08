package victor.training.cleancode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import victor.training.cleancode.support.ProductDto;

import java.net.URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class RapexClient {
  private final RestTemplate restTemplate;
  @Value("${safety.service.url.base}")
  private URL baseUrl;

  public record SafetyResponse(String safetyClass, String detailsUrl) {
  }

  public boolean isSafe(ProductDto productDto) {
    SafetyResponse response = restTemplate.getForEntity(
            baseUrl + "/product/{barcode}/safety",
            SafetyResponse.class, productDto.barcode().toLowerCase())
        .getBody();
    return "SAFE".equals(response.safetyClass());
  }
}
