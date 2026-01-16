package reactor.sec03.assignment;

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
 * Created on: 15-11-2025
 */
public class StockPriceObserver implements Subscriber<Integer> {

    private static final Logger log = LoggerFactory.getLogger(StockPriceObserver.class);
    private int quantity = 0;
    private int balance = 1000;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {

        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }
    
    @Override
    public void onNext(Integer price) {
        if (price < 90 && balance >= price) {
            quantity++;
            balance -= price;
            log.info("bought a stock at {}, total quantity {}, remaining balance {}", price, quantity, balance);
        } else if (price > 110 && quantity > 0) {
            log.info("Selling {} quantities at {}", quantity, price);
            balance += (quantity + price);
            quantity = 0;
            subscription.cancel();
            log.info("profit : {}", (balance - 1000));
        }
    }


    @Override
    public void onError(Throwable throwable) {
        log.error("error", throwable);
    }

    @Override
    public void onComplete() {
        log.info("Completed.");
    }
}
