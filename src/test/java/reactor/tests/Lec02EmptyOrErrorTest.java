package reactor.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
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

public class Lec02EmptyOrErrorTest {

    @Test
    public void productTest() {
        StepVerifier.create(getUsername(1))
                .expectNext("Ankit")
                .expectComplete()
                .verify();//now it will verify the test(subscribe)
    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Ankit");
            case 2 -> Mono.empty(); //Similar to Null
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

    @Test
    public void errorTest1() {
        StepVerifier.create(getUsername(3))
                .expectError()
                .verify();//now it will verify the test(subscribe)
    }

    @Test
    public void errorTest2() {
        StepVerifier.create(getUsername(3))
                .expectError(RuntimeException.class)
                .verify();//now it will verify the test(subscribe)
    }

    @Test
    public void errorTest3() {
        StepVerifier.create(getUsername(3))
                .expectErrorMessage("invalid input")
                .verify();//now it will verify the test(subscribe)
    }

    @Test
    public void errorTest4() {
        StepVerifier.create(getUsername(3))
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(RuntimeException.class, ex.getClass());
                    Assertions.assertEquals("invalid input", ex.getMessage());
                })
                .verify();//now it will verify the test(subscribe)
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUsername(2))
                .expectComplete()
                .verify();//now it will verify the test(subscribe)
    }
}
