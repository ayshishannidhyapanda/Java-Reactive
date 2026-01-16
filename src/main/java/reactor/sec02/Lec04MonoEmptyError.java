package reactor.sec02;

import reactor.common.Util;
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
 * Created on: 08-11-2025
 */
public class Lec04MonoEmptyError {

    public static void main(String[] args) {
        getUsername(1)
                .subscribe(Util.subscriber());

        getUsername(2)
                .subscribe(Util.subscriber());

//        getUsername(3)
//                .subscribe(Util.subscriber());

        getUsername(3)
                .subscribe(
                        s -> System.out.println(s),
                        err -> {
                        } //Error Handler
                );

    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Ankit");
            case 2 -> Mono.empty(); //Similar to Null
            default -> Mono.error(new RuntimeException("Invalid input"));
        };
    }

}
