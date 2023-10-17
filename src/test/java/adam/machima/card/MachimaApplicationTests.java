package adam.machima.card;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class MachimaApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    @Rollback
    void shouldNotFailPostRequest() {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("image", new ClassPathResource("test_image.webp"));
        parameters.add("lat_str", "50.441255");
        parameters.add("lon_str", "30.489610");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/v1/card", HttpMethod.POST, entity, String.class, ""
        );
        // String response = restTemplate.postForObject("/api/v1/card", entity, String.class);
        assertThat(response.getBody()).isEqualTo("File saved successfully");
    }
}
