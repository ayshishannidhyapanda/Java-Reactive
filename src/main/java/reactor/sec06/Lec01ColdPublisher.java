package reactor.sec06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

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

public class Lec01ColdPublisher {
    private final static Logger log = LoggerFactory.getLogger(Lec01ColdPublisher.class);

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        var flux = Flux.create(fluxSink -> {
            log.info("invoked");
            for (int i = 0; i < 3; i++) {
                fluxSink.next(atomicInteger.incrementAndGet());
            }
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
