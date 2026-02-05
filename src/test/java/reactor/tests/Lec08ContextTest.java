package reactor.tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

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
public class Lec08ContextTest {

    private static final Logger log = LoggerFactory.getLogger(Lec08ContextTest.class);

    @Test
    public void unauthenticatedTest() {
        var options = StepVerifierOptions.create().withInitialContext(Context.empty());

        StepVerifier.create(getWelcomeMessage(), options)
                .expectErrorMessage("unauthenticated")
                .verify();
    }

    Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            log.info("{}", ctx);

            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s ".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    @Test
    public void welcomeMessageTest() {
        var options = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));

        StepVerifier.create(getWelcomeMessage(), options)
                .expectNext("Welcome sam ")
                .expectComplete()
                .verify();
    }
}
