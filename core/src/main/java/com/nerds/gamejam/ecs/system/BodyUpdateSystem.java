package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.nerds.gamejam.ecs.component.BodyComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;

public class BodyUpdateSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> positionComponentComponentMapper;
    private ComponentMapper<BodyComponent> bodyComponentComponentMapper;

    public BodyUpdateSystem() {
        super(Aspect.all(PositionComponent.class, BodyComponent.class));
    }

    @Override
    protected void process(int entityId) {
        PositionComponent positionComponent = positionComponentComponentMapper.get(entityId);
        BodyComponent bodyComponent = bodyComponentComponentMapper.get(entityId);

        Shape2D body = bodyComponent.getBody();
        if (body instanceof Circle) {
            ((Circle) body).setX(positionComponent.x + ((Circle) body).radius);
            ((Circle) body).setY(positionComponent.y + ((Circle) body).radius);
       } else if (body instanceof Rectangle) {
            ((Rectangle) body).setPosition(positionComponent.x, positionComponent.y);
        }
    }
}
