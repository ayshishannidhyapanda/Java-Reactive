package reactor.sec02;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 01-11-2025
 */

import reactor.core.publisher.Mono;
import reactor.sec01.subscriber.SubscriberImpl1;

public class Lec02MonoJust {

    public static void main(String[] args) {

        Mono<String> mono = Mono.just("Ankit");
        System.out.println(mono);

        var subscriber = new SubscriberImpl1();

        mono.subscribe(subscriber);

        subscriber.getSubscription().request(10);

        // adding these will have no effect as producer already sent complete
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();


    }

}
