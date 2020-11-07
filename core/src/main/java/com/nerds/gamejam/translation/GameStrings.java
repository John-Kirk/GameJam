package com.nerds.gamejam.translation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

public class GameStrings {

    private static GameStrings gameStrings;
    private static I18NBundle translationBundle;

    private GameStrings() {
        FileHandle baseFileHandle = Gdx.files.internal("i18n/translationStrings");
        translationBundle = I18NBundle.createBundle(baseFileHandle);
    }

    public static I18NBundle getInstance() {
        if (gameStrings == null) {
            gameStrings = new GameStrings();
        }
        return translationBundle;
    }
}
