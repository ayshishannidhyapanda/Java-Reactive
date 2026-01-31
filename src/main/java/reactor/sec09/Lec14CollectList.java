package reactor.sec09;

import reactor.common.Util;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 31-01-2026
 */

//to collect the items received via flux assuming we will have finite item
public class Lec14CollectList {

    public static void main(String[] args) {

        //we get everything as list

        Flux.range(1, 10)
                .collectList()
                .subscribe(Util.subscriber());
    }
}
