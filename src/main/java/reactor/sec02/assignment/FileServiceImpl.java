package reactor.sec02.assignment;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;


public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final Path PATH = Path.of("src/main/resources/sec2");

    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(() -> Files.readString(PATH.resolve(fileName)));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> writeFile(fileName, content));
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }

    public void writeFile(String fileName, String content) {
        try {
            Files.writeString(PATH.resolve(fileName), content);
            log.info("Created {}", fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFile(String fileName) {
        try {
            Files.delete(PATH.resolve(fileName));
            log.info("Deleted {}", fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
