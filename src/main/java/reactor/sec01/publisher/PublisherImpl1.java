package reactor.sec01.publisher;

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

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class PublisherImpl1 implements Publisher<String> {
    @Override
    public void subscribe(Subscriber<? super String> subscriber) {

        var subscription = new SubscriptionImpl1(subscriber);

        subscriber.onSubscribe(subscription);
    }
}
