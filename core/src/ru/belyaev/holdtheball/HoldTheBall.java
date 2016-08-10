package ru.belyaev.holdtheball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// extends Game and set Screen on create
public class HoldTheBall extends ApplicationAdapter {
    private World mWorld;
    private WorldRenderer mWorldRenderer;

    @Override
    public void create() {
        mWorld = new World(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mWorldRenderer = new WorldRenderer(mWorld);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mWorld.update(Gdx.graphics.getDeltaTime());
        mWorldRenderer.render();
    }
}
