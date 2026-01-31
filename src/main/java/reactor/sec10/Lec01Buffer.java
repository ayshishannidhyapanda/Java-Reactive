package reactor.sec10;

import reactor.common.Util;
import reactor.core.publisher.Flux;

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

//collect item based on given interval
public class Lec01Buffer {

    public static void main(String[] args) {
        demo01();
        demo02();
        demo03();
        demo04();

        Util.sleepSeconds(5);
    }

    private static void demo01() {
        eventStream()
                //integer - max value or the source has to complete
                .buffer()
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        eventStream()
                //every 3 items and give me as a list
                .buffer(3)
                .subscribe(Util.subscriber());
    }

    private static void demo03() {
        eventStream()
                //every 500ms
                .buffer(Duration.ofMillis(500))
                .subscribe(Util.subscriber());
    }

    private static void demo04() {
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(2))
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never())
                .map(i -> "event " + (i + 1));
    }
}
