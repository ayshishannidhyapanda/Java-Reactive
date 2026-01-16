package reactor.sec03;

import reactor.common.Util;
import reactor.sec03.client.ExternalServiceClient;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 14-11-2025
 */
public class Lec08NonBlockingStreamingMessages {

    public static void main(String[] args) throws InterruptedException {

        var client = new ExternalServiceClient();

        client.getNames()
                .subscribe(Util.subscriber("Sub1"));

        client.getNames()
                .subscribe(Util.subscriber("Sub2"));

        Util.sleepSeconds(6);
    }
}
