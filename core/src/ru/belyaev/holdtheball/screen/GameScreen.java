package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import ru.belyaev.holdtheball.World;
import ru.belyaev.holdtheball.WorldController;
import ru.belyaev.holdtheball.WorldRenderer;

public class GameScreen extends BaseScreen {
    private static final Color CLEAR_COLOR = Color.WHITE;

    private final World mWorld;
    private final WorldRenderer mWorldRenderer;
    private final WorldController mWorldController;

    private final int mWidth;
    private final int mHeight;

    public GameScreen(Game game) {
        super(game);
        mWidth = Gdx.graphics.getWidth();
        mHeight = Gdx.graphics.getHeight();

        mWorld = new World(mWidth, mHeight);
        mWorldRenderer = new WorldRenderer(mWorld);
        mWorldController = new WorldController(mWorld);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(CLEAR_COLOR.r, CLEAR_COLOR.g, CLEAR_COLOR.b, CLEAR_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mWorld.update(deltaTime);
        mWorldRenderer.render();
    }

    // =============================================================================================
    // InputProcessor implementation
    // =============================================================================================

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // if(clicked) controller.start()
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // if(clicked) controller.gameOver()
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // if(clicked) controller.drag()
        return false;
    }
}
