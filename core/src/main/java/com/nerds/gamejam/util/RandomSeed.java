package com.nerds.gamejam.util;

import com.badlogic.gdx.Gdx;

import java.util.Random;

public class RandomSeed {

    private Random random;

    public RandomSeed() {
        regenerateSeed();
    }

    public void regenerateSeed() {
        long newSeed = new Random().nextLong();
        Gdx.app.log("RandomSeed", String.valueOf(newSeed));
        seedNewRandom(newSeed);
    }

    private void seedNewRandom(long seed) {
        random = new Random(123);
    }

    public Random getRandomGenerator() {
        return random;
    }
}
