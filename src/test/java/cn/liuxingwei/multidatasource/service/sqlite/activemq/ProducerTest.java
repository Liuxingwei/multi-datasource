package cn.liuxingwei.multidatasource.service.sqlite.activemq;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

import java.util.concurrent.TimeUnit;

/**
 * 保留这个测试类，只是为了演示通过发送消息，查看日志，来确定activemq是否正常工作。
 * 这个不是单元测试的作用，而是集成测试的作用。
 * 但是在实验阶段，需要这个测试来看环境是否生效，代码是否正确无误，以及对 activemq 的集成是否成功。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {

    @Resource
    private JmsTemplate jmsTemplate;

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

        producer.sendMessage(queue, queueString);

        producer.sendMessage(topic, topicString);

        TimeUnit.MILLISECONDS.sleep(100);

    }
}