package reactor.sec09;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 26-01-2026
 */

import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*  Zip
    -   we will subscribe to all the producer at the same time
    -   all or nothing
    -   all producers will have to emit an item
 */
public class Lec07Zip {

    public static void main(String[] args) {
        Flux
                .zip(getBody(), getEngine(), getTyres())
                .map(t -> new Car(t.getT1(), t.getT2(), t.getT3()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body-" + i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
                .map(i -> "engine-" + i)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTyres() {
        return Flux.range(1, 10)
                .map(i -> "tyre-" + i)
                .delayElements(Duration.ofMillis(75));
    }

    private static Flux<Integer> producer4() {
        return Flux.range(1, 10);
    }

    record Car(String body, String Engine, String tyre) {
    }

}
