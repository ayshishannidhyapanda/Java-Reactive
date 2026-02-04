package reactor.sec13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
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
 * Created on: 04-02-2026
 */
public class Lec03ContextPropagation {

    private static final Logger log = LoggerFactory.getLogger(Lec03ContextPropagation.class);

    public static void main(String[] args) {

        getWelcomeMessage()
                //now producer2 dont get the values
                .concatWith(Flux.merge(producer1(),
                        producer2().contextWrite(ctx -> Context.empty())))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            log.info("{}", ctx);

            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s ".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("Unauthenticated"));
        });
    }

    private static Mono<String> producer1() {
        return Mono.deferContextual(ctx -> {
                    log.info("producer1, {}", ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.boundedElastic())
                .cast(String.class);
    }

    private static Mono<String> producer2() {
        return Mono.deferContextual(ctx -> {
                    log.info("producer2, {}", ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.parallel())
                .cast(String.class);
    }
}
