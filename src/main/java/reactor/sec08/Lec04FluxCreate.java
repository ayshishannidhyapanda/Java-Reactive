package reactor.sec08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
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
 * Created on: 23-01-2026
 */
public class Lec04FluxCreate {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreate.class);

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("reactor.bufferSize.small", "16"); //Default Queue size is 256, we use 16 here

        var producer = Flux.create(fluxSink -> {
                    for (int i = 1; i <= 500 && !fluxSink.isCancelled(); i++) {
                        log.info("generating {}", i);
                        fluxSink.next(i);
                        Util.sleep(Duration.ofMillis(50));
                    }
                    fluxSink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());


        producer
//                .limitRate(1)//WORSEN THE THING
                .publishOn(Schedulers.boundedElastic())
                .map(Lec04FluxCreate::timeConsuming)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static int timeConsuming(int i) {
        log.info("received {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
