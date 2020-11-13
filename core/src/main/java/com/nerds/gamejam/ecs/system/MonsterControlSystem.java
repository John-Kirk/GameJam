package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.nerds.gamejam.ecs.component.InMotionComponent;
import com.nerds.gamejam.ecs.component.MonsterComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.VelocityComponent;
import com.nerds.gamejam.gameplay.character.Monster;
import com.nerds.gamejam.util.InputUtil;

@Wire
public class MonsterControlSystem extends IteratingSystem {

    private ComponentMapper<VelocityComponent> velocityMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<MonsterComponent> monsterMapper;
    private ComponentMapper<InMotionComponent> inMotionComponentComponentMapper;

    public MonsterControlSystem() {
        super(Aspect.all(MonsterComponent.class));
    }

    @Override
    protected void process(int e) {
        VelocityComponent velocity = velocityMapper.get(e);
        PositionComponent position = positionMapper.get(e);
        MonsterComponent monster = monsterMapper.get(e);
        InMotionComponent inMotionComponent = inMotionComponentComponentMapper.get(e);
        if (inMotionComponent == null) {
            // will be replaced with a trigger from future time system
            if (InputUtil.isKeyPressed(Input.Keys.SPACE)) {
                monster.xPositionGoal = position.x + Monster.TRAVEL_DISTANCE;
                velocity.velocityX = Monster.SPEED;
                world.edit(e).add(InMotionComponent.INSTANCE);
            }
        } else {
            if (position.x >= monster.xPositionGoal) {
                velocity.velocityX = 0;
                world.edit(e).remove(InMotionComponent.INSTANCE);
            }
        }
    }

}
