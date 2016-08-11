package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;

import ru.belyaev.holdtheball.World;
import ru.belyaev.holdtheball.WorldController;
import ru.belyaev.holdtheball.WorldRenderer;
import ru.belyaev.holdtheball.ui.ButtonFactory;

public class GameScreen extends BaseScreen {
    private static final Color CLEAR_COLOR = Color.WHITE;

    private final World mWorld;
    private final WorldRenderer mWorldRenderer;
    private final WorldController mWorldController;

    private final int mWidth;
    private final int mHeight;

    public enum State {
        READY,
        RUNNING,
        PAUSED,
        GAME_OVER
    }

    private State mState;

    private Stage mStage;

    public GameScreen(Game game) {
        super(game);
        mWidth = Gdx.graphics.getWidth();
        mHeight = Gdx.graphics.getHeight();

        mState = State.READY;
        mWorld = new World(mWidth, mHeight);
        mWorldRenderer = new WorldRenderer(mWorld, true);
        mWorldController = new WorldController(mWorld);

        mStage = new Stage();
        Gdx.input.setInputProcessor(mStage);

        TextButton button = ButtonFactory.createButton("button");
        button.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2);
        mStage.addActor(button);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // game.setScreen( new GameScreen(game) );.
                if(mState == State.RUNNING) {
                    mState = State.READY;
                }
            }
        });
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(CLEAR_COLOR.r, CLEAR_COLOR.g, CLEAR_COLOR.b, CLEAR_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        switch (mState) {
            case READY:
                updateReady(deltaTime);
                break;

            case RUNNING:
                updateRunning(deltaTime);
                break;

            case PAUSED:
                updatePaused(deltaTime);
                break;

            case GAME_OVER:
                updateGameOver(deltaTime);
                break;
        }

        //mStage.act();
        //mStage.draw();
    }

    // =============================================================================================
    // update states
    // =============================================================================================

    private ShapeRenderer mShapeRenderer;
    private SpriteBatch mSpriteBatch;
    private static final Color SHADOW_COLOR = new Color(0f, 0f, 0f, 0.5f);


    private void updateReady(float deltaTime) {
        mWorldRenderer.render();

        if (mShapeRenderer == null) {
            mShapeRenderer = new ShapeRenderer();
        }
        if (mSpriteBatch == null) {
            mSpriteBatch = new SpriteBatch();
        }

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mShapeRenderer.setColor(SHADOW_COLOR);
        mShapeRenderer.rect(0, 0, mWidth, mHeight);
        mShapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        if (Gdx.input.justTouched()) {
            mState = State.RUNNING;
        }
    }

    private void updateRunning(float deltaTime) {
        mWorld.update(deltaTime);
        mWorldRenderer.render();
    }

    private void updatePaused(float deltaTime) {

    }

    private void updateGameOver(float deltaTime) {

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
