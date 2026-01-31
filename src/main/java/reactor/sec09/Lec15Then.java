package reactor.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 31-01-2026
 */

/* "then" could be helpful when we are not interested in the result of a publisher /
    we need to have sequential execution of asynchronous task.  */
public class Lec15Then {

    private static final Logger log = LoggerFactory.getLogger(Lec15Then.class);

    //when then() gets complete/error signal it just gives that
    public static void main(String[] args) {

        var records = List.of("a", "b", "c");

        saveRecords(records)
                .then(sendNotification(records))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static Flux<String> saveRecords(List<String> record) {
        return Flux.fromIterable(record)
                .map(r -> "saved " + r)
                .delayElements(Duration.ofMillis(500));

    }

    private static Mono<Void> sendNotification(List<String> record) {
        return Mono.fromRunnable(() -> log.info("all these {} record saved successfully", record));

    }
}
