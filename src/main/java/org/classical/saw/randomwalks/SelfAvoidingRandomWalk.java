package org.classical.saw.randomwalks;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class SelfAvoidingRandomWalk implements Callable<Map<Integer, Double>> {

    private final Map<Integer, Double> randomWalks;
    private final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private final Random random = new Random();
    private final Long limit;

    public SelfAvoidingRandomWalk(Long limit) {
        this.limit = limit;
        this.randomWalks = new ConcurrentHashMap<>();
    }

    public void pseudoRandomWalkWith40Step() {

        for (int i = 0; i < this.limit; i++) {
            Set<String> isVisited = new HashSet<>();
            int x = 0, y = 0;
            for (int j = 1; j <= 40; j++) {
                int index = random.nextInt(4);
                x += this.directions[index][0];
                y += this.directions[index][1];
                if (!isVisited.contains(x + "," + y)) {
                    isVisited.add(x + "," + y);
                    double squareDistance = calculateDistanceBetweenPoints(0, 0, x, y);
                    this.randomWalks.put(j, this.randomWalks.getOrDefault(j, 0.0)
                            + squareDistance);
                } else break;
            }
        }
    }

    @Override
    public Map<Integer, Double> call() throws Exception {
        pseudoRandomWalkWith40Step();
        return this.randomWalks;
    }

    public double calculateDistanceBetweenPoints(
            double x1,
            double y1,
            double x2,
            double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
