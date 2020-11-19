package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.nerds.gamejam.ecs.component.FontComponent;
import com.nerds.gamejam.ecs.component.InMotionComponent;
import com.nerds.gamejam.ecs.component.PlanetComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.ScaleComponent;
import com.nerds.gamejam.util.InputUtil;
import com.nerds.gamejam.util.OrbitalCalculations;

@Wire
public class OrbitalSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<PlanetComponent> planetMapper;
    private ComponentMapper<FontComponent> fontMapper;
    private ComponentMapper<InMotionComponent> inMotionMapper;
    private ComponentMapper<ScaleComponent> scaleMapper;
    private final OrbitalCalculations orbitalCalculations;

    public OrbitalSystem(OrbitalCalculations orbitalCalculations) {
        super(Aspect.all(PlanetComponent.class));
        this.orbitalCalculations = orbitalCalculations;
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
            planetComponent.nextOrbitalAngle = planetComponent.orbitalAngle + (planetComponent.orbitalSpeed / 3);
        } else {
            if (planetComponent.orbitalDirection && planetComponent.orbitalAngle >= planetComponent.nextOrbitalAngle) {
                world.edit(entityId).remove(InMotionComponent.INSTANCE);
            } else if (!planetComponent.orbitalDirection && planetComponent.orbitalAngle <= planetComponent.nextOrbitalAngle) {
                world.edit(entityId).remove(InMotionComponent.INSTANCE);
            } else {
                planetComponent.orbitalAngle = planetComponent.orbitalAngle + (planetComponent.orbitalSpeed * world.getDelta());

                int x = orbitalCalculations.getPlanetXPosition(
                        planetComponent.orbitalAngle, planetComponent.orbitalDistance, scaleComponent.x);
                int y = orbitalCalculations.getPlanetYPosition(
                        planetComponent.orbitalAngle, planetComponent.orbitalDistance, scaleComponent.y);

                positionComponent.x = x;
                positionComponent.y = y;
                fontComponent.x = x - 10;
                fontComponent.y = y - 10;
            }
        }
    }
}
