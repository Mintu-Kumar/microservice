package com.kafkaspring.controller;

import com.kafkaspring.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private KafkaProducer kafkaProducer;  // here we are not using @Autowired annotation..    If spring bean has only one  constructor then we can avoid @autowired

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    //http:localhost:8088/api/v1/kafka/publish?message=hello world
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message)
    {
            kafkaProducer.sendMessage(message);
            return  ResponseEntity.ok("Message sent to the topic");
    }
}