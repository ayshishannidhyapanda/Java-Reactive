package reactor.sec10.assignment.groupBy;

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
public record PurchaseOrder(String item, String category, Integer price) {

    public static PurchaseOrder create() {
        var commerce = Util.faker().commerce();

        return new PurchaseOrder(
                commerce.productName(),
                commerce.department(),
                Util.faker().random().nextInt(10, 100)
        );
    }
}
