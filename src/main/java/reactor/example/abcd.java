package reactor.example;

import java.util.concurrent.CompletableFuture;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 30-01-2026
 */
public class abcd {
    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 100000; j++) {
                int finalI = i;
                int finalJ = j;
                CompletableFuture.supplyAsync(() -> Thread.ofPlatform().start(() -> {
                    while (true) {

                    }
                }));
            }
        }

    }
}
