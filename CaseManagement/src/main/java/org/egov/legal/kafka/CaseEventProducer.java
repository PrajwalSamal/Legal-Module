package org.egov.legal.kafka;

import org.egov.legal.event.CaseFiledEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CaseEventProducer {

    private static final String TOPIC = "legal.case.filed";

//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    public CaseEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public void publishCaseFiled(CaseFiledEvent event) {
//        kafkaTemplate.send(
//                TOPIC,
//                event.diaryNumber(), // key for partitioning
//                event
//        );
    }
}
