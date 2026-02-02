package reactor.sec12;

import reactor.common.Util;
import reactor.core.publisher.Sinks;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 03-02-2026
 */
public class Lec07Replay {

    public static void main(String[] args) {
        demo1();
        Util.sleepSeconds(2);
    }

    private static void demo1() {
        //We will try to store all the on a unbounded queue so that we able to give messages to late subscribers
//        var sink = Sinks.many().replay().all();

        //here late subscribers only see the last 1 messages and if we want we can pass Duration too
        var sink = Sinks.many().replay().limit(1);

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        //tryEmitNext is single threaded
        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);
        flux.subscribe(Util.subscriber("jake"));

        sink.tryEmitNext("new message");
    }
}
