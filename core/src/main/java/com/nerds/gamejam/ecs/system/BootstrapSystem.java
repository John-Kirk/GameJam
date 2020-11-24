package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.gameplay.character.CrewMember;
import com.nerds.gamejam.gameplay.character.stat.Stat;
import com.nerds.gamejam.gameplay.character.stat.StatBlock;
import com.nerds.gamejam.gameplay.character.stat.StatValue;
import com.nerds.gamejam.gameplay.ship.Ship;
import com.nerds.gamejam.gameplay.ship.inventory.Inventory;
import com.nerds.gamejam.util.CachingTextureLoader;
import com.nerds.gamejam.util.TextureReference;
import com.nerds.gamejam.gameplay.character.Monster;
import com.nerds.gamejam.util.TextureRegionFactory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.nerds.gamejam.ecs.component.TextureReferenceComponent.BACKGROUND;

public class BootstrapSystem extends BaseSystem {

    public String monsterAnimReference;

    private static final Texture MONSTER_TEXTURE = new Texture("animation/monster_spritesheet.png");
    private Array<TextureRegion> monsterTextureRegionArray;

    private CachingTextureLoader textureLoader;

    public BootstrapSystem(CachingTextureLoader textureLoader) {
        this.textureLoader = textureLoader;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.monsterTextureRegionArray = TextureRegionFactory
              .createTextureRegionArray(MONSTER_TEXTURE, Monster.WIDTH, Monster.HEIGHT, 4);
        TextureReference textureReference = new TextureReference("stars.png", Color.WHITE);
        textureReference.setTextureWrap(Texture.TextureWrap.Repeat);
        TextureReferenceComponent textureReferenceComponent = new TextureReferenceComponent(textureReference);
        textureReferenceComponent.layer = BACKGROUND;
        this.world.createEntity().edit()
                .add(new PositionComponent(0, 0))
                .add(new BodyComponent(800, 600))
                .add(new ScaleComponent(GameJam.PLANET_VIEW_WIDTH / 800f, GameJam.PLANET_VIEW_HEIGHT / 600f))
                .add(textureReferenceComponent);
        addMonster();
        createShip();
    }

    private void addMonster() {
        Animation<TextureRegion> anim = new Animation<>(0.35f, monsterTextureRegionArray, Animation.PlayMode.LOOP);
        monsterAnimReference = textureLoader.cacheAnimation(anim);
//
//        float scale = (float) GameJam.PLANET_VIEW_HEIGHT / Monster.HEIGHT;
//
//        this.world.createEntity().edit()
//            .add(new MonsterComponent())
//            .add(new PositionComponent((int) Math.ceil(Monster.WIDTH * scale * -1), 0))
//            .add(new VelocityComponent(0, 0))
//            .add(new BodyComponent(Monster.WIDTH, Monster.HEIGHT))
//            .add(new ScaleComponent(scale, scale))
//            .add(new AnimationComponent(monsterAnimReference))
//            .add(InMotionComponent.INSTANCE);
    }

    private void createShip() {

        List<CrewMember> crewMembers = new LinkedList<>();
        crewMembers.add(new CrewMember("bountyhunter", createRandomStatBlock()));
        crewMembers.add(new CrewMember("captain", createRandomStatBlock()));
        crewMembers.add(new CrewMember("knight", createRandomStatBlock()));
        crewMembers.add(new CrewMember("robot", createRandomStatBlock()));
        crewMembers.add(new CrewMember("technomancer", createRandomStatBlock()));

        Ship ship = new Ship(crewMembers, new Inventory());

        BodyComponent body = new BodyComponent(64, 64);
        body.physicalBody = new Rectangle(800, 600, 64, 64);
        this.world.createEntity().edit()
            .add(new ShipComponent(ship))
            .add(new TextureReferenceComponent(new TextureReference("ships/spaceship.png")))
            .add(new PositionComponent(800, 600))
            .add(body)
            .add(new ClickableComponent((worldX, worldY, screenX, screenY, button) -> {

                Array<DialogComponent> dialogs = new Array<>();

                for (int i = 0; i < 3; i++) {
                    dialogs.add(createDialog(crewMembers, createRandomSpeech(i)));
                }

                Stack stack = new Stack();
                Image actor = new Image(textureLoader.getTexture(new TextureReference("ships/planetship.png")));
                actor.setSize(300, 300);
                stack.add(actor);

                SceneComponent sceneComponent = new SceneComponent(dialogs, new ActorComponent(stack), () -> {});

                this.world.createEntity().edit()
                    .add(sceneComponent);
                return true;
            }));
    }

    private DialogComponent createDialog(List<CrewMember> crewMembers, Array<String> lines) {
        return new DialogComponent(crewMembers.get(GameJam.randomSeed.getRandomGenerator().nextInt(crewMembers.size())), lines);
    }

    private StatBlock createRandomStatBlock() {
        Map<Stat, StatValue> stats = new LinkedHashMap<>();

        for (Stat stat : Stat.values()) {
            int value = GameJam.randomSeed.getRandomGenerator().nextInt(13) + 8;
            stats.put(stat, new StatValue(value, value));
        }

        return new StatBlock(stats);
    }

    private Array<String> createRandomSpeech(int i) {
        Array<String> speech = new Array<>();

        speech.add("Hello world! " + i);
        speech.add("I'm a test speech...");
        speech.add("but I bet my dialogue is so great you couldn't even tell!");
        speech.add("Anyhoo, I have to be going now...");
        speech.add("Byeeeeeeee.......");

        return speech;
    }

    @Override
    protected void processSystem() {
        //nothing to process
    }
}
