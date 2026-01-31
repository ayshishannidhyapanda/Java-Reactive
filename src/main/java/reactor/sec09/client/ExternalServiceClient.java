package reactor.sec09.client;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 12-11-2025
 */

import org.springframework.lang.NonNull;
import reactor.common.AbstractHttpClient;
import reactor.core.publisher.Mono;
import reactor.sec09.assignment.Product;

public class ExternalServiceClient extends AbstractHttpClient {
    
    public Mono<Product> getProduct(int productId) {
        return Mono.zip(
                        getProductName(productId),
                        getReview(productId),
                        getPrice(productId)
                )
                .map(t ->
                        new Product(t.getT1(), t.getT2(), t.getT3()));
    }

    @NonNull
    private Mono<String> getProductName(int productId) {
        return get("/demo05/product/" + productId);
    }

    @NonNull
    private Mono<String> getReview(int productId) {
        return get("/demo05/review/" + productId);
    }

    @NonNull
    private Mono<String> getPrice(int productId) {
        return get("/demo05/price/" + productId);
    }

    @NonNull
    private Mono<String> get(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }

}
