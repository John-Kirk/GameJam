package com.nerds.gamejam.gameplay.planet;

import com.badlogic.gdx.Gdx;
import com.github.chaosfirebolt.converter.RomanInteger;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.util.RandomSeed;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PlanetNameFactory {

    private final List<String> singleNames;
    private final List<String> colours;
    private final List<String> comparators;
    private final List<String> greek;

    public PlanetNameFactory() {
        singleNames = readStringsFromFile("single-names");
        colours = readStringsFromFile("colours");
        comparators = readStringsFromFile("comparators");
        greek = readStringsFromFile("greek");
    }

    private List<String> readStringsFromFile(String filename) {
        try {
            return Files.readAllLines(Gdx.files.internal("planet/naming/" + filename + ".txt").file().toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generatePlanetName() {
        String planetName = getPrefix();
        planetName += getRandomLine(singleNames);
        planetName += getSuffix();
        planetName += getNumeral();
        return planetName;
    }

    private String getNumeral() {
        if (GameJam.randomSeed.getRandomGenerator().nextBoolean()) {
            return " " + RomanInteger.parse(String.valueOf(GameJam.randomSeed.getRandomGenerator().nextInt(27) + 1)).toString();
        }
        return "";
    }

    private String getPrefix() {
        String prefix = getFix();
        if (!prefix.isEmpty()) {
            prefix += " ";
        }
        return prefix;
    }

    private String getSuffix() {
        String suffix = getFix();
        if (!suffix.isEmpty()) {
            suffix = " " + suffix;
        }
        return suffix;
    }

    private String getFix() {
        if (GameJam.randomSeed.getRandomGenerator().nextBoolean()) {
            int generationStrategy = GameJam.randomSeed.getRandomGenerator().nextInt(3);
            switch (generationStrategy) {
                case 0: return getRandomLine(colours);
                case 1: return getRandomLine(comparators);
                case 2: return getRandomLine(greek);
            }
        }
        return "";
    }

    private String getRandomLine(List<String> list) {
        return list.get(GameJam.randomSeed.getRandomGenerator().nextInt(list.size()));
    }
}
