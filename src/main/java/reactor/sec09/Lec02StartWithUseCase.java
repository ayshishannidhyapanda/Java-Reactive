package reactor.sec09;

import reactor.common.Util;
import reactor.sec09.helper.NameGenerator;

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
public class Lec02StartWithUseCase {
    public static void main(String[] args) {
        var nameGenerator = new NameGenerator();

        nameGenerator.generateNames()
                .take(4)
                .subscribe(Util.subscriber("Ayshi"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Ankit"));

        nameGenerator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("Abhilash"));
    }
}
