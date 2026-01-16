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
 * Created on: 12-11-2025
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.sec02.client.ExternalServiceClient;

public class Lec11NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) throws InterruptedException {

        var client = new ExternalServiceClient();

        log.info("Starting...");

        for (int i = 1; i <= 50; i++) {
            client.getProductName(i)
//                    .block(); // it will run sequentially, and it will not process the next number until it get
//            the previous number response
                    .subscribe(Util.subscriber());

        }

        Util.sleepSeconds(2);
    }

}
