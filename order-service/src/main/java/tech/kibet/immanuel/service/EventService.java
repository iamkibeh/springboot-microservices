package tech.kibet.immanuel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tech.kibet.immanuel.constant.AppConstant;

@Service
@RequiredArgsConstructor
public class EventService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendNotification(String orderNo){
        kafkaTemplate.send(AppConstant.ORDER_NOTIFICATION_TOPIC, orderNo);
    }
}
