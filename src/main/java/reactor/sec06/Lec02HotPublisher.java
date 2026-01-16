package reactor.sec06;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created on: 16-01-2026
 */
public class Lec02HotPublisher {
    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) throws InterruptedException {

//        var movieFlux = movieStream(); //Its just Streams

//        var movieFlux = movieStream().share(); //now it works like movie theatre

        //min subscriber needs to start emitting data, minimum should be 1
//        var movieFlux = movieStream().share().publish().refCount(2);

        var movieFlux = movieStream().share();


        Util.sleepSeconds(2);

        movieFlux
//                .take(4)//ankit left after 4 scene
                .take(1)
                .subscribe(Util.subscriber("ankit"));

        Util.sleepSeconds(3);
// when we dont have enough subscriber
        movieFlux
                .take(3) //mike left after 3 scene
                .subscribe(Util.subscriber("mike"));

        //after both left the movie will end the stream

        Util.sleepSeconds(15);
    }

    //Movie theatre
    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("received the request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "Movie scene " + state;
                            log.info("playing {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
