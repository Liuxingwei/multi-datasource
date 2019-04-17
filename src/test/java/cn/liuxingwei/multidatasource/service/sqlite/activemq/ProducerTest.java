package cn.liuxingwei.multidatasource.service.sqlite.activemq;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockQueueConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
public class ProducerTest {

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

    @Autowired
    private Producer producer;

    @Autowired
    private Destination queue;

    @Autowired
    private Destination topic;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendMessage() throws Exception{
        String queueString = "hello";
        String topicString = "大家好！";

//        Mockito.verify(topicConsumerFirst, Mockito.times(1)).receiveTopic(topicString);
        producer.sendMessage(queue, queueString);

        producer.sendMessage(topic, topicString);

        TimeUnit.MILLISECONDS.sleep(100);

    }
}