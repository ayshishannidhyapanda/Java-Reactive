package reactor.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.time.Duration;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 08-11-2025
 */
public class Util {

    private static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

//    public static void main(String[] args) {
//        var mono = Mono.just(1);
//        mono.subscribe(subscriber("sub1"));
//        mono.subscribe(subscriber("sub2"));
//        mono.subscribe(subscriber());
//    }

    public static Faker faker() {
        return faker;
    }

    public static void sleepSeconds(int seconds) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(seconds));
    }
}
