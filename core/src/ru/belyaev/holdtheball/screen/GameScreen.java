package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ru.belyaev.holdtheball.World;
import ru.belyaev.holdtheball.WorldController;
import ru.belyaev.holdtheball.WorldRenderer;
import ru.belyaev.holdtheball.ui.ButtonFactory;
import ru.belyaev.holdtheball.ui.Styles;

public class GameScreen extends BaseScreen {
    private static final int GAME_OVER_VIBRATE_TIME = 300;

    private final World mWorld;
    private final WorldRenderer mWorldRenderer;
    private final WorldController mWorldController;

    private final int mWidth;
    private final int mHeight;

    public enum GameState {
        READY,
        RUNNING,
        PAUSED,
        GAME_OVER
    }

    private GameState mGameState;

    private Stage mReadyStateUIStage;
    private Stage mRunningStateUIStage;
    private Stage mPausedStateUIStage;
    private Stage mGameOverStateUIStage;

    private final World.WorldListener mWorldListener = new World.WorldListener() {
        @Override
        public void onBound() {
            System.out.println("onBound");
        }

        @Override
        public void onGameOver() {
            System.out.println("onGameOver");
        }
    };

    public GameScreen(Game game) {
        super(game);
        Gdx.input.setInputProcessor(this);

        mWidth = Gdx.graphics.getWidth();
        mHeight = Gdx.graphics.getHeight();

        mGameState = GameState.READY;
        mWorld = new World(mWidth, mHeight, mWorldListener);
        mWorldRenderer = new WorldRenderer(mWorld);
        mWorldController = new WorldController(mWorld);

        mReadyStateUIStage = new Stage();

        Label gameOverLabel = ButtonFactory.createLabelWhite("Hold the ball");

        gameOverLabel.setPosition(
                Gdx.graphics.getWidth() / 2 - gameOverLabel.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - gameOverLabel.getHeight() / 2 - Gdx.graphics.getHeight() / 4 - mWorld.getBall().getRadius() / 2
        );

        mReadyStateUIStage.addActor(gameOverLabel);
    }

    @Override
    public void render(float deltaTime) {
        clear();
        switch (mGameState) {
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
    private Bezier<Vector2> mBezier;
    private Vector2 tmp = new Vector2();
    private Vector2 tmp0 = new Vector2();
    private Vector2 tmp1 = new Vector2();

    private void updateReady(float deltaTime) {
        mWorldRenderer.render();

        if (mShapeRenderer == null) {
            mShapeRenderer = new ShapeRenderer();
        }
        if (mSpriteBatch == null) {
            mSpriteBatch = new SpriteBatch();
        }
        if (mBezier == null) {
            Vector2[] dataSet = {new Vector2(mWidth / 2, mHeight / 2 - mHeight / 4), new Vector2(mWidth / 2 + mWidth / 4, mHeight / 2 - mHeight / 4), new Vector2(mWidth / 2, mHeight / 2)};
            mBezier = new Bezier<Vector2>(dataSet);
        }

        // TODO ну хз. затенять экран на стадии прототипа скорее всего лишнее
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mShapeRenderer.setColor(SHADOW_COLOR);
        mShapeRenderer.rect(0, 0, mWidth, mHeight);
        mShapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        mReadyStateUIStage.act();
        mReadyStateUIStage.draw();

//        Gdx.gl.glLineWidth(5f);
//        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        mShapeRenderer.setColor(Color.YELLOW);
//        for (int i=0; i<30; i++) {
//            mBezier.valueAt(tmp0,i/30f);
//            mBezier.valueAt(tmp1,(i+1)/30f);
//            mShapeRenderer.line(tmp0,tmp1);
//        }
//        mShapeRenderer.end();
    }

    private float mScreenX = 0;
    private float mScreenY = 0;

    private void updateRunning(float deltaTime) {
        if (!mWorld.hit(mScreenX, mHeight - mScreenY)) {
            mGameState = GameState.GAME_OVER;
            Gdx.input.vibrate(GAME_OVER_VIBRATE_TIME);
        }
        mWorld.update(deltaTime);
        mWorldRenderer.render();
    }

    private void updatePaused(float deltaTime) {
        // TODO
    }

    private void updateGameOver(float deltaTime) {
        getGame().setScreen(new GameOverScreen(getGame(), mWorld.getTime()));
    }

    // =============================================================================================
    // InputProcessor implementation
    // =============================================================================================

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (mGameState == GameState.READY && mWorld.hit(screenX, mHeight - screenY)) {
            mGameState = GameState.RUNNING;

            mScreenX = screenX;
            mScreenY = screenY;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (mGameState == GameState.RUNNING) {
            mGameState = GameState.GAME_OVER;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        mScreenX = screenX;
        mScreenY = screenY;
        return true;
    }
}
