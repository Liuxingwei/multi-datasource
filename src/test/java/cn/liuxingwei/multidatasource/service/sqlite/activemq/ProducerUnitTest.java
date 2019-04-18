package cn.liuxingwei.multidatasource.service.sqlite.activemq;

import cn.liuxingwei.multidatasource.client.activemq.QueueConsumerFirst;
import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockDestination;
import com.mockrunner.mock.jms.MockQueue;
import com.mockrunner.mock.jms.MockQueueConnectionFactory;
import com.mockrunner.mock.jms.MockTopic;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
public class ProducerUnitTest {

    @Resource
    private JmsMessagingTemplate jmsTemplate;

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
    public void sendMessage() throws Exception{
        String queueString = "hello";
        String topicString = "大家好！";

        MockQueue queue = destinationManager().createQueue("testQueue");
        MockTopic topic = destinationManager().createTopic("testTopic");

        producer.sendMessage(queue, queueString);
        String testQueue = jmsTemplate.receiveAndConvert(queue, String.class);
        Assert.assertEquals(queueString, testQueue);

        producer.sendMessage(topic, topicString);
        String testTopic = jmsTemplate.receiveAndConvert(topic, String.class);
        Assert.assertEquals(topicString, testTopic);

        TimeUnit.MILLISECONDS.sleep(100);
    }
}