package reactor.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 20-11-2025
 */
public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorContinue((ex, obj) -> log.error("==> {}", obj, ex))
                .onErrorReturn(ArithmeticException.class, -1)
                .onErrorReturn(IllegalArgumentException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
        
    }

    private static void onErrorComplete() {
        Mono.just(1)
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

    private static void onErrorReume() {
        Mono.just(5)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorResume(ArithmeticException.class, ex -> fallback())
                .onErrorReturn(-5)
                .onErrorResume(ex -> fallback())
                .subscribe(Util.subscriber());
    }

    private static void onErrorReturn() {
        Mono.just(5)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorReturn(ArithmeticException.class, -1)
                .onErrorReturn(IllegalArgumentException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());

    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }
}
