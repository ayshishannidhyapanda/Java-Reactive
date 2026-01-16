package reactor.sec05;

import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 21-11-2025
 */
public class Lec07DefaultIfEmpty {

    public static void main(String[] args) {

        Mono.empty()
                .defaultIfEmpty("fallback")
                .subscribe(Util.subscriber());

        Mono.just("Ankit")
                .defaultIfEmpty("fallback")
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .filter(integer -> integer > 10)
                .defaultIfEmpty(500)
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .filter(integer -> integer > 9)
                .defaultIfEmpty(500)
                .subscribe(Util.subscriber());
    }
}
