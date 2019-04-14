package cn.liuxingwei.multidatasource.service.sqlite.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final Object message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
