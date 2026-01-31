package reactor.sec10.assignment.buffer;

import reactor.common.Util;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 01-02-2026
 */
public record BookOrder(String genre,
                        String title,
                        Integer price) {

    public static BookOrder create() {

        var book = Util.faker().book();

        return new BookOrder(
                book.genre(),
                book.title(),
                Util.faker().random().nextInt(100, 1000)
        );
    }
}
