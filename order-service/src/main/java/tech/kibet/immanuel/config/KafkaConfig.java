package tech.kibet.immanuel.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import tech.kibet.immanuel.constant.AppConstant;

@Configuration
public class KafkaConfig {


    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(AppConstant.ORDER_NOTIFICATION_TOPIC)
                .build();
    }
}
