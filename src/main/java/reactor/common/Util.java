package reactor.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.UnaryOperator;

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

    private final static Logger log = LoggerFactory.getLogger(Util.class);

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

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxUnaryOperator(String name) {
        return tFlux -> tFlux
                .doOnSubscribe(subscription -> log.info("Subscribing {}", name))
                .doOnCancel(() -> log.info("Cancelling {}", name))
                .doOnComplete(() -> log.info("{} Completed", name));
    }
}
