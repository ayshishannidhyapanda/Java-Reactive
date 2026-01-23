package reactor.sec07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 22-01-2026
 */

/*  Reactor support virtual threads
    System.setProperty("reactor.schedulers.defaultBoundedElasticVirtualThreads", "true");
*/
public class Lec04VirtualThreads {

    private static final Logger log = LoggerFactory.getLogger(Lec04VirtualThreads.class);

    //parallel thread pool are for CPU task(Not for parallel execution), virtual threads are not for CPU task
    //virtual thread for time-consuming, IO, network calls for blocking operation,
    // boundedElastic schedules tasks that MAY execute on virtual threads
    // but subscription always happens on the caller thread

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first1 - {}",
                        Thread.currentThread().isVirtual()))//run by bounded elastic thread
                .subscribeOn(Schedulers.boundedElastic())//bounded elastic activate
                .doFirst(() -> log.info("first2")); //thread-0 runnable


        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(2);
    }

}
