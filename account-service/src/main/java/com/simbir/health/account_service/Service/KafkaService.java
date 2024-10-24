package com.simbir.health.account_service.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @KafkaListener(topics = "auth-request", groupId = "account-service")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
