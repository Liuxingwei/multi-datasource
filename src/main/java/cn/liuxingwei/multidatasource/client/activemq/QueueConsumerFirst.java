package cn.liuxingwei.multidatasource.client.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueConsumerFirst {
    @JmsListener(destination = "message")
    public void receiveMessage(String text) {
        log.info("Consumer queue first：" + text);
    }
}
