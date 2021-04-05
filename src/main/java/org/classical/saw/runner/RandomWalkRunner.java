package org.classical.saw.runner;

import org.classical.saw.randomwalks.SelfAvoidingRandomWalk2DGrid;
import org.classical.saw.randomwalks.SelfAvoidingRandomWalk3DGrid;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RandomWalkRunner {

    private final Integer setNumberOfThreads;
    private final Long setNumberOfWalks;
    private final Integer setNumberOfStep;
    private final Integer dimensionSize;

    public RandomWalkRunner(Integer setNumberOfThreads,
                            Long setNumberOfWalks,
                            Integer setNumberOfStep,
                            Integer dimensionSize) throws ExecutionException, InterruptedException {
        this.setNumberOfThreads = setNumberOfThreads;
        this.setNumberOfWalks = setNumberOfWalks;
        this.setNumberOfStep = setNumberOfStep;
        this.dimensionSize = dimensionSize;
        randomRunner();
    }

    public void randomRunner() throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(this.setNumberOfThreads);

        Future<Map<Integer, Double>> future = null;

        if (dimensionSize == 2) future = executorService.submit(
                new SelfAvoidingRandomWalk2DGrid(this.setNumberOfWalks, this.setNumberOfStep));
        else if (dimensionSize == 3) future = executorService.submit(
                new SelfAvoidingRandomWalk3DGrid(this.setNumberOfWalks, this.setNumberOfStep));

        assert future != null;
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
