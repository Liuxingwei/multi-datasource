package cn.liuxingwei.multidatasource.service.sqlite.activemq;

import cn.liuxingwei.multidatasource.client.activemq.QueueConsumerFirst;
import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockDestination;
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
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
@Slf4j
public class ProducerUnitTest {

//    @Resource
//    private JmsMessagingTemplate jmsTemplate;

    @Resource
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsListenerContainerFactory jmsListenerContainerTopic;

    @Autowired
    private Producer producer;

    @Autowired
    private QueueConsumerFirst queueConsumerFirst;

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
    private Destination topic;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendQueue() {

        String queueString = "hello";

        MockQueue queue = destinationManager().createQueue("testQueue");

        producer.sendMessage(queue, queueString);
        String textMessage = (String) jmsTemplate.receiveAndConvert(queue);
        log.info("queue string: " + textMessage);
        Assert.assertEquals(queueString, textMessage);

    }

    @Test
    public void sendTopic() {

        jmsTemplate.setPubSubDomain(true);

        String topicString = "大家好！";

        MockTopic topic = destinationManager().createTopic("testTopic");

        producer.sendMessage(topic, topicString);

        String testTopic = (String) jmsTemplate.receiveAndConvert(topic);
        log.info("topic string: " + testTopic);
        Assert.assertEquals(topicString, testTopic);

    }
}