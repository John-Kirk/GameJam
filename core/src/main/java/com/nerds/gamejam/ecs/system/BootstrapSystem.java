package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.AnimationComponent;
import com.nerds.gamejam.ecs.component.BackgroundComponent;
import com.nerds.gamejam.ecs.component.InMotionComponent;
import com.nerds.gamejam.ecs.component.MonsterComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.RenderableComponent;
import com.nerds.gamejam.ecs.component.SpriteComponent;
import com.nerds.gamejam.ecs.component.VelocityComponent;
import com.nerds.gamejam.gameplay.character.Monster;
import com.nerds.gamejam.util.TextureRegionFactory;

public class BootstrapSystem extends BaseSystem {

    private static final Texture BACKGROUND_TEXTURE = new Texture("stars.png");
    private static final Texture MONSTER_TEXTURE = new Texture("animation/monster_spritesheet.png");
    private Array<TextureRegion> monsterTextureRegionArray;

    static {
        BACKGROUND_TEXTURE.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.monsterTextureRegionArray = TextureRegionFactory
              .createTextureRegionArray(MONSTER_TEXTURE, Monster.WIDTH, GameJam.PLANET_VIEW_HEIGHT, 4);
        this.world.createEntity().edit()
                .add(new BackgroundComponent())
                .add(new PositionComponent(0, 0))
                .add(new SpriteComponent(new Sprite(BACKGROUND_TEXTURE)));
        addMonster();
    }

    private void addMonster() {
        Sprite monsterSprite = new Sprite(MONSTER_TEXTURE);
        monsterSprite.setSize(Monster.WIDTH, GameJam.PLANET_VIEW_HEIGHT);
        this.world.createEntity().edit()
              .add(new MonsterComponent())
              .add(new PositionComponent(-150, 0))
              .add(new VelocityComponent(0, 0))
              .add(new AnimationComponent(monsterTextureRegionArray, 0.35f, Animation.PlayMode.LOOP))
              .add(InMotionComponent.INSTANCE)
              .add(RenderableComponent.INSTANCE);
    }

    @Override
    protected void processSystem() {
        //nothing to process
    }
}
