package reactor.sec07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

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

//Ofter times you really do not need this!
// - prefer non-blocking IO for network calls
public class Lec08Parallel {

    private static final Logger log = LoggerFactory.getLogger(Lec08Parallel.class);

    public static void main(String[] args) throws InterruptedException {

        //Always use non/blocking IO for Networking Calls

        Flux.range(1, 10)
                // in parallel() pass integer you want to perform that many task simultaneously only
                .parallel()
                //both runOn() and parallel() used for REAL Parallel Execution
                .runOn(Schedulers.parallel())
                .map(m -> {
                    try {
                        return process(m);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
//                .sequential()
                .map(i -> i + "a")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);

    }

    private static int process(int i) throws InterruptedException {
        log.info("Time Consuming Task {}", i);
        Util.sleepSeconds(1);
        return i * 2;
    }
}
