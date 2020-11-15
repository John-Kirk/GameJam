package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.nerds.gamejam.util.TextureReference;

import java.util.Collections;
import java.util.List;

public class TextureReferenceComponent extends Component {

    public static int BACKGROUND = 0;
    public static int GAME = 1;
    public static int FOREGROUND = 2;

    public List<TextureReference> references;
    public int layer = GAME;

    public TextureReferenceComponent(List<TextureReference> references) {
        this.references = references;
    }

    public TextureReferenceComponent(TextureReference reference) {
        this.references = Collections.singletonList(reference);
    }

    public TextureReferenceComponent() {
    }
}
