package reactor.sec08;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 23-01-2026
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*Reactor automatically handle the backpressure
to handle backpressure which subscribers calls producer to produce a lot but the subscriber cant consume it
so cant speed up the subscriber consumption because its on business logic so basically we have to slow down
production rate of producer
System.setProperty("reactor.bufferSize.small","16");
 * */
public class Lec01BackPressureHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec01BackPressureHandling.class);

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("reactor.bufferSize.small", "16"); //Default Queue size is 256, we use 16 here

        var producer = Flux.generate(
                        () -> 1,
                        (integer, synchronousSink) -> {
                            log.info("generating {}", integer);
                            synchronousSink.next(integer);
                            return ++integer;
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());


        producer
                .publishOn(Schedulers.boundedElastic())
                .map(i -> {
                    try {
                        return timeConsuming(i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static int timeConsuming(int i) throws InterruptedException {
        Util.sleepSeconds(1);
        return i;
    }
}
