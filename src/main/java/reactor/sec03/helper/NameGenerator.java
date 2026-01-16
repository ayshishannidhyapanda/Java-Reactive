package reactor.sec03.helper;

import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

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
public class NameGenerator {

    public static List<String> getNamesList(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> {
                    try {
                        return generateName();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public static Flux<String> getNamesFlux(int count) {
        return Flux.range(1, count)
                .map(i -> {
                    try {
                        return generateName();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static String generateName() throws InterruptedException {
        Util.sleepSeconds(1);
        return Util.faker().name().firstName();
    }

}
