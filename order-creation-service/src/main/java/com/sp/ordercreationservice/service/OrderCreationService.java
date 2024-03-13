package com.sp.ordercreationservice.service;

import com.sp.ordercreationservice.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/order/v1")
public class OrderCreationService {

    private static final Logger log = LoggerFactory.getLogger(OrderCreationService.class);

    @Autowired
    KafkaTemplate<String, Order> createOrderKafkaTemplate;

    @Value("${spring.kafka.order.topic.orders}")
    String createOrderTopic;

    @PostMapping("/createOrder")
    public ResponseEntity createOrder(@RequestBody Order order) throws ExecutionException, InterruptedException {
        SendResult<String, Order> sendResult = createOrderKafkaTemplate.send(createOrderTopic, order).get();
        log.info("Create order {} event sent via Kafka", order);
        log.info(sendResult.toString());
        return ResponseEntity.created(null).build();
    }
}
