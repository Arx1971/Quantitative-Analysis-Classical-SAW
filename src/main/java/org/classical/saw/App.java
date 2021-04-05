package org.classical.saw;

import org.classical.saw.randomwalks.SelfAvoidingRandomWalk2DGrid;
import org.classical.saw.randomwalks.SelfAvoidingRandomWalk3DGrid;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        threeDimensionsWalk();
    }

    public static void twoDimensionsWalk() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(100000);

        Future<Map<Integer, Double>> future = executorService.submit(
                new SelfAvoidingRandomWalk2DGrid(1000000L, 40));

        Map<Integer, Double> result = future.get();

        for (Integer key : result.keySet()) {
            System.out.println(key + " -> " + result.get(key));
        }

        executorService.shutdown();

        long end = System.currentTimeMillis();

        double time = (end - start) / 1000.0;

        System.out.println("Total Time to Execute " + time + " Seconds");

    }

    public static void threeDimensionsWalk() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(100000);

        Future<Map<Integer, Double>> future = executorService.submit(
                new SelfAvoidingRandomWalk3DGrid(1000000L, 40));

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
