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
public class Lec10TimeoutTest {

    @Test
    public void timeoutTest() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify(Duration.ofMillis(1500));//test has to complete within 500 milliseconds so it will timeout
        //if we pass more than 1000 millisec then it will pass the test for example lets take 1500 mSec
    }

    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofMillis(200));
    }
}
