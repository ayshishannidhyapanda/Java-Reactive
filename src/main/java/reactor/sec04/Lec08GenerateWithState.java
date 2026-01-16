package reactor.sec04;

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
 * Created on: 16-11-2025
 */
public class Lec08GenerateWithState {

    public static void main(String[] args) {

        Flux.generate(
                () -> 0,
                (counter, sink) -> {
                    var country = Util.faker().country().name();
                    sink.next(country);
                    counter++;
                    if (counter == 10 || country.equalsIgnoreCase("India")) {
                        sink.complete();
                    }
                    return counter;
                }
        ).subscribe(Util.subscriber());
    }
}
