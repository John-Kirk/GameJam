package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.util.CachingTextureLoader;
import com.nerds.gamejam.util.TextureReference;

import java.util.HashMap;
import java.util.Map;

@Wire
public class RenderSystem extends BaseEntitySystem {

    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<TextureReferenceComponent> textureReferenceMapper;
    private ComponentMapper<ScaleComponent> scaleMapper;
    private ComponentMapper<BodyComponent> bodyMapper;
    private ComponentMapper<FontComponent> fontMapper;
    private final CachingTextureLoader textureLoader;
    private CameraSystem cameraSystem;
    private ExtendViewport scalingViewport;
    private Batch batch;
    private BitmapFont font;
    private Map<Integer, Array<Integer>> layerMap;

    public RenderSystem(CachingTextureLoader textureLoader) {
        super(Aspect.all(PositionComponent.class, TextureReferenceComponent.class, BodyComponent.class));
        this.textureLoader = textureLoader;
        this.layerMap = new HashMap<>();
    }

    @Override
    protected void initialize() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        float worldWidth = GameJam.PLANET_VIEW_HEIGHT * w / h;
        int worldHeight = GameJam.PLANET_VIEW_HEIGHT;
        scalingViewport = new ExtendViewport(worldWidth, worldHeight, cameraSystem.camera);
        scalingViewport.update((int)w, (int)h);
        scalingViewport.apply();
        cameraSystem.camera.position.add(worldWidth * -2f, worldHeight / -2f, 0);
    }

    @Override
    protected void begin() {
        this.batch.setProjectionMatrix(cameraSystem.camera.combined);
        this.batch.begin();
    }

    @Override
    protected void inserted(int entityId) {
        TextureReferenceComponent textureReferenceComponent = textureReferenceMapper.get(entityId);
        System.out.println("Adding entity " + entityId);

        if (layerMap.get(textureReferenceComponent.layer) != null) {
            Array<Integer> entityIds = layerMap.get(textureReferenceComponent.layer);
            entityIds.add(entityId);
        } else {
            Array<Integer> entityIds = new Array<>();
            entityIds.add(entityId);
            layerMap.put(textureReferenceComponent.layer, entityIds);
        }
    }

    @Override
    protected void removed(int entityId) {
        TextureReferenceComponent textureReferenceComponent = textureReferenceMapper.get(entityId);
        if (layerMap.containsKey(textureReferenceComponent.layer)) {
            Array<Integer> entityIds = layerMap.get(entityId);
            entityIds.removeValue(entityId, true);
        }
    }

    @Override
    protected void processSystem() {
        scalingViewport.apply();

        layerMap.keySet().stream().sorted(Integer::compareTo).forEach(i -> {
            layerMap.get(i).forEach(e -> {
                PositionComponent position = positionMapper.get(e);
                TextureReferenceComponent textureReference = textureReferenceMapper.get(e);
                BodyComponent bodyComponent = bodyMapper.get(e);
                ScaleComponent scaleComponent = scaleMapper.get(e);

                float width = scaleComponent!= null ? bodyComponent.width * scaleComponent.x : bodyComponent.width;
                float height = scaleComponent != null ? bodyComponent.height * scaleComponent.y : bodyComponent.height;

                for (TextureReference reference : textureReference.references) {
                    TextureRegion toDraw = textureLoader.getTexture(reference);
                    batch.setColor(reference.getColor());
                    batch.draw(toDraw, position.x, position.y, width, height);
                }

                drawFont(e);
            });
        });
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
        scalingViewport.update(width, height);
        scalingViewport.apply();
        cameraSystem.resize(width, height);
    }

    @Override
    protected void dispose() {
       this.batch.dispose();
       this.font.dispose();
    }
}
