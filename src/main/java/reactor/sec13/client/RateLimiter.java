package reactor.sec13.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
public class RateLimiter {

    private static final Map<String, Integer> categoryAttempts = Collections.synchronizedMap(new HashMap<>());

    static {
        refresh();
    }

    static <T> Mono<T> limitCalls() {
        return Mono.deferContextual(ctx -> {
            var allowCall = ctx.<String>getOrEmpty("category")
                    .map(RateLimiter::canAllow)
                    .orElse(false);
            return allowCall ? Mono.empty() : Mono.error(new RuntimeException("exceeded the given limit"));
        });
    }

    
    //we implemented a basic RateLimiter where the categoryAttempts map is mutated to control access.
    // However, this approach is not thread-safe as-is.
//    For a simple and effective fix, we can make the canAllow method synchronized to ensure atomic access:
    private synchronized static boolean canAllow(String category) {
        var attempts = categoryAttempts.getOrDefault(category, 0);
        if (attempts > 0) {
            categoryAttempts.put(category, attempts - 1);
            return true;
        }
        return false;
    }

    private static void refresh() {
        Flux.interval(Duration.ofSeconds(5))
                .startWith(0L)
                .subscribe(i -> {
                    categoryAttempts.put("standard", 2);
                    categoryAttempts.put("prime", 3);
                });
    }
}
