package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.util.TextureLoader;

@Wire
public class RenderSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<TextureReferenceComponent> textureReferenceMapper;
    private ComponentMapper<ScaleComponent> scaleMapper;
    private ComponentMapper<BodyComponent> bodyMapper;
    private ComponentMapper<FontComponent> fontMapper;
    private final TextureLoader textureLoader;
    private CameraSystem cameraSystem;
    private Batch batch;
    private BitmapFont font;

    public RenderSystem(CameraSystem cameraSystem, TextureLoader textureLoader) {
        super(Aspect.all(PositionComponent.class, TextureReferenceComponent.class, BodyComponent.class));
        this.textureLoader = textureLoader;
        this.cameraSystem = cameraSystem;
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

    @Override
    protected void process(int e) {
        PositionComponent position = positionMapper.get(e);
        TextureReferenceComponent textureReference = textureReferenceMapper.get(e);
        BodyComponent bodyComponent = bodyMapper.get(e);
        ScaleComponent scaleComponent = scaleMapper.get(e);

        float width = scaleComponent!= null ? bodyComponent.getWidth() * scaleComponent.getX() : bodyComponent.getWidth();
        float height = scaleComponent != null ? bodyComponent.getHeight() * scaleComponent.getY() : bodyComponent.getHeight();

        textureReference.getReferences().forEach(reference -> {
            TextureRegion toDraw = textureLoader.getTexture(reference);
            batch.setColor(reference.getColor());
            batch.draw(toDraw, position.x, position.y, width, height);
        });

        drawFont(e);
    }

    private void drawFont(int e) {
        FontComponent fontComponent = this.fontMapper.get(e);
        if (fontComponent != null) {
            this.font.setColor(Color.WHITE);
            this.font.draw(this.batch, fontComponent.text, fontComponent.x, fontComponent.y);
        }
    }

    @Override
    protected void end() {
        this.batch.end();
    }

    public void resize(int width, int height) {
        cameraSystem.resize(width, height);
    }

    @Override
    protected void dispose() {
       this.batch.dispose();
       this.font.dispose();
    }
}
