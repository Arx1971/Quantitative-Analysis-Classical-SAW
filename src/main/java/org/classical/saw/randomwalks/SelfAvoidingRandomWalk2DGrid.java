package org.classical.saw.randomwalks;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class SelfAvoidingRandomWalk2DGrid implements Callable<Map<Integer, Double>> {

    private final Map<Integer, Double> randomWalks;
    private final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private final Random random = new Random();
    private final Long limit;
    private final Integer numberOfSteps;

    public SelfAvoidingRandomWalk2DGrid(Long limit, Integer numberOfSteps) {
        this.limit = limit;
        this.randomWalks = new ConcurrentHashMap<>();
        this.numberOfSteps = numberOfSteps;
    }

    public void calculateEndToEndDistance() {

        for (int i = 0; i < this.limit; i++) {
            Set<String> isVisited = new HashSet<>();
            int x = 0, y = 0;
            for (int j = 1; j <= this.numberOfSteps; j++) {
                int index = random.nextInt(4);
                x += this.directions[index][0];
                y += this.directions[index][1];
                if (!isVisited.contains(x + "," + y)) {
                    isVisited.add(x + "," + y);
                    double squareDistance = distanceFromOrigin(x, y);
                    this.randomWalks.put(j, this.randomWalks.getOrDefault(j, 0.0)
                            + squareDistance);
                } else break;
            }
        }
    }

    @Override
    public Map<Integer, Double> call() throws Exception {
        calculateEndToEndDistance();
        return this.randomWalks;
    }

    public double distanceFromOrigin(double x, double y) {
        return Math.sqrt((x) * (x) + (y) * (y));
    }
}
