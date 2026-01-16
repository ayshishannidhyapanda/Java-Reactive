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
public class Lec02HandleUtilAssignment {

    public static void main(String[] args) {
        Flux.<String>generate(synchronousSink ->
                        synchronousSink.next(Util.faker().country().name()))
                .handle((item, sink) -> {
                    sink.next(item);
                    if (item.equalsIgnoreCase("India")) {
                        sink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }
}
