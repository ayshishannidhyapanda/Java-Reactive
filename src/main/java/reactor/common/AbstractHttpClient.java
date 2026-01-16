package reactor.common;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

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

public abstract class AbstractHttpClient {

    private static final String BASE_URL = "http://localhost:7070";
    protected final HttpClient httpClient;

    public AbstractHttpClient() {
        var loopResources = LoopResources.create("ankit", 1, true);
        this.httpClient = HttpClient.create().runOn(loopResources).baseUrl(BASE_URL);
    }

}
