package reactor.sec09.applications;

import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
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
 * Created on: 31-01-2026
 */
public class OrderService {

    private static final Map<Integer, List<Order>> orderTable = Map.of(
            1, List.of(
                    new Order(1,
                            Util.faker().commerce().productName(),
                            Util.faker().random().nextInt(10, 1000)),
                    new Order(1,
                            Util.faker().commerce().productName(),
                            Util.faker().random().nextInt(10, 1000))
            ),
            2, List.of(
                    new Order(2,
                            Util.faker().commerce().productName(),
                            Util.faker().random().nextInt(10, 1000)),
                    new Order(2,
                            Util.faker().commerce().productName(),
                            Util.faker().random().nextInt(10, 1000)),
                    new Order(2,
                            Util.faker().commerce().productName(),
                            Util.faker().random().nextInt(10, 1000))
            ),
            3, List.of()
    );

    public static Flux<Order> getUserOrders(Integer userId) {
        return Flux.fromIterable(orderTable.get(userId))
                .delayElements(Duration.ofMillis(500))
                .transform(Util.fluxUnaryOperator("order-for-user " + userId));
    }
}
