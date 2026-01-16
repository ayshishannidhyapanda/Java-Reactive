package reactor.sec01.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
public class SubscriptionImpl1 implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl1.class);
    private static final int MAX_ITEMS = 10;
    private final Faker faker;
    private final Subscriber<? super String> subscriber;
    private boolean isCancelled;
    private int count = 0;

    public SubscriptionImpl1(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if (isCancelled) {
            return;
        }

        log.info("subscriber has requested {} items", requested);

        if (requested > MAX_ITEMS) {
            this.subscriber.onError(new RuntimeException("Validation Failed."));
            this.isCancelled = true;
            return;
        }

        for (int i = 0; i < requested && count < MAX_ITEMS; i++) {
            count++;
            this.subscriber.onNext(this.faker.internet().emailAddress());
        }
        if (count == MAX_ITEMS) {
            log.info("no more data can produced");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }

    }

    @Override
    public void cancel() {
        log.info("Subscriber has Cancelled");
        this.isCancelled = true;

    }
}
