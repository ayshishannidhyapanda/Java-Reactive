package reactor.sec10;

import reactor.common.Util;
import reactor.core.publisher.Flux;
import reactor.sec10.assignment.buffer.BookOrder;
import reactor.sec10.assignment.buffer.RevenueReport;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
public class Lec02BufferAssignment {
    public static void main(String[] args) {
        var allowedCategories = Set.of(
                "Sci-Fi",
                "Fantasy",
                "Psychological Thriller"
        );
        orderStream()
                .filter(o -> allowedCategories.contains(o.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(Lec02BufferAssignment::generateReport)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<BookOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> BookOrder.create());
    }

    private static RevenueReport generateReport(List<BookOrder> orderList) {
        var revenue = orderList.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::genre,
                        Collectors.summingInt(BookOrder::price)
                ));

        return new RevenueReport(LocalTime.now(), revenue);
    }
}
