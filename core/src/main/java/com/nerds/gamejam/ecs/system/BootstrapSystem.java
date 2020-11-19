package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.util.CachingTextureLoader;
import com.nerds.gamejam.util.TextureReference;
import com.nerds.gamejam.gameplay.character.Monster;
import com.nerds.gamejam.util.TextureRegionFactory;

import static com.nerds.gamejam.ecs.component.TextureReferenceComponent.BACKGROUND;

public class BootstrapSystem extends BaseSystem {

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
    }

    private void addMonster() {
        Animation<TextureRegion> anim = new Animation<>(0.35f, monsterTextureRegionArray, Animation.PlayMode.LOOP);
        String animRef = textureLoader.cacheAnimation(anim);

        float scale = (float) GameJam.PLANET_VIEW_HEIGHT / Monster.HEIGHT;

        this.world.createEntity().edit()
            .add(new MonsterComponent())
            .add(new PositionComponent(-150, 0))
            .add(new VelocityComponent(0, 0))
            .add(new BodyComponent(Monster.WIDTH, Monster.HEIGHT))
            .add(new ScaleComponent(scale, scale))
            .add(new AnimationComponent(animRef))
            .add(InMotionComponent.INSTANCE);
    }

    @Override
    protected void processSystem() {
        //nothing to process
    }
}
