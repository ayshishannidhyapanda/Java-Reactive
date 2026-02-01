package reactor.sec11;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * retry operator simply resubscribe when it sees an error signal,
 * it does not like complete signal.
 * */
public class Lec02Retry {

    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        demo2();

        Util.sleepSeconds(10);
    }

    private static void demo2() {
        getCountry()
                .retryWhen(
                        Retry.fixedDelay(3, Duration.ofSeconds(1))
                                .doBeforeRetry(retrySignal -> log.info("Retrying {} time", retrySignal))
                                .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                                .onRetryExhaustedThrow((spec, signal) -> signal.failure())
                )
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Mono<String> getCountry() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                    if (atomicInteger.getAndIncrement() < 3) {
                        throw new RuntimeException("Error");
                    }
                    return Util.faker().country().name();
                }).doOnError(err -> log.error("Error {}", err.getMessage()))
                .doOnSubscribe(s -> log.info("Subscribing {}", s)); //non-blocking IO
    }

    private static void demo1() {
        getCountry()
                .retry(3)
                .subscribe(Util.subscriber("Ashish"));
    }
}
