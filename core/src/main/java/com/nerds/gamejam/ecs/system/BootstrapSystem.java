package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.ecs.component.TextureReferenceComponent.TextureReference;
import com.nerds.gamejam.gameplay.character.Monster;
import com.nerds.gamejam.util.TextureLoader;
import com.nerds.gamejam.util.TextureRegionFactory;

import java.util.Collections;

public class BootstrapSystem extends BaseSystem {

    private static final Texture MONSTER_TEXTURE = new Texture("animation/monster_spritesheet.png");
    private Array<TextureRegion> monsterTextureRegionArray;

    private TextureLoader textureLoader;

    public BootstrapSystem(TextureLoader textureLoader) {
        this.textureLoader = textureLoader;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.monsterTextureRegionArray = TextureRegionFactory
              .createTextureRegionArray(MONSTER_TEXTURE, Monster.WIDTH, Monster.HEIGHT, 4);
        TextureReference textureReference = new TextureReference("stars.png", Color.WHITE);
        textureReference.setTextureWrap(Texture.TextureWrap.Repeat);
        this.world.createEntity().edit()
                .add(new PositionComponent(0, 0))
                .add(new BodyComponent(800, 600))
                .add(new ScaleComponent(3.5f, 3.5f))
                .add(new TextureReferenceComponent(Collections.singletonList(textureReference)));
                addMonster();
    }

    private void addMonster() {
        Animation<TextureRegion> anim = new Animation<>(0.35f, monsterTextureRegionArray, Animation.PlayMode.LOOP);
        String animRef = textureLoader.cacheAnimation(anim);

        this.world.createEntity().edit()
              .add(new MonsterComponent())
              .add(new PositionComponent(-150, 0))
              .add(new VelocityComponent(0, 0))
              .add(new BodyComponent(Monster.WIDTH, GameJam.PLANET_VIEW_HEIGHT))
              .add(new AnimationComponent(animRef))
              .add(InMotionComponent.INSTANCE);
    }

    @Override
    protected void processSystem() {
        //nothing to process
    }
}
