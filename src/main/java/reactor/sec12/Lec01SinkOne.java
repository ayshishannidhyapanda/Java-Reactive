package reactor.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//Sink is a hot publisher
public class Lec01SinkOne {

    private static final Logger log = LoggerFactory.getLogger(Lec01SinkOne.class);

    public static void main(String[] args) {

        demo3();
    }

    private static void demo3() {

        //here we have N number of Subscriber
        var sink = Sinks.one(); //We can create a sink which can emit only ONE item, which is a mono type.
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("Sam"));

        //Successfully emitted
        sink.emitValue("hi", (signal, result) -> {
            log.info("hi");
            log.info(signal.name());
            log.info(result.name());
            return false;
        });

        //Gives error because only item can emit
        sink.emitValue("hello", (signal, result) -> {
            log.info("hello");
            log.info(signal.name());
            log.info(result.name());
            return false; //if true it will retry it infinitely, till emit value
        });
    }

    private static void demo1() {
        //Subscriber
        var sink = Sinks.one(); //We can create a sink which can emit only ONE item, which is a mono type.

        var mono = sink.asMono();
        mono.subscribe(Util.subscriber());

//        sink.tryEmitValue("Hi");
//        sink.tryEmitEmpty();

        sink.tryEmitError(new RuntimeException());
    }

    //We can have multiple subscriber
    private static void demo2() {
        //Subscriber
        var sink = Sinks.one(); //We can create a sink which can emit only ONE item, which is a mono type.

        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("Sam"));
        mono.subscribe(Util.subscriber("Mike"));
        sink.tryEmitValue("Hi");
//        sink.tryEmitEmpty();

//        sink.tryEmitError(new RuntimeException());
    }
}
