package cn.liuxingwei.multidatasource.client.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer2 {
    @JmsListener(destination = "message")
    public void receiveMessage(String text) {
        log.info("Consumer2 queue：" + text);
    }
    @JmsListener(destination = "topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(String text) {
        log.info("Consumer2 topic: " + text);
    }
}
