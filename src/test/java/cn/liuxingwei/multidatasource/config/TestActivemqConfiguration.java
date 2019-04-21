package cn.liuxingwei.multidatasource.config;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestActivemqConfiguration {
    @Bean
    public DestinationManager destinationManager() {
        return new DestinationManager();
    }

    @Bean
    public ConfigurationManager configurationManager() {
        return new ConfigurationManager();
    }
}
