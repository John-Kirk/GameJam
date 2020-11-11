package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.nerds.gamejam.ecs.component.MonsterComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.VelocityComponent;
import com.nerds.gamejam.gameplay.character.Monster;

@Wire
public class MonsterControlSystem extends IteratingSystem {

    private ComponentMapper<VelocityComponent> velocityMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<MonsterComponent> monsterMapper;
    private boolean moving = false;
    private int goalX;

    public MonsterControlSystem() {
        super(Aspect.all(MonsterComponent.class));
    }

    @Override
    protected void process(int e) {
        VelocityComponent velocity = velocityMapper.get(e);
        PositionComponent position = positionMapper.get(e);
        MonsterComponent monster = monsterMapper.get(e);
        if (!moving) {
            // will be replaced with a trigger from future time system
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                goalX = position.x + Monster.TRAVEL_DISTANCE;
                velocity.velocityX = Monster.SPEED;
                moving = true;
            }
        } else {
            if (position.x == goalX) {
                velocity.velocityX = 0;
                moving = false;
            }
        }
    }

}