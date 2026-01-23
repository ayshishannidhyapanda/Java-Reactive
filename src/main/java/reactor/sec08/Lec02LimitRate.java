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

/*
Reactor automatically handles the backpressure
we can also adjust the limit
 */
public class Lec02LimitRate {

    private static final Logger log = LoggerFactory.getLogger(Lec02LimitRate.class);

    public static void main(String[] args) throws InterruptedException {


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
                .limitRate(5)//don't produce more than 5 item
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
