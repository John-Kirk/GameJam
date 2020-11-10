package com.nerds.gamejam.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class TextureRegionFactory {

    /**
     * Splits a texture into regions for animation purposes
     * Texture split wil occur from left to right, down a column, repeat
     */
    public static Array<TextureRegion> createTextureRegionArray(Texture texture, int width, int height, int numTextures) {
        TextureRegion[][] textureRegions = TextureRegion.split(texture, width, height);
        Array<TextureRegion> flatTextureRegionArray = new Array<>(numTextures);
        int numCols = texture.getWidth() / width;
        int numRows = texture.getHeight() / height;
        for(int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                flatTextureRegionArray.add(textureRegions[i][j]);
                if (flatTextureRegionArray.size == numTextures) {
                    break;
                }
            }
        }
        return flatTextureRegionArray;
    }
}
