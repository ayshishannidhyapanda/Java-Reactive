package reactor.sec01;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 21-10-2025
 */

import reactor.sec01.publisher.PublisherImpl1;
import reactor.sec01.subscriber.SubscriberImpl1;

import java.time.Duration;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
//        demo2();
//        demo3();
        demo4();
    }

    private static void demo4() throws InterruptedException {
        var publisher = new PublisherImpl1();
        var subscriber = new SubscriberImpl1();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(9);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
    }

    private static void demo1() {
        var publisher = new PublisherImpl1();
        var subscriber = new SubscriberImpl1();
        publisher.subscribe(subscriber);
    }

    private static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl1();
        var subscriber = new SubscriberImpl1();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
    }

    private static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl1();
        var subscriber = new SubscriberImpl1();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(5);
    }
}
