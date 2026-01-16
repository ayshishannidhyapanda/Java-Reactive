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
public class Lec05TakeOperator {

    public static void main(String[] args) {
        System.out.println("Take....");
        take();
        System.out.println();
        System.out.println("Take While...");
        takeWhile();
        System.out.println();
        System.out.println("Take Until...");
        takeUntil();
    }

    private static void take() {
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile() {
        Flux.range(1, 10)
                .log("take")
                .takeWhile(i -> i < 5)// stop when the condition is not met
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeUntil() {
        Flux.range(1, 10)
                .log("take")
                .takeUntil(integer -> integer < 5)// stop when the condition is met + allow the last item which satisfy the condition
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
