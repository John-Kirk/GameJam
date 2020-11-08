package com.nerds.gamejam;

import java.util.Random;

public class RandomSeed {

    private Random random;

    public RandomSeed() {
        regenerateSeed();
    }

    public void regenerateSeed() {
        seedNewRandom(new Random().nextLong());
    }

    private void seedNewRandom(long seed) {
        random = new Random(seed);
    }

    public Random getRandomGenerator() {
        return random;
    }
}
