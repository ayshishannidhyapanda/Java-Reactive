package reactor.sec02;

import reactor.common.Util;
import reactor.sec02.assignment.FileServiceImpl;

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
public class Lec12Assignment {
    public static void main(String[] args) {
        var fileService = new FileServiceImpl();

        fileService.write("file.txt", "Hello from Java Reactive Assignment 01")
                .subscribe(Util.subscriber());

        fileService.read("file.txt")
                .subscribe(Util.subscriber());

        fileService.delete("file.txt")
                .subscribe(Util.subscriber());

    }
}
