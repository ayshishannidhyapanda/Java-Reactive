package reactor.sec03;

import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

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
public class Lec04FluxFromStream {

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4);
        var stream = list.stream(); // Java stream has to be consumed only once

//        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);

//        var flux = Flux.fromStream(stream); // for only one subscriber

        var flux = Flux.fromStream(() -> list.stream());// here we used supplier of stream for using multiple subscriber

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
