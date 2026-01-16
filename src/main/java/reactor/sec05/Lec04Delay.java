package reactor.sec05;

import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

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
public class Lec04Delay {

    public static void main(String[] args) throws InterruptedException {

        Flux.range(1, 10)
                .log()
                //delayElements requesting one element each time that's why the producer wait and don't do anything
                .delayElements(Duration.ofSeconds(1))//it happens periodically so we have different thread
                //that is why we have to make sleep the main thread
                .subscribe(Util.subscriber());

        Util.sleepSeconds(12);
    }
}
