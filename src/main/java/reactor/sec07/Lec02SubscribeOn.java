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
 * Created on: 19-01-2026
 */
public class Lec02SubscribeOn {

    private static final Logger log = LoggerFactory.getLogger(Lec02SubscribeOn.class);

    public static void main(String[] args) throws InterruptedException {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", 1);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));/*it was printed by main thread
                (if we don't use runnable, and if we do so then it was run by Thread-0)
                after this everything was print by boundedElastic()*/

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));
        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("sub2"));

//        Thread thread = new Thread(runnable);
//        thread.start();

        Thread.ofPlatform().start(runnable1);
        Thread.ofPlatform().start(runnable2);

        Util.sleepSeconds(2);
    }
}
