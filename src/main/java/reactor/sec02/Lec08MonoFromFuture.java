package reactor.sec02;

import reactor.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 08-11-2025
 */
public class Lec08MonoFromFuture {

    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) throws InterruptedException {
        Mono.fromFuture(() -> getName());
//                .subscribe(Util.subscriber());
        Util.sleepSeconds(1);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Generating name");
            return Util.faker().name().firstName();
        });
    }
}
