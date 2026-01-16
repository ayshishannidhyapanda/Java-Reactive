package reactor.sec03;

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
 * Created on: 14-11-2025
 */
public class Lec09FluxInterval {

    public static void main(String[] args) throws InterruptedException {

        Flux.interval(Duration.ofMillis(500))
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
