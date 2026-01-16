package reactor.sec02;

import reactor.common.Util;
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
 * Created on: 08-11-2025
 */
public class Lec09PublisherCreateVsExecution {

    private static final Logger log = LoggerFactory.getLogger(Lec09PublisherCreateVsExecution.class);

    public static void main(String[] args) {

        getName()
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getName() {
        log.info("Entered the method");
        return Mono.fromSupplier(() -> {
            log.info("Generating Name");
            try {
                Util.sleepSeconds(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Util.faker().name().firstName();
        });
    }

}
