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
 * Created on: 19-01-2026
 */

//We can have multiple SubscribeOn.
//The closest to the source will take the precedence
public class Lec03MultipleSubscribeOn {
    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribeOn.class);

    public static void main(String[] args) throws InterruptedException {
        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", 1);
                        sink.next(i);
                    }
                    sink.complete();
                })
                //we can have multiple subscribeOn method but the closest one to the producer end up doing all the task
                //in this case Ankit is the closest one
//                .subscribeOn(Schedulers.newParallel("Ankit"))

                //Lets Use immediate()
                .subscribeOn(Schedulers.immediate()) //it just continues the previous here that is bounded elastic
                .doOnNext(v -> log.info("value: {}", v))//run by Ankit-1 thread which comes from newParallel
                .doFirst(() -> log.info("first1"))//run by bounded elastic thread
                .subscribeOn(Schedulers.boundedElastic())//bounded elastic activate
                .doFirst(() -> log.info("first2")); //thread-0 runnable


        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(2);
    }
}
