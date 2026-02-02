package reactor.sec12;

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

import reactor.common.Util;
import reactor.core.publisher.Sinks;

//We can emit multiple messages, but there will be only one subscriber
//if subscriber is late on subscribing it will store data on memory and emit after subscriber subscribe
public class Lec02UnicastSink {

    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        //onBackpressureBuffer is a bounded queue which helps to store value on memory
        // handle through which we pushed item
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        //handle through which subscriber will receive item
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("sam"));

    }

    private static void demo2() { //onBackpressureBuffer is a bounded queue which helps to store value on memory
        // handle through which we pushed item
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        //handle through which subscriber will receive item
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

    }
}
