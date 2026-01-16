package reactor.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.sec01.subscriber.SubscriberImpl1;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 15-11-2025
 */
public class Lec04FluxCreateDownstreamDemand {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Produced Demand here...");
        produceDemand();
        System.out.println("Produced Early here...");
        produceEarly();
    }

    private static void produceEarly() throws InterruptedException {
        var subscriber = new SubscriberImpl1();
        Flux.<String>create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                log.info("name generated: {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);

//        subscriber.getSubscription().cancel();

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel(); // after cancellation the data is gone
    }

    private static void produceDemand() throws InterruptedException {
        var subscriber = new SubscriberImpl1();
        Flux.<String>create(fluxSink -> {

            fluxSink.onRequest(request -> {
                for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {

                    var name = Util.faker().name().firstName();
                    log.info("name generated: {}", name);
                    fluxSink.next(name);
                }
            });

//            fluxSink.isCancelled();
        }).subscribe(subscriber);

//        subscriber.getSubscription().cancel();

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel(); // after cancellation the data is gone
    }
}
