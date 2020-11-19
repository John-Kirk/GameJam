package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
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

        if (bodyComponent.physicalBody != null) {
            if (bodyComponent.physicalBody instanceof Circle) {
                Circle circle = (Circle) bodyComponent.physicalBody;
                circle.setX(positionComponent.x + circle.radius);
                circle.setY(positionComponent.y + circle.radius);
            } else if (bodyComponent.physicalBody instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) bodyComponent.physicalBody;
                rectangle.setPosition(positionComponent.x, positionComponent.y);
            }
        }
    }
}
