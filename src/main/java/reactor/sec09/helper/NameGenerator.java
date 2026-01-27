package reactor.sec09.helper;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 24-01-2026
 */
public class NameGenerator {

    private static final Logger log = LoggerFactory.getLogger(NameGenerator.class);
    private final List<String> redis = new ArrayList<>(); // for demo

    public Flux<String> generateNames() {

        return Flux.generate(synchronousSink -> {
                    log.info("Generating names...");
                    Util.sleepSeconds(1);
                    Faker faker = Faker.instance();
                    var name = faker.name().firstName();
                    redis.add(name);
                    synchronousSink.next(name);
                })
                .startWith(redis)
                .cast(String.class);
    }
}
