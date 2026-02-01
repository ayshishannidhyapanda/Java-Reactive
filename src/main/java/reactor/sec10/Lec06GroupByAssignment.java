package reactor.sec10;

import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.sec10.assignment.groupBy.OrderProcessingService;
import reactor.sec10.assignment.groupBy.PurchaseOrder;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 01-02-2026
 */
public class Lec06GroupByAssignment {

    public static void main(String[] args) {
        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(grFlux -> grFlux
                        .transform(OrderProcessingService.getProcessor(grFlux.key())))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.create());
    }
}
