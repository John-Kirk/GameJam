package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.nerds.gamejam.ecs.component.InMotionComponent;
import com.nerds.gamejam.ecs.component.MonsterComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.VelocityComponent;
import com.nerds.gamejam.gameplay.character.Monster;
import com.nerds.gamejam.util.InputUtil;

@Wire
public class GameIntroSystem extends IteratingSystem {

    private CameraSystem cameraSystem;
    private ComponentMapper<MonsterComponent> monsterMapper;
    private ComponentMapper<VelocityComponent> velocityMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<InMotionComponent> inMotionComponentComponentMapper;
    private boolean cameraCurrentlyZooming = true;

    public GameIntroSystem() {
        super(Aspect.all(MonsterComponent.class, VelocityComponent.class, PositionComponent.class));
    }

    @Override
    protected void initialize() {
        cameraSystem.camera.zoom = 0.1f;
        InputUtil.INPUT_ALLOWED = false;
    }

    @Override
    protected void process(int e) {
        cameraSystem.camera.zoom += 0.1f * this.world.getDelta();
        if (cameraCurrentlyZooming && cameraSystem.camera.zoom >= 1f) {
            cameraSystem.camera.zoom = 1f;
            cameraCurrentlyZooming = false;
            beginMonsterMovement(e);
        } else if (inMotionComponentComponentMapper.get(e) == null) {
            InputUtil.INPUT_ALLOWED = true;
            setEnabled(false);
        }
        cameraSystem.camera.update();
    }

    private void beginMonsterMovement(int e) {
        MonsterComponent monsterComponent = monsterMapper.get(e);
        monsterComponent.xPositionGoal = 0;
        VelocityComponent velocityComponent = velocityMapper.get(e);
        velocityComponent.velocityX = Monster.SPEED / 10;
        world.edit(e).add(InMotionComponent.INSTANCE);
    }

}
