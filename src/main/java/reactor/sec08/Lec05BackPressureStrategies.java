package reactor.sec08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

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
 * Created on: 24-01-2026
 */

/* Reactor provides back pressure strategies like:-
    - buffer    ->  Keep in memory
    - drop      ->  Once the queue is full, new items will be dropped
                    Also it drops any unnecessary item which are generate by producer but not request by consumer
                    downstream request is 2 and upstream produces 20 so 18 will be dropped out.

    - latest    ->  Once the queue is full, keep 1 latest item as and when it arrives. drop old
    - error     ->  throw error in downstream(Subscriber) and cancel message to upstream(Producer)
*/
public class Lec05BackPressureStrategies {
    private static final Logger log = LoggerFactory.getLogger(Lec05BackPressureStrategies.class);

    public static void main(String[] args) {

        var producer = Flux.create(fluxSink -> {
                    for (int i = 1; i <= 500 && !fluxSink.isCancelled(); i++) {
                        log.info("generating {}", i);
                        fluxSink.next(i);
                        Util.sleep(Duration.ofMillis(50));
                    }
                    fluxSink.complete();
                }, FluxSink.OverflowStrategy.BUFFER)
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());


        producer
                //the producer thread generate data/item and store it here,.onBackpressureBuffer() unbounded queue
                //.onBackpressureBuffer()

                //.onBackpressureError()

                //10 item will store on buffer,
                // 2-11 item stored here when 12th arrive it stop producer and sends error
                //.onBackpressureBuffer(10)

//                .onBackpressureDrop()

                .onBackpressureLatest()
                .log()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec05BackPressureStrategies::timeConsuming)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static int timeConsuming(int i) {
        log.info("received {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
