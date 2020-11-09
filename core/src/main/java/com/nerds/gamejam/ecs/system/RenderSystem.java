package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nerds.gamejam.ecs.component.CompositeSpriteComponent;
import com.nerds.gamejam.ecs.component.FontComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.RenderableComponent;
import com.nerds.gamejam.ecs.component.SpriteComponent;

@Wire
public class RenderSystem extends IteratingSystem {

    private ComponentMapper<CompositeSpriteComponent> compositeSpriteMapper;
    private ComponentMapper<SpriteComponent> spriteMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<FontComponent> fontMapper;
    private CameraSystem cameraSystem;
    private Batch batch;
    private BitmapFont font;

    public RenderSystem() {
        super(Aspect.all(RenderableComponent.class));
    }

    @Override
    protected void initialize() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
    }

    @Override
    protected void begin() {
        this.batch.setProjectionMatrix(cameraSystem.camera.combined);
        this.batch.begin();
    }

    protected void process(int e) {
        PositionComponent positionComponent = positionMapper.get(e);
        CompositeSpriteComponent compositeSpriteComponent = this.compositeSpriteMapper.get(e);
        if (compositeSpriteComponent != null) {
            for (int i = 0; i < compositeSpriteComponent.sprites.size; i++) {
                Sprite sprite = compositeSpriteComponent.sprites.get(i);
                drawSprite(sprite, positionComponent);
            }
        }
        FontComponent fontComponent = fontMapper.get(e);
        if (fontComponent != null) {
            this.font.setColor(Color.WHITE);
            this.font.draw(this.batch, fontComponent.text, fontComponent.x, fontComponent.y);
        }
        SpriteComponent spriteComponent = spriteMapper.get(e);
        if (spriteComponent != null) {
            drawSprite(spriteComponent.sprite, positionComponent);
        }
    }

    private void drawSprite(Sprite sprite, PositionComponent positionComponent) {
        sprite.setPosition(positionComponent.x, positionComponent.y);
        sprite.draw(this.batch);
    }

    @Override
    protected void end() {
        this.batch.end();
    }

    public void resize(int width, int height) {
        //todo
    }

    @Override
    protected void dispose() {
       this.batch.dispose();
       this.font.dispose();
    }
}
