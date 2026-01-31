package reactor.sec09;

import reactor.common.Util;
import reactor.core.publisher.Flux;
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
 * Created on: 31-01-2026
 */
public class Lec12FluxFlapMapAssignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        Flux.range(1, 10)
                .flatMap(client::getProduct, 3)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }
}
