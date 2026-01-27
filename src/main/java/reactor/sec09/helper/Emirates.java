package reactor.sec09.helper;

import org.springframework.lang.NonNull;
import reactor.common.Util;
import reactor.core.publisher.Flux;

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
 * Created on: 26-01-2026
 */

//to represent the client class to call remote service
public class Emirates {

    private static final String AIRLINE = "Emirates";

    @NonNull
    public static Flux<Flight> getFlights() {
        return Flux
                .range(1, Util.faker().random().nextInt(2, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200, 1000)))
                .map(integer -> new Flight(AIRLINE, Util.faker().random().nextInt(300, 1000)))
                .transform(Util.fluxUnaryOperator(AIRLINE));
    }
}
