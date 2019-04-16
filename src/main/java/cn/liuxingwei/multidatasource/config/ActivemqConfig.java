package cn.liuxingwei.multidatasource.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@Configuration
@Slf4j
public class ActivemqConfig {

    @Bean
    public Destination queue() {
        return new ActiveMQQueue("message");
    }

    @Bean
    public Destination topic() {
        return new ActiveMQTopic("topic");
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

}