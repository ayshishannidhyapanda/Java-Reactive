package reactor.sec13;

import reactor.common.Util;
import reactor.sec13.client.ExternalServiceClient;
import reactor.util.context.Context;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 04-02-2026
 */
public class Lec04ContextRateLimiterDemo {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for (int i = 0; i < 20; i++) {
            client
                    .getBook()
//                    .contextWrite(Context.of("user", "sam"))
                    .contextWrite(Context.of("user", "mike"))
//                    .contextWrite(Context.of("category", "prime")) //error
                    .subscribe(Util.subscriber());
            Util.sleepSeconds(1);
        }

        Util.sleepSeconds(5);
    }
}
