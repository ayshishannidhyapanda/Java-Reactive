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
 * Created on: 02-02-2026
 */
public class Lec04Multicast {

    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        //onBackpressureBuffer is a bounded queue which helps to store value on memory
        // handle through which we pushed item
        var sink = Sinks.many().multicast().onBackpressureBuffer();

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

    //warm-up behaviour
    private static void demo2() {
        //onBackpressureBuffer is a bounded queue which helps to store value on memory
        // handle through which we pushed item
        var sink = Sinks.many().multicast().onBackpressureBuffer();// by sending value we can change the queue size

        var flux = sink.asFlux();

        //tryEmitNext is single threaded
        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        //When you try to emit messages and there are no subscriber then the messages goes to onBackpressureBuffer queue
        //which bounded queue so that when sam join, messages goes to very first subscriber
        //then new messages goes to every subscriber.

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        flux.subscribe(Util.subscriber("jake"));

        sink.tryEmitNext("new message");
    }
}
