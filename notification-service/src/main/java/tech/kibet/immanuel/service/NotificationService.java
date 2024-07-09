package tech.kibet.immanuel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tech.kibet.immanuel.constant.AppConstant;

@Service
@Slf4j
public class NotificationService {

    @KafkaListener(topics = AppConstant.ORDER_NOTIFICATION_TOPIC, groupId = AppConstant.EMAIL_GROUP)
    public void orderNotification(String orderNo){
        log.info("Order notification received for order number -> {}",orderNo);
        // todo - send email notification to the user.
    }
}
