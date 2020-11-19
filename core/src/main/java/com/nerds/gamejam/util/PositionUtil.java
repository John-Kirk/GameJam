package com.nerds.gamejam.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PositionUtil {

    private OrthographicCamera orthographicCamera;
    private final Viewport viewport;

    public PositionUtil(OrthographicCamera orthographicCamera, Viewport viewport) {
        this.orthographicCamera = orthographicCamera;
        this.viewport = viewport;
    }

    public Vector2 toWorldPosition(Vector2 vector2) {
        Vector3 project = viewport.project(new Vector3(vector2.x, vector2.y, 0));
        return new Vector2(project.x, project.y);
    }
}
