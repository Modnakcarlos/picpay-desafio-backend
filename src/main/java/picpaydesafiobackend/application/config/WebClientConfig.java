package picpaydesafiobackend.application.config;

import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Builder
public class WebClientConfig {

    @Bean
    public WebClientConfig webClient() {
        return WebClientConfig.builder().build();
    }
}
