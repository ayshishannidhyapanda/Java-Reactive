package reactor.sec01.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

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

public class SubscriberImpl1 implements Subscriber<String> {

    private static final Logger logger = LoggerFactory.getLogger(SubscriberImpl1.class);
    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String email) {
        logger.info("received: {}", email);
    }

    @Override
    public void onError(Throwable throwable) {

        logger.error("error", throwable);

    }

    @Override
    public void onComplete() {
        logger.info("Completed!");
    }
}
