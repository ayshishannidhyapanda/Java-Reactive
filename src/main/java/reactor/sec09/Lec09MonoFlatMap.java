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

/* Sequential non-blocking IO calls
 * flat map is used to flatten the inner publisher / to subscriber to the inner publisher
 * */

import reactor.common.Util;
import reactor.core.publisher.Mono;
import reactor.sec09.applications.PaymentService;
import reactor.sec09.applications.UserService;

public class Lec09MonoFlatMap {
    public static void main(String[] args) {
//        We have username, Get user Account Balance
        /*Mono<Mono<Integer>> monoMono = */
        //here map will not work because it was inside Mono which consist another mono which have integer
        /*UserService.getUserId("sam")
                .map(userId -> PaymentService.getUserBalance(userId))
                .subscribe(Util.subscriber());*/
        // if we use map() it becomes Mono<Mono<String>> but flatMap becomes Mono<String>

        //So we have to use FlatMap() to make it functional

        UserService.getUserId("sam")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());
    }
}
