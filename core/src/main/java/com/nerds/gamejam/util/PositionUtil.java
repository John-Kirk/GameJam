package com.nerds.gamejam.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PositionUtil {

    private OrthographicCamera orthographicCamera;

    public PositionUtil(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

    public Vector2 toWorldPosition(Vector2 vector2) {
        Vector3 project = orthographicCamera.project(new Vector3(vector2.x, vector2.y, 0));
        return new Vector2(project.x, project.y);
    }

}
