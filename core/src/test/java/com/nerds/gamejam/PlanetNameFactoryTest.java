package com.nerds.gamejam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlanetNameFactoryTest extends LibGdxTest {

    private Random random;
    private PlanetNameFactory planetNameFactory;

    @BeforeEach
    public void setUp() {
        RandomSeed randomSeed = mock(RandomSeed.class);
        random = mock(Random.class);
        when(randomSeed.getRandomGenerator()).thenReturn(random);
        planetNameFactory = new PlanetNameFactory(randomSeed);
    }

    @Test
    public void testGenerateNameWithSingleName() {
        when(random.nextInt()).thenReturn(1);
        assertEquals("Babylon", planetNameFactory.generatePlanetName());
    }

    @Test
    public void testGenerateNameWithPrefixOnly() {
        when(random.nextBoolean()).thenReturn(true).thenReturn(false);
        when(random.nextInt()).thenReturn(1);
        assertEquals("White Babylon", planetNameFactory.generatePlanetName());
    }

    @Test
    public void testGenerateNameWithSuffixOnly() {
        when(random.nextBoolean()).thenReturn(false).thenReturn(true).thenReturn(false);
        when(random.nextInt()).thenReturn(1);
        assertEquals("Babylon White", planetNameFactory.generatePlanetName());
    }

    @Test
    public void testGenerateNameWithNumeralOnly() {
        when(random.nextBoolean()).thenReturn(false).thenReturn(false).thenReturn(true);
        when(random.nextInt()).thenReturn(1);
        assertEquals("Babylon I", planetNameFactory.generatePlanetName());
    }

    @Test
    public void testGenerateNameWithPrefixAndSuffixOnly() {
        when(random.nextBoolean()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(random.nextInt()).thenReturn(1);
        assertEquals("White Babylon White", planetNameFactory.generatePlanetName());
    }

    @Test
    public void testGenerateNameWithPrefixAndNumeralOnly() {
        when(random.nextBoolean()).thenReturn(true).thenReturn(false).thenReturn(true);
        when(random.nextInt()).thenReturn(1);
        assertEquals("White Babylon I", planetNameFactory.generatePlanetName());
    }

    @Test
    public void testGenerateNameWithSuffixAndNumeralOnly() {
        when(random.nextBoolean()).thenReturn(false).thenReturn(true).thenReturn(true);
        when(random.nextInt()).thenReturn(1);
        assertEquals("Babylon White I", planetNameFactory.generatePlanetName());
    }

    @Test
    public void testGenerateNameWithPrefixSuffixAndNumeral() {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt()).thenReturn(1);
        assertEquals("White Babylon White I", planetNameFactory.generatePlanetName());
    }
}
