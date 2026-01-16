package reactor.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 20-11-2025
 */
public class Lec05Subscribe {

    private final static Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);

    public static void main(String[] args) {


        Flux.range(1, 10)
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("error", err))
                .subscribe();
    }
}
