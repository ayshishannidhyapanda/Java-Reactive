package reactor.sec13.client;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 04-02-2026
 */
public class UserService {

    private static final Map<String, String> USER_CATEGORY = Map.of(
            "sam", "standard",
            "mike", "prime"
    );

    static Function<Context, Context> userCategoryContext() {
        return ctx -> ctx.getOrEmpty("user")
                .filter(USER_CATEGORY::containsKey)
                .map(USER_CATEGORY::get)
                .map(category -> ctx.put("category", category))
                .orElse(Context.empty());
    }

}
