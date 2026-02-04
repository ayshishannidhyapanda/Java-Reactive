package reactor.tests;

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

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

//To write simple test using StepVerifier
// StepVerifier acts like subscriber
public class Lec01MonoTest {
    private final static Logger log = LoggerFactory.getLogger(Lec01MonoTest.class);

    @Test
    public void productTest() {
        StepVerifier.create(getProduct(1))
                .expectNext("product-1")
                .expectComplete()
                .verify();//now it will verify the test(subscribe)
    }

    private Mono<String> getProduct(int id) {
        return Mono.fromSupplier(() -> "product-" + id)
                .doFirst(() -> log.info("invoked"));
    }
}
