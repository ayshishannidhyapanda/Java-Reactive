package reactor.sec09;

import reactor.common.Util;
import reactor.sec09.applications.OrderService;
import reactor.sec09.applications.User;
import reactor.sec09.applications.UserService;

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
public class Lec11FluxFlatMap {
    public static void main(String[] args) {
        //Get all the orders from Order Service
        //we don't have direct endpoints for get all order
        //so we have to first fetch all the username then we can fetch all orders of them

        //here map is defining Flux<Flux<Order>>
        /*UserService.getAllUsers()
                .map(user -> OrderService.getUserOrders(user.id()))
                .subscribe(Util.subscriber());*/

        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getUserOrders, 1)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);
    }
}
