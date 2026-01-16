package reactor.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;


/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 16-11-2025
 */

// Flux generate
//    - invokes the given lambda expression again and again based on downstream demand.
//    - We can emit only one value at a time
//    - will stop when complete method is invoked
//    - will stop when error method is invoked
//    - will stop downstream cancels

public class Lec06FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
                    log.info("invoked");
                    synchronousSink.next(1);
//            synchronousSink.next(2); //we can emit only one value
//            synchronousSink.complete();

                })
                .take(4)
                .subscribe(Util.subscriber());
    }
}
