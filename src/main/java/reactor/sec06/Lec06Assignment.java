package reactor.sec06;

import reactor.common.Util;
import reactor.sec06.assignment.ExternalServiceClient;
import reactor.sec06.assignment.InventoryService;
import reactor.sec06.assignment.RevenueService;

/*
 * Copyright (c) 2026 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 16-01-2026
 */
public class Lec06Assignment {

    public static void main(String[] args) throws InterruptedException {
        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.stream()
                .subscribe(Util.subscriber("inventory"));

        revenueService.stream()
                .subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(30);
    }
}
