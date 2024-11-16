package com.kafkaspring.controller;


import com.kafkaspring.kafka.JaonKafkaProducer;
import com.kafkaspring.payload.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private JaonKafkaProducer jaonKafkaProducer;

    public JsonMessageController(JaonKafkaProducer kafkaProducer) {
        this.jaonKafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user)
    {
        jaonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Json message sent to kafka topic");
    }
}
