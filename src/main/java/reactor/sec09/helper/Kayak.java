package reactor.sec09.helper;

import org.springframework.lang.NonNull;
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
public class Kayak {
    @NonNull
    public static Flux<Flight> getFlights() {
        return Flux.merge(
                        AmericanAirlines.getFlights(),
                        Emirates.getFlights(),
                        Qatar.getFlights()
                )
                .take(Duration.ofSeconds(5));

    }
}
