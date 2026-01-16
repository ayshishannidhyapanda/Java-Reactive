package reactor.sec06.assignment;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 16-01-2026
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    private Flux<Order> orderFlux;

    public Flux<Order> orderStream() {
        if (Objects.isNull(orderFlux))
            this.orderFlux = getOrderStream();
        return this.orderFlux;
    }

    public Flux<Order> getOrderStream() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::parse)
                .doOnNext(order -> log.info("{}", order))
                .publish()
                .refCount(2);
    }

    private Order parse(String message) {
        var arr = message.split(":");
        return new Order(
                arr[1],
                Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3])
        );
    }
}
