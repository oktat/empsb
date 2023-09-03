package lan.zold.emp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class EmpApplicationTests {
	
	// UserController userController;

	@Autowired
    private AuthController userController;
	
    @LocalServerPort
    private int port;	
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/api";
    }

    @Test
    public void testValidToken() {
        // Az érvényes JWT token, amit ellenőrzünk
        String validToken = generateValidToken();

		HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", validToken);

		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(tokenMap, headers);

        // ResponseEntity<String> response = new TestRestTemplate().postForEntity(baseUrl + "/checkToken", tokenMap, String.class);
	    ResponseEntity<String> response = new TestRestTemplate().exchange(baseUrl + "/checkToken", HttpMethod.POST, requestEntity, String.class);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Érvényes token", response.getBody());
    }

    private String generateValidToken() {
        return "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjg0Njc5NDk0NjUwMzAwMCwiaWF0IjoxNjkzNTg5ODkzfQ.oapj_gJ2yCfVbIgk-Y7QILVkteCkFeuryJv3VMOlG_yIoHW8jyuIlviZELqIJmxbU1xtyc9kUv1BiyUj4iRV6w";
    }	

}
