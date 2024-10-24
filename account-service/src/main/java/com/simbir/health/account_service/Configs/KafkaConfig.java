package com.simbir.health.account_service.Configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    NewTopic createAccountEventsCreatedTopic() {
        return TopicBuilder.name("account-events-created")
                .partitions(3)
                .build();
    }

    @Bean
    NewTopic createAuthRequest() {
        return TopicBuilder.name("auth-request")
                .partitions(3)
                .build();
    }

    @Bean
    NewTopic createAuthResponse() {
        return TopicBuilder.name("auth-response")
                .partitions(3)
                .build();
    }
}
