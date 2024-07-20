package com.kafkastream;

import com.kafkastream.producer.TestProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkastreamApplicationTests {

    @Autowired
    private TestProducer testProducer;

    @Test
    void test() {
        testProducer.create();
    }

}
