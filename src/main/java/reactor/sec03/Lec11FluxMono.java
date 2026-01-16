package reactor.sec03;

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
 * Created on: 14-11-2025
 */
public class Lec11FluxMono {

    public static void main(String[] args) {

        // converting flux to mono
        var flux = Flux.range(1, 10);
        flux.next()
                .subscribe(Util.subscriber());

        //or Mono.from(flux).subscribe(Util.subscriber());
    }

    private static void monoToFlux() {
        var mono = getUsername(1);
        //converting mono to flux
        save(Flux.from(mono));
    }


    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Ankit");
            case 2 -> Mono.empty(); //Similar to Null
            default -> Mono.error(new RuntimeException("Invalid input"));
        };
    }

    private static void save(Flux<String> flux) {
        flux.subscribe(Util.subscriber());
    }
}
