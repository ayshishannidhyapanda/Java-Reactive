package reactor.sec11;


import reactor.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

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
public class Lec01Repeat {
    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();

        Util.sleepSeconds(10);
    }

    private static void demo1() {
        getCountry()
                .repeat(2)
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo2() {
        getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo3() {
        var atomicInteger = new AtomicInteger(0);
        getCountry()
                .repeat(() -> atomicInteger.getAndIncrement() < 3)
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo4() {
        getCountry()
                .repeatWhen(flux -> flux.delayElements(Duration.ofMillis(2000))
                        .take(2)) // repeat after every 2 sec for 2 items
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Mono<String> getCountry() {
        return Mono.fromSupplier(() -> Util.faker().country().name()); //non-blocking IO
    }
}
