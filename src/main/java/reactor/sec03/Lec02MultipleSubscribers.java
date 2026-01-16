package reactor.sec03;

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
 * Created on: 14-11-2025
 */
public class Lec02MultipleSubscribers {

    public static void main(String[] args) {

        var flux = Flux.just(1, 2, 3, 4, 5, 6);

        flux.subscribe(Util.subscriber("Sub1"));

        flux.filter(integer -> integer > 7)
                .subscribe(Util.subscriber("Sub2"));

        flux.filter(i -> i % 2 == 0)
                .map(i -> i + "ankit")
                .subscribe(Util.subscriber("Sub3"));


    }
}
