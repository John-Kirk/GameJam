package com.nerds.gamejam.gameplay.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {

    @Test
    public void testGetCurrentHpDescriptionWhenHealthy() {
        Character character = new Character();
        assertEquals(character.getCurrentHpDescription(), "Healthy");
    }
}
