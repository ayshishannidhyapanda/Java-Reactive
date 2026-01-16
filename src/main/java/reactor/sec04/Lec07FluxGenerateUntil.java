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
public class Lec07FluxGenerateUntil {

    public static void main(String[] args) {
        
        System.out.println("Without Take Until...");
        demo01();
        System.out.println();
        System.out.println("Take Until...");
        demo02();
    }

    private static void demo01() {
        Flux.generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("India")) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        Flux.<String>generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);

                })
                .takeUntil(c -> c.equalsIgnoreCase("India"))
                .subscribe(Util.subscriber());
    }
}
