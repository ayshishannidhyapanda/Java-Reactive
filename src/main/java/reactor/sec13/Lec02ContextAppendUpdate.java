package reactor.sec13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Locale;

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

//Context is immutable map we can append additional info!
public class Lec02ContextAppendUpdate {
    private static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(ctx -> Context.of("user", "mike"))// it will override user = sam, a = b, c = d
                .contextWrite(ctx -> ctx.delete("c")) //it will delete c = d
                .contextWrite(
                        ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("a", "b").put("c", "d"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
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

    private static void append() {
        getWelcomeMessage()
                .contextWrite(Context.of("a", "b").put("c", "d"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }
}
