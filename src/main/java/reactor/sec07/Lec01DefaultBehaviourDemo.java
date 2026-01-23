package reactor.sec07;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;

/* By default, the current thread is doing all work
 */
public class Lec01DefaultBehaviourDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviourDemo.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> log.info("value: {}", v));

        Runnable runnable =
                () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable);
    }
}
