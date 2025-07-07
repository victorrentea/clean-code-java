package victor.training.cleancode.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class SafetyApiAdapter {
  private final RestTemplate restTemplate;
  @Value("${safety.service.url.base}")
  private final URL baseUrl;

  public record SafetyResponse(String category, String detailsUrl) {
  }

  public boolean isSafe(ProductDto productDto) {
    SafetyResponse response = restTemplate.getForEntity(
            baseUrl + "/product/{barcode}/safety",
            SafetyResponse.class, productDto.barcode())
        .getBody();
    return "SAFE".equals(response.category());
  }
}
