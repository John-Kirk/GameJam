package com.nerds.gamejam.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.system.SpeechSystem.EndOfSpeechCallback;

public class SpeechActor extends Dialog {

    private Array<String> lines;
    private Label currentLine;
    private final EndOfSpeechCallback onFinished;

    public SpeechActor(String name, TextureRegion portrait, Array<String> lines, EndOfSpeechCallback onFinished) {
        super(name, GameJam.skin);
        this.lines = lines;
        this.onFinished = onFinished;

        getContentTable().add(new Image(portrait)).width(Value.percentWidth(0.25f, this)).padRight(Value.percentWidth(0.05f, this));

        currentLine = new Label(lines.removeIndex(0), GameJam.skin);
        currentLine.setWrap(true);
        currentLine.setFontScale(3);
        getContentTable().add(currentLine).width(Value.percentWidth(0.7f, this));
    }

    public void next() {
        if (!lines.isEmpty()) {
            currentLine.setText(lines.removeIndex(0));
        } else {
            onFinished.execute();
        }
    }
}
