package com.kafkastream.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final SseEmitterService sseEmitterService;

    @KafkaListener(topics = "topic", groupId = "group1")
    public void consume(String message) {
        // 메시지 처리 및 계산 로직
        String processedMessage = processMessage(message);

        // SSE를 통해 클라이언트에 전송
        sseEmitterService.sendToClient(processedMessage);
    }

    private String processMessage(String message) {
        // 여기에서 필요한 계산을 수행
        return "처리된 메시지: " + message;
    }
}