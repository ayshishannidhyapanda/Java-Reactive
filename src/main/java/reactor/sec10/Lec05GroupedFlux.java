package reactor.sec10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

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
 * Created on: 01-02-2026
 */
public class Lec05GroupedFlux {

    private static final Logger log = LoggerFactory.getLogger(Lec05GroupedFlux.class);

    public static void main(String[] args) {
        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .map(integer -> integer * 2)
                .startWith(1)
                //0, 1
                .groupBy(i -> i % 2)
                .flatMap(integerIntegerGroupedFlux ->
                        processEvents(integerIntegerGroupedFlux))
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> groupedFlux) {

        log.info("Received flux for {}", groupedFlux.key());

        return groupedFlux
                .doOnNext(i -> log.info("key: {}, item: {}", groupedFlux.key(), i))
                .doOnComplete(() -> log.info("{} completed", groupedFlux.key()))
                .then();
    }
}
