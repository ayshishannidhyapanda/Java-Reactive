package reactor.sec04;

import reactor.common.Util;
import reactor.sec04.assignment.FileReaderServiceImpl;

import java.nio.file.Path;

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
public class Lec09Assignment {

    public static void main(String[] args) {
        var path = Path.of("src/main/resources/sec04/file.txt");
        var fileReaderService = new FileReaderServiceImpl();
        fileReaderService.read(path)
                .take(6)
                .subscribe(Util.subscriber());

    }
}
