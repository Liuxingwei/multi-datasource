package cn.liuxingwei.multidatasource.config;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public DestinationManager destinationManager() {
        return new DestinationManager();
    }

    @Bean
    public ConfigurationManager configurationManager() {
        return new ConfigurationManager();
    }
}
