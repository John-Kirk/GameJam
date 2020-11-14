package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.FontComponent;
import com.nerds.gamejam.ecs.component.InMotionComponent;
import com.nerds.gamejam.ecs.component.LandmassComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.util.InputUtil;

@Wire
public class OrbitalSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<LandmassComponent> landmassMapper;
    private ComponentMapper<FontComponent> fontMapper;
    private ComponentMapper<InMotionComponent> inMotionMapper;

    public OrbitalSystem() {
        super(Aspect.all(LandmassComponent.class));
    }

    @Override
    protected void process(int entityId) {
        PositionComponent positionComponent = positionMapper.get(entityId);
        LandmassComponent landmassComponent = landmassMapper.get(entityId);
        FontComponent fontComponent = fontMapper.get(entityId);
        InMotionComponent inMotionComponent = inMotionMapper.get(entityId);

        if (inMotionComponent == null && InputUtil.isKeyPressed(Input.Keys.N)) {
            world.edit(entityId).add(InMotionComponent.INSTANCE);
            landmassComponent.setNextOrbitalAngle();
        } else {
            if (landmassComponent.getOrbitAngle() >= landmassComponent.getNextOrbitalAngle()) {
                world.edit(entityId).remove(InMotionComponent.INSTANCE);
            } else {
                int solarCenterX = GameJam.PLANET_VIEW_WIDTH / 2;
                int solarCenterY = GameJam.PLANET_VIEW_HEIGHT / 2;
                landmassComponent.setOrbitAngle(landmassComponent.getOrbitAngle()
                        + (landmassComponent.getOrbitalSpeed() * world.getDelta()));

                double radius = landmassComponent.getOrbitalDistance() + 24 - solarCenterX;
                int x = solarCenterX + (int) (Math.cos(landmassComponent.getOrbitAngle()) * radius);
                int y = solarCenterY + (int) (Math.sin(landmassComponent.getOrbitAngle()) * radius);
                positionComponent.x = x;
                positionComponent.y = y;
                fontComponent.x = x - 10;
                fontComponent.y = y - 10;
            }
        }
    }
}
