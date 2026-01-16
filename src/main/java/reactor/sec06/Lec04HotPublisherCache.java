package reactor.sec06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created on: 16-01-2026
 */
public class Lec04HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) throws InterruptedException {

        /*The problem here is we have to wait to know the current price
        we have to wait to the price to get emitted after that we can receive*/
//        var stockFlux = stockStream().publish().autoConnect;

        //replay try to cache everything, so that newcomers can see
        var stockFlux = stockStream().replay(1)// 1 is the backward level
                .autoConnect(0);

        Util.sleepSeconds(4);

        log.info("Ankit Joining");

        stockFlux
                .subscribe(Util.subscriber("ankit"));

        Util.sleepSeconds(4);

        log.info("Mike Joining");

        stockFlux
                .subscribe(Util.subscriber("mike"));


        Util.sleepSeconds(15);
    }

    //The problem here is we have to wait to know the current price
    //we have to wait to the price to get emitted after that we can receive

    private static Flux<Integer> stockStream() {
        return Flux.generate(
                        sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emitting price: {}", price))
                .cast(Integer.class);
    }
}
