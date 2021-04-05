package org.classical.saw;

import org.classical.saw.runner.RandomWalkRunner;

import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        RandomWalkRunner threeD = new RandomWalkRunner(
                10000, 1000000L, 40, 3);
        RandomWalkRunner twoD = new RandomWalkRunner(
                10000, 1000000L, 40, 2);

    }


}
