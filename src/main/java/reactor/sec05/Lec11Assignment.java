package reactor.sec05;

import reactor.common.Util;
import reactor.sec05.assignment.ExternalServiceClient;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 21-11-2025
 */
public class Lec11Assignment {

    public static void main(String[] args) throws InterruptedException {

        var client = new ExternalServiceClient();

        for (int i = 1; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(5);
    }
}
