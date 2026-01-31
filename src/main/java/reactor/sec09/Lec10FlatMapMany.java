package reactor.sec09;

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

import reactor.common.Util;
import reactor.sec09.applications.OrderService;
import reactor.sec09.applications.UserService;

//Mono is supposed to be 1 item - what if the flatMap returns multiple items!?
public class Lec10FlatMapMany {
    public static void main(String[] args) {
        //  we have username, get all user orders!

        //here .map() becomes Mono<Flux<String>>
        /*UserService.getUserId("sam")
                .map(userId -> OrderService.getOrders(userId))
                .subscribe(Util.subscriber());*/

        //now due to flatMapMany becomes Flux<Order> and if we use flatMap it becomes Mono<Object> which is wrong.
        UserService.getUserId("sam")
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);

    }
}
