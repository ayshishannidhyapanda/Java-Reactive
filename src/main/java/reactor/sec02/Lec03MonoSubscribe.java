package reactor.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 02-11-2025
 */
public class Lec03MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {

        var mono = Mono.just(1)
                .map(integer -> integer + "a");

        mono.subscribe(
                i -> log.info("received {}", i),
                err -> log.error("Error ", err),
                () -> log.info("Completed"),
                subscription -> subscription.request(1)
//                subscription -> subscription.cancel()
        );

    }

}
