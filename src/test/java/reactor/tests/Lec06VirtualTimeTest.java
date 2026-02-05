package reactor.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

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
 * Created on: 05-02-2026
 */
public class Lec06VirtualTimeTest {

    //We cant run like this it will take 50sec
    @Test
    public void demo() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(10));
    }

    @Test
    public void virtualTimeTest1() {
        StepVerifier.withVirtualTime(() -> getItems())
                //Take this producer and simulate after 51 sec
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();

    }

    @Test
    public void virtualTimeTest2() {
        StepVerifier.withVirtualTime(this::getItems)
                //we expect subscription now but expectNoEvent for first 9 seconds
                .expectSubscription()
                //First 9 second
                .expectNoEvent(Duration.ofSeconds(9))
                //then wait for 1 sec
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                //then wait for 40 sec
                .thenAwait(Duration.ofSeconds(40))
                .expectNext(2, 3, 4, 5)
                .expectComplete()
                .verify();

    }
}
