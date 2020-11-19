package com.nerds.gamejam.gameplay.character;

import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.LibGdxTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest extends LibGdxTest {

    Character character;

    @BeforeEach
    public void setUp() {
        character = new Character(GameJam.gameStrings);
        character.setTotalHp(10);
    }

    @Test
    public void testGetCurrentHpDescriptionWhenHealthy() {
        character.setCurrentHp(10);
        assertEquals(character.getCurrentHpDescription(), "Healthy");
    }

    @Test
    public void testGetCurrentHpDescriptionWhenLightlyWounded() {
        character.setCurrentHp(8);
        assertEquals(character.getCurrentHpDescription(), "Lightly Wounded");
    }

    @Test
    public void testGetCurrentHpDescriptionWhenWounded() {
        character.setCurrentHp(5);
        assertEquals(character.getCurrentHpDescription(), "Wounded");
    }

    @Test
    public void testGetCurrentHpDescriptionWhenBadlyWounded() {
        character.setCurrentHp(3);
        assertEquals(character.getCurrentHpDescription(), "Badly Wounded");
    }

    @Test
    public void testGetCurrentHpDescriptionWhenNearDeath() {
        character.setCurrentHp(1);
        assertEquals(character.getCurrentHpDescription(), "Near Death");
    }

    @Test
    public void testGetCurrentHpDescriptionWhenDead() {
        character.setCurrentHp(0);
        assertEquals(character.getCurrentHpDescription(), "Dead");
    }
}
