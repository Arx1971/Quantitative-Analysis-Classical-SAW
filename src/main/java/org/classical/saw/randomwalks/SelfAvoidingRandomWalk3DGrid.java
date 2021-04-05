package org.classical.saw.randomwalks;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class SelfAvoidingRandomWalk3DGrid implements Callable<Map<Integer, Double>> {

    private final Map<Integer, Double> randomWalks;
    private final int[][] directions = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
    private final Random random = new Random();
    private final Long limit;
    private final Integer numberOfSteps;

    public SelfAvoidingRandomWalk3DGrid(Long limit, Integer numberOfSteps) {
        this.randomWalks = new ConcurrentHashMap<>();
        this.limit = limit;
        this.numberOfSteps = numberOfSteps;
    }

    public void calculateEndToEndDistance() {
        for (int i = 0; i < this.limit; i++) {
            Set<String> isVisited = new HashSet<>();
            int x = 0, y = 0, z = 0;
            for (int j = 1; j <= this.numberOfSteps; j++) {
                int index = random.nextInt(6);
                x += this.directions[index][0];
                y += this.directions[index][1];
                z += this.directions[index][2];

                if (!isVisited.contains(x + "," + y + "," + z)) {
                    isVisited.add(x + "," + y + "," + z);
                    double squareDistance = distanceFromOrigin(x, y, z);
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

    public double distanceFromOrigin(double x, double y, double z) {
        return Math.sqrt((x) * (x) + (y) * (y) + (z) * (z));
    }

}
