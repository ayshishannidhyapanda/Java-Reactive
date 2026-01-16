package reactor.sec02;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 01-11-2025
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Lec01LazyStream {

    private static final Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);

    public static void main(String[] args) {

        Stream.of(1)
                .peek(i -> log.info("received : {}", i))
                .toList();// w/o this we cant execute this LAZYSTREAM

    }

}
