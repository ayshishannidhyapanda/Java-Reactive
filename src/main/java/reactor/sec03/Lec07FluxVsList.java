package reactor.sec03;

import reactor.common.Util;
import reactor.sec01.subscriber.SubscriberImpl1;
import reactor.sec03.helper.NameGenerator;

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
public class Lec07FluxVsList {
    public static void main(String[] args) {

        // it will print value all at a time i.e. why it takes a lot of time to show the result(e.g. Gemini)
        var list = NameGenerator.getNamesList(10);
        System.out.println("List");
        System.out.println(list);

        System.out.println("Flux");
        // it will print value one by one, it don't take much time because it doesn't print all at once(e.g. ChatGPT)
//        NameGenerator.getNamesFlux(10)
//                .subscribe(Util.subscriber());

        var subscriber = new SubscriberImpl1();
        NameGenerator.getNamesFlux(10)
                .subscribe(subscriber);

        //here I have the ability to cancel where I want, or I can react or stop early
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();
    }
}

