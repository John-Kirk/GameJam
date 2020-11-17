package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.nerds.gamejam.util.TextureReference;

import java.util.Collections;
import java.util.List;

public class TextureReferenceComponent extends Component {

    public List<TextureReference> references;

    public TextureReferenceComponent(List<TextureReference> references) {
        this.references = references;
    }

    public TextureReferenceComponent(TextureReference reference) {
        this.references = Collections.singletonList(reference);
    }

    public TextureReferenceComponent() {
    }
}
