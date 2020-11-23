package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.gameplay.character.CrewMember;
import com.nerds.gamejam.gameplay.character.Monster;
import com.nerds.gamejam.util.CachingTextureLoader;
import com.nerds.gamejam.util.InputUtil;
import com.nerds.gamejam.util.TextureReference;

@Wire
public class GameIntroSystem extends IteratingSystem {

    private final CachingTextureLoader textureLoader;
    private CameraSystem cameraSystem;
    private PlanetMapGeneratorSystem planetMapGeneratorSystem;
    private ComponentMapper<MonsterComponent> monsterMapper;
    private ComponentMapper<VelocityComponent> velocityMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<InMotionComponent> inMotionComponentComponentMapper;
    private ComponentMapper<ShipComponent> shipComponentComponentMapper;
    private boolean cameraCurrentlyZooming = true;
    private Array<SceneComponent> sceneComponents;
    public SceneComponent currentScene;

    public GameIntroSystem(CachingTextureLoader textureLoader) {
        super(Aspect.all(ShipComponent.class));
        this.textureLoader = textureLoader;
    }

    @Override
    protected void process(int e) {
        if (currentScene == null && sceneComponents != null && sceneComponents.size > 0) {
            SceneComponent component = sceneComponents.removeIndex(0);
            world.createEntity().edit().add(component);
            this.currentScene = component;
        }

        if (this.currentScene == null && sceneComponents.size == 0) {
            planetMapGeneratorSystem.createSolarSystem();
            this.setEnabled(false);
        }
    }

    @Override
    protected void inserted(int entityId) {
        ShipComponent shipComponent = shipComponentComponentMapper.get(entityId);
        CrewMember crewMember = shipComponent.ship.getCrewMember("captain");

        sceneComponents = new Array<>();
        sceneComponents.add(createFirstScene(crewMember));
        sceneComponents.add(createSecondScene(crewMember));
        sceneComponents.add(createThirdScene(crewMember));
        sceneComponents.add(createFourthScene(crewMember));
    }

    private SceneComponent createFirstScene(CrewMember crewMember) {
        Array<String> strings = new Array<>();
        strings.add(GameJam.gameStrings.get("line1"));
        strings.add(GameJam.gameStrings.get("line2"));
        DialogComponent dialog1 = new DialogComponent(crewMember, strings);

        Table table = new Table();
        table.setFillParent(true);

        Array<DialogComponent> dialogs = new Array<>();
        dialogs.add(dialog1);

        return new SceneComponent(dialogs, new ActorComponent(table));
    }

    private SceneComponent createSecondScene(CrewMember crewMember) {
        Array<String> strings = new Array<>();
        strings.add(GameJam.gameStrings.get("line3"));
        strings.add(GameJam.gameStrings.get("line4"));
        strings.add(GameJam.gameStrings.get("line5"));
        DialogComponent dialog1 = new DialogComponent(crewMember, strings);

        Table table = new Table();
        table.setFillParent(true);

        Array<DialogComponent> dialogs = new Array<>();
        dialogs.add(dialog1);

        return new SceneComponent(dialogs, new ActorComponent(table));
    }

    private SceneComponent createThirdScene(CrewMember crewMember) {
        Array<String> strings = new Array<>();
        strings.add(GameJam.gameStrings.get("line6"));
        strings.add(GameJam.gameStrings.get("line7"));
        DialogComponent dialog1 = new DialogComponent(crewMember, strings);

        Table table = new Table();
        table.setFillParent(true);

        Array<DialogComponent> dialogs = new Array<>();
        dialogs.add(dialog1);

        return new SceneComponent(dialogs, new ActorComponent(table));
    }

    private SceneComponent createFourthScene(CrewMember crewMember) {
        Array<String> strings = new Array<>();
        strings.add(GameJam.gameStrings.get("line8"));
        strings.add(GameJam.gameStrings.get("line9"));
        strings.add(GameJam.gameStrings.get("line10"));
        DialogComponent dialog1 = new DialogComponent(crewMember, strings);

        Table table = new Table();
        table.setFillParent(true);

        Array<DialogComponent> dialogs = new Array<>();
        dialogs.add(dialog1);

        return new SceneComponent(dialogs, new ActorComponent(table));
    }

    private void beginMonsterMovement(int e) {
        MonsterComponent monsterComponent = monsterMapper.get(e);
        monsterComponent.xPositionGoal = 0;
        VelocityComponent velocityComponent = velocityMapper.get(e);
        velocityComponent.velocityX = Monster.SPEED / 10;
        world.edit(e).add(InMotionComponent.INSTANCE);
    }

}
