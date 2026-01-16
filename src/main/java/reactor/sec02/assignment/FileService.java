package reactor.sec02.assignment;

import reactor.core.publisher.Mono;

import java.io.IOException;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 14-11-2025
 */
public interface FileService {

    Mono<String> read(String name) throws IOException;

    Mono<Void> write(String fileName, String content) throws IOException;

    Mono<Void> delete(String fileName) throws IOException;

}
