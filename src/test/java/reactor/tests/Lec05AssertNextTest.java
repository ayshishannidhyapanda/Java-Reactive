package reactor.tests;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 05-02-2026
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

//AssertNext is a method in stepVerifier
//assertNext = ConsumeNextWith
//We can also collect all the items and test.
public class Lec05AssertNextTest {

    @Test
    public void assertNextTest() {
        StepVerifier.create(getBooks())
                .assertNext(b -> Assertions.assertEquals(1, b.id))
                .thenConsumeWhile(b -> Objects.nonNull(b.title()))
                .expectComplete()
                .verify();
    }

    private Flux<Book> getBooks() {
        return Flux.range(1, 3)
                .map(i -> new Book(i, Util.faker().book().author(), Util.faker().book().title()));
    }

    @Test
    public void collectAllAndTest() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .expectComplete()
                .verify();
    }

    record Book(int id, String author, String title) {
    }
}
