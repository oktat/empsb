package lan.zold.emp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "emp")
public class CustomProperties {
    private String auth;
    public String getAuth() {
        return this.auth;
    }
    public void setAuth(String auth) {
        this.auth = auth;
    }
}
