package org.classical.saw.randomwalks;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class SelfAvoidingRandomWalk implements Callable<Map<Integer, Integer>> {

    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {1, 0}};
    private Map<Integer, Integer> endToEndDistanceMap;

    public SelfAvoidingRandomWalk() {
        this.endToEndDistanceMap = new ConcurrentHashMap<>();
    }

    @Override
    public Map<Integer, Integer> call() throws Exception {
        return this.endToEndDistanceMap;
    }
}
