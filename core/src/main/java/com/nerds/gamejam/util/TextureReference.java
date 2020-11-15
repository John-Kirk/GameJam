package com.nerds.gamejam.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class TextureReference {

    private String reference;
    private Color color;
    private Texture.TextureWrap textureWrap;

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

    public Texture.TextureWrap getTextureWrap() {
        return textureWrap;
    }

    public void setTextureWrap(Texture.TextureWrap textureWrap) {
        this.textureWrap = textureWrap;
    }
}
