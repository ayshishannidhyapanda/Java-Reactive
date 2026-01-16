package reactor.common;

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

public class DefaultSubscriber<T> implements Subscriber<T> {

    private static final Logger logger = LoggerFactory.getLogger(DefaultSubscriber.class);
    private final String name;

    public DefaultSubscriber(String name) {
        this.name = name;
    }
    
    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        logger.info("{} received: {}", this.name, item);
    }

    @Override
    public void onError(Throwable throwable) {

        logger.error("{} error", this.name, throwable);

    }

    @Override
    public void onComplete() {
        logger.info("{} receive Completed!", this.name);
    }
}
