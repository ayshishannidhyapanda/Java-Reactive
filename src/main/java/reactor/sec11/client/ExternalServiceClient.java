package reactor.sec11.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

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
public class ExternalServiceClient extends AbstractHttpClient {


    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

    public Mono<String> getProductName(int productId) {
        return get("/demo06/product/" + productId);
    }

    private Mono<String> get(String path) {
        return this.httpClient
                .get()
                .uri(path)
                .response(this::toResponse)
                .next();
    }

    private Flux<String> toResponse(HttpClientResponse httpClientResponse, ByteBufFlux byteBufFlux) {
        return switch (httpClientResponse.status().code()) {
            case 200 -> byteBufFlux.asString();
            case 400 -> throw new ClientError("Bad Request");
            default -> throw new ServerError("Internal Server Error " + httpClientResponse.status().code());
        };
    }

    public Mono<String> getCountry() {
        return get("/demo06/country");
    }


}
