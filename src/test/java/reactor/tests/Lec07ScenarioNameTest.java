package reactor.tests;

import org.junit.jupiter.api.Test;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

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
public class Lec07ScenarioNameTest {

    @Test
    public void scenarioNameTest1() {

        var options = StepVerifierOptions.create().scenarioName("1 to 3 items test");

        StepVerifier.create(getItems(), options)
                .expectNext(11)
                .as("First i will expect 11")
                .expectNext(2, 3)
                .as("then i will expect 2 and 3")
                .expectComplete()
                .verify();
    }

    private Flux<Integer> getItems() {
        return Flux.range(1, 3);
    }
}
