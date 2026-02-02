package reactor.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Sinks;

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
 * Created on: 02-02-2026
 */
public class Lec05MulticastDirectBestEffort {

    private static final Logger log = LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);

    public static void main(String[] args) {
//        demo1();
        demo2();

        Util.sleepSeconds(25);
    }
    //when we have multiple subscribers, if one subscriber is slow,
    //we might not be able to safely deliver messages to all subscribers /
    //other fast subscribers might not get the messages.

    //directBestEffort - ignore the slow subscriber and focus the fast subscriber
    private static void demo2() {

        System.setProperty("reactor.bufferSize.small", "16");

        //onBackpressureBuffer is a bounded queue which helps to store value on memory
        // handle through which we pushed item
//        var sink = Sinks.many().multicast().onBackpressureBuffer(100);

        //here mike(slow subscriber) dont affects the fast subscriber(sam)
        var sink = Sinks.many().multicast().directBestEffort();

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));


        for (int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }

    private static void demo1() {

        System.setProperty("reactor.bufferSize.small", "16");

        //onBackpressureBuffer is a bounded queue which helps to store value on memory
        // handle through which we pushed item
        var sink = Sinks.many().multicast().onBackpressureBuffer(100);

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));


        for (int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }
}
