package cn.liuxingwei.multidatasource.service.sqlite.activemq;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockQueue;
import com.mockrunner.mock.jms.MockQueueConnectionFactory;
import com.mockrunner.mock.jms.MockTopic;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
@Slf4j
public class ProducerUnitTest {

    @Resource
    private JmsTemplate jmsTemplate;

    @Autowired
    private Producer producer;

    @Bean
    public DestinationManager destinationManager() {
        return new DestinationManager();
    }

    @Bean
    public ConfigurationManager configurationManager() {
        return new ConfigurationManager();
    }

    @Bean
    public MockQueueConnectionFactory jmsConnectionFactory() {
        return new MockQueueConnectionFactory(destinationManager(), configurationManager());
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendQueue() {

        jmsTemplate.setConnectionFactory(jmsConnectionFactory());
        String queueString = "hello";

        MockQueue queue = destinationManager().createQueue("message");

        producer.sendMessage(queue, queueString);
        String textMessage = (String) jmsTemplate.receiveAndConvert(queue);
        log.info("queue string: " + textMessage);
        Assert.assertEquals(queueString, textMessage);

    }

    @Test
    public void sendTopic() {

        jmsTemplate.setConnectionFactory(jmsConnectionFactory());
        jmsTemplate.setPubSubDomain(true);
        String topicString = "大家好！";

        MockTopic topic = destinationManager().createTopic("topic");
        producer.sendMessage(topic, topicString);
        String testTopic = (String) jmsTemplate.receiveAndConvert(topic);
        log.info("topic string: " + testTopic);
        Assert.assertEquals(topicString, testTopic);

    }
}