package reactor.sec09;

import reactor.common.Util;
import reactor.sec09.helper.Kayak;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 26-01-2026
 */

public class Lec06MergeUseCase {

    public static void main(String[] args) {
        Kayak.getFlights()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
}
