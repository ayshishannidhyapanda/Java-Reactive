package reactor.sec07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import reactor.common.Util;
import reactor.sec07.client.ExternalServiceClient;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 22-01-2026
 */
public class Lec06EventLoopIssueFix {

    private static final Logger log = LoggerFactory.getLogger(Lec06EventLoopIssueFix.class);

    public static void main(String[] args) throws InterruptedException {
        var client = new ExternalServiceClient();

        log.info("Starting...");

//        for (int i = 1; i <= 5; i++) {
//            client.getProductName(i)
//                    .<String>handle((m, sink) -> {
//                        try {
//                            sink.next(process(m));
//                        } catch (InterruptedException e) {
//                            sink.error(new RuntimeException(e));
//                        }
//                    })
//                    .subscribe(Util.subscriber());
//
//        }

        for (int i = 1; i <= 5; i++) {
            client.getProductName(i)
                    .map(m -> {
                        try {
                            return process(m);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .subscribe(Util.subscriber());

        }

        Util.sleepSeconds(20);
    }

    @NonNull
    private static String process(String input) throws InterruptedException {
        Util.sleepSeconds(1);
        return input + "-processed";
    }

}
