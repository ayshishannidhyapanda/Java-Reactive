package reactor.sec10.assignment.window;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
public class FileWriter {

    private final Path path;
    private BufferedWriter bufferedWriter;

    public FileWriter(Path path) {
        this.path = path;
    }

    public static Mono<Void> create(Flux<String> flux, Path path) {

        var writer = new FileWriter(path);
        return flux.doOnNext(writer::write)
                .doFirst(writer::createFile)
                .doFinally((s -> writer.closeFile()))
                .then();

    }

    private void write(String content) {
        try {
            this.bufferedWriter.write(content);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFile() {
        try {
            Files.createDirectories(this.path.getParent());
            this.bufferedWriter = Files.newBufferedWriter(this.path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void closeFile() {
        try {
            if (this.bufferedWriter != null) {
                this.bufferedWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
