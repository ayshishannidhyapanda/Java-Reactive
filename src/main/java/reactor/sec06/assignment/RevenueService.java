package reactor.sec06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 16-01-2026
 */
public class RevenueService implements OrderProcessor {

    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        var currentRevenue = db.getOrDefault(order.category(), 0);
        var updateRevenue = currentRevenue + order.price();
        db.put(order.category(), updateRevenue);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> this.db.toString());
    }
}
