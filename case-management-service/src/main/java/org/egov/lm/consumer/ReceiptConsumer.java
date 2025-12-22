package org.egov.lm.consumer;

import java.util.HashMap;

import org.egov.lm.config.PropertyConfiguration;
import org.egov.lm.service.PaymentNotificationService;
import org.egov.lm.service.PaymentUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class ReceiptConsumer {

	@Autowired
    private PaymentUpdateService paymentUpdateService;

	@Autowired
    private PaymentNotificationService paymentNotificationService;

	@Autowired
    private PropertyConfiguration config;

    @KafkaListener(topics = {"${kafka.topics.receipt.create}","${kafka.topics.notification.pg.save.txns}"})
    public void listenPayments(final HashMap<String, Object> record,  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        if(topic.equalsIgnoreCase(config.getReceiptTopic())){
            paymentUpdateService.process(record);
            paymentNotificationService.process(record, topic);
        }
        else paymentNotificationService.process(record, topic);

    }
}
