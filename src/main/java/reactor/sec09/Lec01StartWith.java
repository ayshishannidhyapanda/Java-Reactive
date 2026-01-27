package reactor.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 24-01-2026
 */
public class Lec01StartWith {

    private final static Logger log = LoggerFactory.getLogger(Lec01StartWith.class);

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();

        Util.sleepSeconds(3);
    }

    private static void demo1() {
        producer1()
                .startWith(-3 - 2, -1, 0)// just add these value before the producer's values
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1()
                .startWith(List.of(-2, -1, 0, 1 + 2))
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        producer1()
                .startWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        producer1()
                .startWith(producer2())
                .startWith(1000)
                .subscribe(Util.subscriber());
    }

    @NonNull
    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(subscription -> log.info("Subscribing to Producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    @NonNull
    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(subscription -> log.info("Subscribing to Producer2"))
                .delayElements(Duration.ofMillis(10));
    }
}
