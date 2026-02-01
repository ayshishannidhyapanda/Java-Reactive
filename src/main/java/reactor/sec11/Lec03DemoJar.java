package reactor.sec11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.sec11.client.ExternalServiceClient;
import reactor.sec11.client.ServerError;
import reactor.util.retry.Retry;

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
 * Created on: 01-02-2026
 */
public class Lec03DemoJar {
    private static final Logger log = LoggerFactory.getLogger(Lec03DemoJar.class);

    public static void main(String[] args) {

        retry();

        Util.sleepSeconds(60);
    }

    private static void retry() {
        var client = new ExternalServiceClient();
        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Retry retryOnServerError() {
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(retrySignal -> log.info("Retrying {} time", retrySignal.failure().getMessage()));
    }

    private static void repeat() {
        var client = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber("Ashish"));
    }
}
