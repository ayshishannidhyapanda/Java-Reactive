package reactor.sec10.assignment.groupBy;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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
public class OrderProcessingService {

    private static final Map<String, UnaryOperator<Flux<PurchaseOrder>>> PROCESSOR_MAP = Map.of(
            "Kids", kidsProcessing(),
            "Automotive", automotiveProcessing()
    );


    private static UnaryOperator<Flux<PurchaseOrder>> automotiveProcessing() {

        return poFlux -> poFlux
                .map(po -> new PurchaseOrder(
                        po.item(),
                        po.category(),
                        po.price() + 100));
    }

    private static UnaryOperator<Flux<PurchaseOrder>> kidsProcessing() {

        return poFlux -> poFlux
                .flatMap(po -> getFreeKidsOrder(po)
                        .flux()
                        .startWith(po));
    }

    private static Mono<PurchaseOrder> getFreeKidsOrder(PurchaseOrder order) {
        return Mono.fromSupplier(() -> new PurchaseOrder(
                order.item() + "-FREE",
                order.category(),
                0
        ));
    }

    public static Predicate<PurchaseOrder> canProcess() {
        return po -> PROCESSOR_MAP.containsKey(po.category());
    }

    public static UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category) {
        return PROCESSOR_MAP.get(category);
    }

}
