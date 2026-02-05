package reactor.tests;

import org.junit.jupiter.api.Test;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

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

public class Lec04RangeTest {

    @Test
    public void rangeTest1() {
        StepVerifier.create(getItems(), 50)
                .expectNext(1, 2, 3)
                .expectNextCount(47) //OnNext() is invoked 47 times
                .expectComplete()
                .verify();
    }

    private Flux<Integer> getItems() {
        return Flux.range(1, 50);
    }

    @Test
    public void rangeTest2() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectNextCount(22)
                .expectNext(26, 27, 28)
                .expectNextCount(22)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest3() {
        StepVerifier.create(getRandomItems(), 50)
                .expectNextMatches(i -> i > 0 && i < 101)
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

    private Flux<Integer> getRandomItems() {
        return Flux.range(1, 50)
                .map(i -> Util.faker().random().nextInt(1, 100));
    }

    @Test
    public void rangeTest4() {
        StepVerifier.create(getRandomItems(), 50)
                .thenConsumeWhile(i -> i > 0 && i < 101)
                .expectComplete()
                .verify();
    }
}
