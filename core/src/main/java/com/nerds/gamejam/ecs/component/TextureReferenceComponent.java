package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

import java.util.List;

public class TextureReferenceComponent extends Component {

    private List<TextureReference> references;

    public TextureReferenceComponent(List<TextureReference> references) {
        this.references = references;
    }

    public TextureReferenceComponent() {
    }

    public List<TextureReference> getReferences() {
        return references;
    }

    public void setReferences(List<TextureReference> references) {
        this.references = references;
    }

    public static class TextureReference {

        private String reference;
        private Color color;
        private TextureWrap textureWrap;

        public TextureReference(String reference, Color color) {
            this.reference = reference;
            this.color = color;
        }

        public String getReference() {
            return reference;
        }

        public Color getColor() {
            return color;
        }

        public TextureWrap getTextureWrap() {
            return textureWrap;
        }

        public void setTextureWrap(TextureWrap textureWrap) {
            this.textureWrap = textureWrap;
        }
    }
}
