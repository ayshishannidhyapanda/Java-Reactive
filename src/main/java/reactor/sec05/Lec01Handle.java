package reactor.sec05;

import reactor.common.Util;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 17-11-2025
 */

/*
    Handle behaves like filter + map
    1 => -2
    4 => do not send
    7 => error
    everything else => send as it is
*/
public class Lec01Handle {

    public static void main(String[] args) {

//        Flux<Integer> flux = Flux.range(1, 10);
//        Flux<Integer> flux1 = flux.handle((item, sink) -> {
//            sink.error(new RuntimeException("Error"));
//
//        });
//
//        flux.subscribe(Util.subscriber());
//        flux1.subscribe(Util.subscriber());

        Flux.range(1, 10)
                .filter(integer -> integer != 7)
                .handle((item, sink) -> {
                    switch (item) {
                        case 1 -> sink.next(-2);
                        case 4 -> {
                        }
                        case 7 -> sink.error(new RuntimeException("Runtime Error"));
                        default -> sink.next(item);

                    }
                })
                .cast(Integer.class)
                .subscribe(Util.subscriber());
    }
}
