package reactor.sec09;

import reactor.common.Util;
import reactor.sec09.client.ExternalServiceClient;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 30-01-2026
 */
public class Lec08ZipAssignment {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 0; i < 10; i++) {
            client.getProduct(i)
                    .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(2);
    }
}
