package reactor.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 21-11-2025
 */
public class Lec10Transform {

    private static final Logger log = LoggerFactory.getLogger(Lec10Transform.class);

    public static void main(String[] args) {

        var isDebugEnabled = true;

        getCustomers()
                //Function identity means just return the value and do nothing
                .transform(isDebugEnabled ? addDebugger() : Function.identity())
                .subscribe(Util.subscriber());

//        getPurchaseOrders()
//                .doOnNext(i -> log.info("received: {}", i))
//                .doOnComplete(() -> log.info("completed"))
//                .doOnError(err -> log.error("error", err))
//                .subscribe(Util.subscriber());

        getPurchaseOrders()
                .transform(addDebugger())
                .subscribe(Util.subscriber());
    }

//    private static <T>Function<Flux<T>, Flux<T>>, //if return type & accept type are same we can use unary operator

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("error", err));

    }

    private static Flux<Customer> getCustomers() {
        return Flux.range(1, 3)
                .map(integer -> new Customer(integer, Util.faker().name().firstName()));
    }

    private static Flux<Purchase> getPurchaseOrders() {
        return Flux.range(1, 5)
                .map(integer -> new Purchase(Util.faker().commerce().productName(), integer, integer * 10));
    }

    record Customer(int id, String name) {
    }

    record Purchase(String productName, int price, int quantity) {
    }
}
