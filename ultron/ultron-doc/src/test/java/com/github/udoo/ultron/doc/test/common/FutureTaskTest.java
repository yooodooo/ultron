package com.github.udoo.ultron.doc.test.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) throws Exception {

        List<Callable<Integer>> taskList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            taskList.add(() -> {
                int sleep = new Random().nextInt(5) * 1000;
                System.out.println("Sleep: " + sleep);
                Thread.sleep(sleep);
                return new Random().nextInt(10) + 10;
            });
        }

        long begin = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Integer>> futures = executorService.invokeAll(taskList);

        long result = 0;
        for (Future<Integer> future : futures) {
            result += future.get();
        }
        System.out.println("Result: " + result + " Cost: " + (System.currentTimeMillis() - begin));

        executorService.shutdown();
    }
}
