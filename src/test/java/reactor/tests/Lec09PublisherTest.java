package reactor.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 05-02-2026
 */
public class Lec09PublisherTest {


    @Test
    public void publisherTest2() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "b", "c"))
                .expectComplete()
                .verify();
    }

    private UnaryOperator<Flux<String>> processor() {
        return flux -> flux
                .filter(s -> s.length() > 1)
                .map(s -> s.toUpperCase())
                .map(s -> s + ":" + s.length());
    }

    @Test
    public void publisherTest1() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

//        publisher.emit("hi", "hello");

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("hi", "hello"))
                .expectNext("HI:2")
                .expectNext("HELLO:5")
                .expectComplete()
                .verify();


//        flux.subscribe(Util.subscriber());


//        publisher.next("a", "b");
//        publisher.complete();

//        publisher.emit("a", "b");
    }
}
