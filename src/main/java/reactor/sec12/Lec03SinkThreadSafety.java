package reactor.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 02-02-2026
 */
public class Lec03SinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03SinkThreadSafety.class);

    public static void main(String[] args) {

        demo1();
        demo2();
    }

    private static void demo1() {

        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        //Arraylist is not thread safe intentionally chosen to for demo
        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(j);
            });

        }

        Util.sleepSeconds(3);

        log.info("Size of list: {}", list.size());
    }

    private static void demo2() {

        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        var list = new ArrayList<>();
        flux.subscribe(list::add);

        //This is thread safe
        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                //emitNext is multithreaded
                sink.emitNext(j, (signal, result) -> {
                    //I'm trying to deliver the messages, but somehow I couldn't. meaning of FAIL_NON_SERIALIZED.
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(result);
                });
            });

        }
        Util.sleepSeconds(3);

        log.info("Size of list: {}", list.size());
    }
}
