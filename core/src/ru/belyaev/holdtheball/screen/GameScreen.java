package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ru.belyaev.holdtheball.HoldTheBall;
import ru.belyaev.holdtheball.World;
import ru.belyaev.holdtheball.WorldController;
import ru.belyaev.holdtheball.WorldRenderer;
import ru.belyaev.holdtheball.util.Assets;
import ru.belyaev.holdtheball.util.Dimens;
import ru.belyaev.holdtheball.util.Strings;

public class GameScreen extends BaseScreen {
    private static final int GAME_OVER_VIBRATE_TIME = 300;

    private final World mWorld;
    private final WorldRenderer mWorldRenderer;
    private final WorldController mWorldController;

    private final int mWidth;
    private final int mHeight;

    private Stage mReadyStateUIStage;
    private Stage mRunningStateUIStage;
    private Stage mGameOverStateUIStage;

    private final Label mTimeLabel;

    private final World.WorldListener mWorldListener = new World.WorldListener() {
        @Override
        public void onBound() {
            Assets.sBallHitSound.play();
        }
    };

    public GameScreen(HoldTheBall game) {
        super(game);
        Gdx.input.setInputProcessor(this);

        mWidth = Gdx.graphics.getWidth();
        mHeight = Gdx.graphics.getHeight();

        mWorld = new World(mWidth, mHeight, mWorldListener);
        mWorldRenderer = new WorldRenderer(mWorld, getGame().getShapeRenderer(), getGame().getSpriteBatch());
        mWorldController = new WorldController(mWorld);

        mReadyStateUIStage = new Stage(); // TODO передавать getGame().getSpriteBatch()

        final Label holdTheBallLabel = new Label(Strings.HOLD_THE_BALL_STRING, Assets.sSkin, "black-label-style");
        holdTheBallLabel.setPosition(
                Gdx.graphics.getWidth() / 2 - holdTheBallLabel.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - holdTheBallLabel.getHeight() / 2 - Gdx.graphics.getHeight() / 4 - mWorld.getBall().getScaledRadius() / 2
        );
        mReadyStateUIStage.addActor(holdTheBallLabel);

        final int marginLeft = Dimens.toDpi(8);
        final int marginTop = Dimens.toDpi(4);

        mRunningStateUIStage = new Stage(); // TODO передавать getGame().getSpriteBatch()
        mTimeLabel = new Label(Strings.getTimeString(0), Assets.sSkin, "black-label-style");
        mTimeLabel.setPosition(
                marginLeft,
                mWorld.getHeight() - mTimeLabel.getHeight() - marginTop
        );
        mRunningStateUIStage.addActor(mTimeLabel);
    }

    @Override
    public void render(float deltaTime) {
        clear();
        switch (mWorld.getWorldState()) {
            case READY:
                updateReady(deltaTime);
                break;

            case RUNNING:
                updateRunning(deltaTime);
                break;

            case GAME_OVER:
                updateGameOver(deltaTime);
                break;
        }
    }

    // =============================================================================================
    // update states
    // =============================================================================================

    private void updateReady(float deltaTime) {
        mWorldRenderer.render();

        mReadyStateUIStage.act(deltaTime);
        mReadyStateUIStage.draw();
    }

    private void updateRunning(float deltaTime) {
        mWorldController.update(deltaTime);
        mWorldRenderer.render();

        // TODO update UI method
        mTimeLabel.setText(Strings.getTimeString(mWorld.getTime()));
        mRunningStateUIStage.act(deltaTime);
        mRunningStateUIStage.draw();
    }

    private void updateGameOver(float deltaTime) {
        Gdx.input.vibrate(GAME_OVER_VIBRATE_TIME);
        //mWorld.setWorldState(World.WorldState.RUNNING);
        getGame().setScreen(new GameOverScreen(getGame(), mWorld.getTime()));
    }

    // =============================================================================================
    // InputProcessor implementation
    // =============================================================================================

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mWorldController.touchDown(screenX, mHeight - screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mWorldController.touchUp(screenX, mHeight - screenY);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        mWorldController.touchDragged(screenX, mHeight - screenY);
        return true;
    }
}
