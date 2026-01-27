package reactor.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import reactor.common.Util;
import reactor.core.publisher.Flux;

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
 * Created on: 25-01-2026
 */
public class Lec04ConcatError {
    private final static Logger log = LoggerFactory.getLogger(Lec04ConcatError.class);

    public static void main(String[] args) {

        demo1();
        demo2();

        Util.sleepSeconds(3);
    }

    private static void demo1() {
        producer1()
                .concatWith(producer3())
                .concatWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.concatDelayError(producer1(), producer3(),
                        producer2()) //it will run error at last after producer 2, 3 will run
                .subscribe(Util.subscriber());
    }

    @NonNull
    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(subscription -> log.info("Subscribing to Producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    @NonNull
    private static Flux<Integer> producer3() {
        return Flux.error(new RuntimeException("oops"));
    }

    @NonNull
    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(subscription -> log.info("Subscribing to Producer2"))
                .delayElements(Duration.ofMillis(10));
    }
}
