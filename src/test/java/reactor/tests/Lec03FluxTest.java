package reactor.tests;

import org.junit.jupiter.api.Test;
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
 * Created on: 04-02-2026
 */
public class Lec03FluxTest {
    @Test
    public void fluxTest1() {
        StepVerifier.create(getItems(), 1)
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    private Flux<Integer> getItems() {
        return Flux.just(1, 2, 3)
                .log();
    }

    //verifyComplete is equal to expectComplete() + verify()

    @Test
    public void fluxTest2() {
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void fluxTest3() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }
}
