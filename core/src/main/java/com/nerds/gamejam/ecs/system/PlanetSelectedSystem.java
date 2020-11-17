package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.ActorComponent;
import com.nerds.gamejam.ecs.component.SelectedPlanet;
import com.nerds.gamejam.util.InputUtil;
import com.nerds.gamejam.util.PositionUtil;

@Wire
public class PlanetSelectedSystem extends BaseEntitySystem {

    public static final float ZOOM_DURATION = 1.5f;
    private ComponentMapper<SelectedPlanet> selectedPlanetComponentMapper;
    private CameraSystem cameraSystem;
    private RenderSystem renderSystem;

    private PositionUtil positionUtil;
    private Table table;
    private float timeToZoom;
    private float cameraZoomOrigin;
    private float cameraXOrigin;
    private float cameraYOrigin;

    public PlanetSelectedSystem(PositionUtil positionUtil) {
        super(Aspect.all(SelectedPlanet.class));
        this.positionUtil = positionUtil;
    }

    @Override
    protected void processSystem() {
        IntBag bag = getEntityIds();
        if (bag.size() > 0) {
            int entityId = bag.get(0);
            SelectedPlanet selectedPlanet = selectedPlanetComponentMapper.get(entityId);

            if (timeToZoom >= 0){
                timeToZoom -= this.world.getDelta();
                float progress = timeToZoom < 0 ? 1 : 1f - timeToZoom / ZOOM_DURATION;
                cameraSystem.camera.zoom = Interpolation.linear.apply(cameraZoomOrigin, 0, progress);
                cameraSystem.camera.position.x = Interpolation.exp10Out.apply(cameraXOrigin, selectedPlanet.x + selectedPlanet.radius * 2, progress);
                cameraSystem.camera.position.y = Interpolation.exp10Out.apply(cameraYOrigin, selectedPlanet.y + selectedPlanet.radius, progress);
            }

            cameraSystem.camera.update();
            renderSystem.renderFont = false;
            InputUtil.INPUT_ALLOWED = false;
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

        if (table != null) {
            table.remove();
        }


        table = new Table();
        table.add().fill().expand();
        table.add().fill().expand();
        table.add(createPopup(entityId)).fill().expand().pad(50);
        table.setFillParent(true);

        world.createEntity().edit().add(new ActorComponent(table));

        timeToZoom = ZOOM_DURATION;
        cameraZoomOrigin = cameraSystem.camera.zoom;
        cameraXOrigin = cameraSystem.camera.position.x;
        cameraYOrigin = cameraSystem.camera.position.y;
    }

    private Dialog createPopup(int entityId) {
        SelectedPlanet selectedPlanet = selectedPlanetComponentMapper.get(entityId);
        Dialog planetPopup = new Dialog(selectedPlanet.name, GameJam.skin);
        planetPopup.setWidth(Gdx.graphics.getWidth() / 3f);
        planetPopup.setHeight(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10f);
        planetPopup.setModal(true);
        planetPopup.setMovable(false);

        Label label = new Label(selectedPlanet.desc, GameJam.skin);
        label.setWrap(true);
        planetPopup.getContentTable().add(label).fill().expand();

        addAbortButton(planetPopup, entityId);
        addGoButton(planetPopup);

        return planetPopup;
    }

    private void addGoButton(Dialog planetPopup) {
        TextButton goButton = new TextButton(GameJam.gameStrings.get("goButton"), GameJam.skin);
        planetPopup.getButtonTable().add(goButton).expandX().padRight(5).align(Align.bottomRight);
    }

    private void addAbortButton(Dialog planetPopup, int entityId) {
        TextButton abortButton = new TextButton(GameJam.gameStrings.get("abortButton"), GameJam.skin);
        abortButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                world.edit(entityId).remove(SelectedPlanet.class);
                planetPopup.remove();
                renderSystem.renderFont = true;
                InputUtil.INPUT_ALLOWED = true;
            }
        });
        planetPopup.getButtonTable().add(abortButton).expandX().padLeft(5).align(Align.bottomLeft);
    }
}
