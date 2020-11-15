package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.FontComponent;
import com.nerds.gamejam.ecs.component.InMotionComponent;
import com.nerds.gamejam.ecs.component.PlanetComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.ScaleComponent;
import com.nerds.gamejam.util.InputUtil;

import static com.nerds.gamejam.ecs.system.PlanetMapGeneratorSystem.PLANET_SPIRTE_SIZE;

@Wire
public class OrbitalSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<PlanetComponent> planetMapper;
    private ComponentMapper<FontComponent> fontMapper;
    private ComponentMapper<InMotionComponent> inMotionMapper;
    private ComponentMapper<ScaleComponent> scaleMapper;

    public OrbitalSystem() {
        super(Aspect.all(PlanetComponent.class));
    }

    @Override
    protected void process(int entityId) {
        PositionComponent positionComponent = positionMapper.get(entityId);
        PlanetComponent planetComponent = planetMapper.get(entityId);
        FontComponent fontComponent = fontMapper.get(entityId);
        InMotionComponent inMotionComponent = inMotionMapper.get(entityId);
        ScaleComponent scaleComponent = scaleMapper.get(entityId);

        if (inMotionComponent == null && InputUtil.isKeyPressed(Input.Keys.N)) {
            world.edit(entityId).add(InMotionComponent.INSTANCE);
            planetComponent.setNextOrbitalAngle();
        } else {
            if (planetComponent.orbitalDirection && planetComponent.orbitalAngle >= planetComponent.nextOrbitalAngle) {
                world.edit(entityId).remove(InMotionComponent.INSTANCE);
            } else if (!planetComponent.orbitalDirection && planetComponent.orbitalAngle <= planetComponent.nextOrbitalAngle) {
                world.edit(entityId).remove(InMotionComponent.INSTANCE);
            } else {
                int solarCenterX = GameJam.PLANET_VIEW_WIDTH / 2;
                int solarCenterY = GameJam.PLANET_VIEW_HEIGHT / 2;
                planetComponent.orbitalAngle = planetComponent.orbitalAngle
                        + (planetComponent.orbitalSpeed * world.getDelta());

                double orbitalRadius = planetComponent.orbitalDistance + PLANET_SPIRTE_SIZE - solarCenterX;
                int x = solarCenterX + (int) ((Math.cos(planetComponent.orbitalAngle) * orbitalRadius) - (PLANET_SPIRTE_SIZE / 2 * scaleComponent.x));
                int y = solarCenterY + (int) ((Math.sin(planetComponent.orbitalAngle) * orbitalRadius) - (PLANET_SPIRTE_SIZE / 2 * scaleComponent.y));
                positionComponent.x = x;
                positionComponent.y = y;
                fontComponent.x = x - 10;
                fontComponent.y = y - 10;
            }
        }
    }
}
