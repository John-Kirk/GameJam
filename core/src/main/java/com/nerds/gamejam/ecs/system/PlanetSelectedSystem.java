package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.ActorComponent;
import com.nerds.gamejam.ecs.component.SelectedPlanet;
import com.nerds.gamejam.util.PositionUtil;

public class PlanetSelectedSystem extends BaseEntitySystem {

    private ComponentMapper<SelectedPlanet> selectedPlanetComponentMapper;

    private final PositionUtil positionUtil;
    private Dialog planetPopup;

    public PlanetSelectedSystem(PositionUtil positionUtil) {
        super(Aspect.all(SelectedPlanet.class));
        this.positionUtil = positionUtil;
    }

    @Override
    protected void processSystem() {
        IntBag bag = getEntityIds();
        if (bag.size() > 0) {
            int entityId = bag.get(0);
            positionPopup(entityId);
        }
    }

    @Override
    protected void inserted(int entityId) {
        IntBag bag = getEntityIds();

        for(int i = 0; i < bag.size(); i++) {
            int id = bag.get(i);

            if (id != entityId) {
                world.edit(id).remove(SelectedPlanet.class);
            }
        }

        if (planetPopup != null) {
            planetPopup.remove();
        }

        SelectedPlanet selectedPlanet = selectedPlanetComponentMapper.get(entityId);
        planetPopup = new Dialog(selectedPlanet.name, GameJam.skin);
        Label label = new Label(selectedPlanet.desc, GameJam.skin);
        label.setWrap(true);
        planetPopup.getContentTable().add(label).fill().expand();
        TextButton textButton = new TextButton("Go", GameJam.skin);
        planetPopup.getButtonTable().add(textButton).expandX().padRight(5).align(Align.bottomRight);
        planetPopup.setKeepWithinStage(false);
        world.createEntity().edit().add(new ActorComponent(planetPopup));
    }

    private void positionPopup(int entityId) {
        SelectedPlanet selectedPlanet = selectedPlanetComponentMapper.get(entityId);

        Vector2 v = positionUtil.toWorldPosition(new Vector2(selectedPlanet.x, selectedPlanet.y));

        float x = v.x;
        float y = v.y;
        float radius = selectedPlanet.radius;

        if (planetPopup != null) {
            planetPopup.setPosition(x + radius * 4 + 10, y - planetPopup.getHeight() / 2);
        }
    }
}
