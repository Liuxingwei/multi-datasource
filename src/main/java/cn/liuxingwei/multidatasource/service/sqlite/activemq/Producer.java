package cn.liuxingwei.multidatasource.service.sqlite.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
@Slf4j
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final Object message) {
        try {
            jmsMessagingTemplate.convertAndSend(destination, message);
        } catch (Exception e) {
            log.info("--------是我抛的-------");
            throw e;
        }
    }
}
