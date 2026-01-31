package reactor.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

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
public class UserService {
    private static final Map<String, Integer> userTable = Map.of(
            "sam", 1,
            "mike", 2,
            "jake", 3
    );

    public static Flux<User> getAllUsers() {
        return Flux
                .fromIterable(userTable.entrySet())
                .map(stringIntegerEntry -> new User(
                        stringIntegerEntry.getValue(),
                        stringIntegerEntry.getKey()
                ));
    }

    public static Mono<Integer> getUserId(String userName) {
        return Mono
                .fromSupplier(() -> userTable.get(userName));
    }


}
