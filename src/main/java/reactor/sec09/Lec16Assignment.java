package reactor.sec09;

import reactor.common.Util;
import reactor.core.publisher.Mono;
import reactor.sec09.applications.*;

import java.util.List;

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
public class Lec16Assignment {
    public static void main(String[] args) {
        UserService.getAllUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(
                        PaymentService.getUserBalance(user.id()),
                        OrderService.getUserOrders(user.id()).collectList()
                )
                .map(t -> new UserInformation(
                        user.id(),
                        user.userName(),
                        t.getT1(),
                        t.getT2()));
    }

    record UserInformation(Integer userId,
                           String userName,
                           Integer balance,
                           List<Order> orderList) {
    }
}
