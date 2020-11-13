package com.nerds.gamejam.util;

import com.badlogic.gdx.Gdx;

public class InputUtil {

    public static boolean INPUT_ALLOWED = true;

    public static boolean isKeyPressed(int key) {
          return INPUT_ALLOWED && Gdx.input.isKeyPressed(key);
    }
}
