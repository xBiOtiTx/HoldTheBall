package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ru.belyaev.holdtheball.World;
import ru.belyaev.holdtheball.WorldController;
import ru.belyaev.holdtheball.WorldRenderer;
import ru.belyaev.holdtheball.ui.ButtonFactory;

public class GameScreen extends BaseScreen {
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
        Gdx.input.setInputProcessor(this);

        mWidth = Gdx.graphics.getWidth();
        mHeight = Gdx.graphics.getHeight();

        mState = State.READY;
        mWorld = new World(mWidth, mHeight);
        mWorldRenderer = new WorldRenderer(mWorld, true);
        mWorldController = new WorldController(mWorld);

        mStage = new Stage();

        Label gameOverLabel = ButtonFactory.createLabel("Hold the ball");
        gameOverLabel.setPosition(
                Gdx.graphics.getWidth() / 2 - gameOverLabel.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - gameOverLabel.getHeight() / 2
        );
        mStage.addActor(gameOverLabel);
    }

    @Override
    public void render(float deltaTime) {
        clear();
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

        // TODO ну хз. затенять экран на стадии прототипа скорее всего лишнее
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mShapeRenderer.setColor(SHADOW_COLOR);
        mShapeRenderer.rect(0, 0, mWidth, mHeight);
        mShapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        mStage.act();
        mStage.draw();
    }

    private void updateRunning(float deltaTime) {
        mWorld.update(deltaTime);
        mWorldRenderer.render();

        float screenX = Gdx.input.getX();
        float screenY = Gdx.input.getY();
        if(!mWorld.hit(screenX, mHeight - screenY)) {
            mState = State.GAME_OVER;
        }
    }

    private void updatePaused(float deltaTime) {
        // TODO
    }

    private void updateGameOver(float deltaTime) {
        getGame().setScreen(new GameOverScreen(getGame()));
    }

    // =============================================================================================
    // InputProcessor implementation
    // =============================================================================================

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(mState == State.READY) {
            mState = State.RUNNING;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mState = State.GAME_OVER;
        return true;
    }
}
