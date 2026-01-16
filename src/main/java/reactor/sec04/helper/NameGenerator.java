package reactor.sec04.helper;

import reactor.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

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
public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        System.out.println("got a flux sink");
        this.sink = stringFluxSink;
    }

    public void generate() {
        this.sink.next(Util.faker().name().firstName());
    }

}
