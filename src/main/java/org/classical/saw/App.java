package org.classical.saw;

import org.classical.saw.randomwalks.SelfAvoidingRandomWalk;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(100000);

        Future<Map<Integer, Double>> future = executorService.submit(new SelfAvoidingRandomWalk(1000000L));

        Map<Integer, Double> result = future.get();

        for (Integer key : result.keySet()) {
            System.out.println(key + " -> " + result.get(key));
        }

        executorService.shutdown();

        long end = System.currentTimeMillis();


        double time = (end - start) / 1000.0;

        System.out.println("Total Time to Execute " + time + " Seconds");

    }
}
