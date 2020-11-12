package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.nerds.gamejam.ecs.component.BodyComponent;

public class ShapeRenderSystem extends IteratingSystem {

    private final ShapeRenderer shapeRenderer;
    private final OrthographicCamera orthographicCamera;
    private ComponentMapper<BodyComponent> bodyComponentComponentMapper;

    public ShapeRenderSystem(OrthographicCamera orthographicCamera) {
        super(Aspect.all(BodyComponent.class));
        this.orthographicCamera = orthographicCamera;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    protected void process(int entityId) {
        BodyComponent bodyComponent = bodyComponentComponentMapper.get(entityId);
        Circle body = (Circle) bodyComponent.getBody();
        shapeRenderer.setColor(Color.CORAL);
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(body.x, body.y, body.radius);
        shapeRenderer.end();
    }
}
