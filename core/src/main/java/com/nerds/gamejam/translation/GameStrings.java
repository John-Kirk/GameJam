package com.nerds.gamejam.translation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

public class GameStrings {

    private I18NBundle translationBundle;

    public GameStrings() {
        FileHandle baseFileHandle = Gdx.files.internal("i18n/translationStrings");
        translationBundle = I18NBundle.createBundle(baseFileHandle);
    }

    public String get(String key) {
        return translationBundle.get(key);
    }
}
