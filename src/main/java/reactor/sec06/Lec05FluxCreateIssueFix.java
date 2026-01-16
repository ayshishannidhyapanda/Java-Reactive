package reactor.sec06;

import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.sec04.helper.NameGenerator;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 16-01-2026
 */
public class Lec05FluxCreateIssueFix {

    public static void main(String[] args) {

        var generator = new NameGenerator();
        var flux = Flux.create(generator).share(); //Hot Publisher
        
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }

    }
}
