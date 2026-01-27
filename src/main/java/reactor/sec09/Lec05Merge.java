package reactor.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 25-01-2026
 */
public class Lec05Merge {
    private final static Logger log = LoggerFactory.getLogger(Lec05Merge.class);

    public static void main(String[] args) {

        demo1();
        demo2();

        Util.sleepSeconds(3);
    }

    private static void demo1() {
        Flux
                .merge(producer3(), producer1(), producer2()) //in merge() there is no specific order for merging
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer2() //in mergeWith() there is no specific order for merging too
                .mergeWith(producer1())
                .mergeWith(producer3())
                .subscribe(Util.subscriber());
    }

    @NonNull
    private static Flux<Integer> producer3() {
        return Flux.just(11, 12, 13)
                .transform(Util.fluxUnaryOperator("producer3"))
                .delayElements(Duration.ofMillis(10));
    }

    @NonNull
    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .transform(Util.fluxUnaryOperator("producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    @NonNull
    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .transform(Util.fluxUnaryOperator("producer2"))
                .delayElements(Duration.ofMillis(10));
    }
}
