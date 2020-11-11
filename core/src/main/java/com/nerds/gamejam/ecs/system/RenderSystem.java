package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.nerds.gamejam.ecs.component.AnimationComponent;
import com.nerds.gamejam.ecs.component.CompositeSpriteComponent;
import com.nerds.gamejam.ecs.component.FontComponent;
import com.nerds.gamejam.ecs.component.CircleComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.RenderableComponent;
import com.nerds.gamejam.ecs.component.SpriteComponent;

@Wire
public class RenderSystem extends IteratingSystem {

    private ComponentMapper<CompositeSpriteComponent> compositeSpriteMapper;
    private ComponentMapper<SpriteComponent> spriteMapper;
    private ComponentMapper<CircleComponent> circleMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<FontComponent> fontMapper;
    private ComponentMapper<AnimationComponent> animationMapper;
    private CameraSystem cameraSystem;
    private Batch batch;
    private BitmapFont font;
    private ShapeRenderer circleRenderer;

    public RenderSystem() {
        super(Aspect.all(RenderableComponent.class));
    }

    @Override
    protected void initialize() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.circleRenderer = new ShapeRenderer();
    }

    @Override
    protected void begin() {
        this.batch.begin();
        this.batch.setProjectionMatrix(cameraSystem.camera.combined);
        this.circleRenderer.setProjectionMatrix(cameraSystem.camera.combined);
        this.circleRenderer.begin(ShapeRenderer.ShapeType.Line);
    }

    @Override
    protected void process(int e) {
        drawCircle(e);
        drawComposites(e);
        drawSingleSprite(e);
        drawFont(e);
        drawAnimation(e);
    }

    private void drawCircle(int e) {
        CircleComponent circleComponent = this.circleMapper.get(e);
        if (circleComponent != null) {
            this.circleRenderer.setColor(Color.WHITE);
            this.circleRenderer.circle(circleComponent.x , circleComponent.y, circleComponent.radius);
        }
    }

    private void drawSingleSprite(int e) {
        SpriteComponent spriteComponent = this.spriteMapper.get(e);
        if (spriteComponent != null) {
            PositionComponent positionComponent = this.positionMapper.get(e);
            drawSprite(spriteComponent.sprite, positionComponent);
        }
    }

    private void drawFont(int e) {
        FontComponent fontComponent = this.fontMapper.get(e);
        if (fontComponent != null) {
            this.font.setColor(Color.WHITE);
            this.font.draw(this.batch, fontComponent.text, fontComponent.x, fontComponent.y);
        }
    }

    private void drawComposites(int e) {
        PositionComponent positionComponent = this.positionMapper.get(e);
        CompositeSpriteComponent compositeSpriteComponent = this.compositeSpriteMapper.get(e);
        if (compositeSpriteComponent != null) {
            for (int i = 0; i < compositeSpriteComponent.sprites.size; i++) {
                Sprite sprite = compositeSpriteComponent.sprites.get(i);
                drawSprite(sprite, positionComponent);
            }
        }
    }

    private void drawAnimation(int e) {
        AnimationComponent animationComponent = animationMapper.get(e);
        if (animationComponent != null) {
            PositionComponent positionComponent = positionMapper.get(e);
            animationComponent.elapsedTime += Gdx.graphics.getDeltaTime();
            TextureRegion keyFrame = animationComponent.animation.getKeyFrame(animationComponent.elapsedTime);
            this.batch.draw(keyFrame, positionComponent.x, positionComponent.y);
            if (animationComponent.animation.getPlayMode() == Animation.PlayMode.NORMAL &&
                  animationComponent.animation.isAnimationFinished(animationComponent.elapsedTime)) {
                this.world.edit(e).remove(animationComponent);
            }
        }
    }

    private void drawSprite(Sprite sprite, PositionComponent positionComponent) {
        sprite.setPosition(positionComponent.x, positionComponent.y);
        sprite.draw(this.batch);
    }

    @Override
    protected void end() {
        this.batch.end();
        this.circleRenderer.end();
    }

    public void resize(int width, int height) {
        cameraSystem.resize(width, height);
    }

    @Override
    protected void dispose() {
        this.batch.dispose();
        this.circleRenderer.dispose();
        this.font.dispose();
    }
}
