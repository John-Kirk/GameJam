package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.VelocityComponent;

@Wire
public class MovementSystem extends EntityProcessingSystem {
    private ComponentMapper<VelocityComponent> velocityMapper;
    private ComponentMapper<PositionComponent> positionMapper;

    public MovementSystem() {
        super(Aspect.all(PositionComponent.class, VelocityComponent.class));
    }

    @Override
    protected void process(Entity e) {
        PositionComponent position = positionMapper.get(e);
        VelocityComponent velocity = velocityMapper.get(e);

        int newX = (int) (position.x + (world.getDelta()*velocity.velocityX));
        int newY = (int) (position.y + (world.getDelta()*velocity.velocityY));

        position.setPosition(newX, newY);
    }

}