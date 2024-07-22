package com.kafkastream.controller;

import com.kafkastream.service.SseEmitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class SseController {

    private final SseEmitterService sseService;


    @GetMapping("/sse")
    public SseEmitter subscribe() {
        return sseService.createEmitter();
    }

    @KafkaListener(topics = "topic")
    public void listen(String message) {
        sseService.sendToClient(message);
    }
}
