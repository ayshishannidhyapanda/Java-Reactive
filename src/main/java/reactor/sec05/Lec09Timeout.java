package reactor.sec05;

import reactor.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

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

/*timeout-will produce timeout error
        we can handle as part of onError methods
        there is also an overloaded method to accept a publisher
        we can have multiple timeouts. the closest one to the subscriber will take effect for the subscriber.
 **/
public class Lec09Timeout {

    public static void main(String[] args) throws InterruptedException {

        var mono = getProductName()
                .timeout(Duration.ofSeconds(2), fallback());

        mono
                .timeout(Duration.ofMillis(400), fallback())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "service-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(1900));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> "fallback-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(300))
                .doFirst(() -> System.out.println("do first"));
    }

}
