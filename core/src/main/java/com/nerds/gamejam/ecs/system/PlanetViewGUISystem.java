package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.gameplay.character.Monster;
import com.nerds.gamejam.screen.MenuScreen;
import com.nerds.gamejam.util.TextureRegionFactory;

public class PlanetViewGUISystem extends BaseSystem {

    private final GameJam game;
    private final MenuScreen menuScreen;
    private final Array<TextureRegion> monsterTextureRegionArray;
    private Stage stage;
    private static final Texture MONSTER_TEXTURE = new Texture("animation/monster_spritesheet.png");


    public PlanetViewGUISystem(GameJam game, MenuScreen menuScreen) {
        this.game = game;
        this.menuScreen = menuScreen;
        this.monsterTextureRegionArray = TextureRegionFactory
                .createTextureRegionArray(MONSTER_TEXTURE, Monster.WIDTH, GameJam.PLANET_VIEW_HEIGHT, 4);
    }

    @Override
    protected void initialize() {
        this.stage = new Stage();
        this.stage.addActor(createPauseButton());
        addMonster();
    }

    private void addMonster() {
        Sprite monsterSprite = new Sprite(MONSTER_TEXTURE);
        monsterSprite.setSize(Monster.WIDTH, GameJam.PLANET_VIEW_HEIGHT);
        this.world.createEntity().edit()
                .add(new MonsterComponent())
                .add(new PositionComponent(0, 0))
                .add(new VelocityComponent(0, 0))
                .add(new AnimationComponent(monsterTextureRegionArray, 5f, Animation.PlayMode.LOOP))
                .add(RenderableComponent.INSTANCE);
    }

    private Button createPauseButton() {
        Button button = new TextButton("||", GameJam.skin);
        button.setPosition(
              Gdx.graphics.getWidth() - button.getWidth() - 10,
              Gdx.graphics.getHeight() - button.getHeight() - 10
        );
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(menuScreen);
            }

        });
        return button;
    }

    @Override
    protected void processSystem() {
        Gdx.input.setInputProcessor(this.stage);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    protected void dispose() {
        stage.dispose();
    }

}
