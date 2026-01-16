package reactor.sec04.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Java-Reactive
 * Author: Ayshi Shannidhya Panda
 * Created on: 16-11-2025
 */
public class FileReaderServiceImpl implements FileReaderService {

    private static final Logger log = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Override
    public Flux<String> read(Path path) {

        return Flux.generate(
                () -> openFile(path),
                (reader, sink) -> readFile(reader, sink),
                reader -> closedFile(reader)
        );
    }

    private BufferedReader openFile(Path path) throws IOException {
        log.info("opening file...");
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink) {
        try {
            var line = reader.readLine();
            log.info("reading file {}", line);
            if (Objects.isNull(line)) {
                sink.complete();
            } else {
                sink.next(line);
            }
        } catch (Exception e) {
            sink.error(e);
        }
        return reader;
    }

    private void closedFile(BufferedReader reader) {
        try {
            reader.close();
            log.info("Closed file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
