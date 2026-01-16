package reactor.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.sec04.helper.NameGenerator;

import java.util.ArrayList;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 15-11-2025
 */
//flux sink is thread safe
public class Lec03FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    public static void main(String[] args) throws InterruptedException {

        demo01();
        demo02();
    }

    private static void demo01() throws InterruptedException {
        var list = new ArrayList<Integer>();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 10; i++) {
//            new Thread(runnable).start();
            Thread.ofPlatform().start(runnable);
        }
        //since we are creating multiple threads we have to pause main thread so that other threads will do there task
        Util.sleepSeconds(3);
        log.info("List size {}", list.size());
    }

    //once flux sink get the item it don't lose it, and transfer the item to subscriber sequentially one by one
    private static void demo02() throws InterruptedException {
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for (int i = 0; i < 10; i++) {
//            new Thread(runnable).start();
            Thread.ofPlatform().start(runnable);
        }

        //since we are creating multiple threads we have to pause main thread so that other threads will do there task
        Util.sleepSeconds(3);
        log.info("List size {}", list.size());

    }

}
