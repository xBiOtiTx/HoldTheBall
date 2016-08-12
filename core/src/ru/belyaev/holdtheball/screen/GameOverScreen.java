package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ru.belyaev.holdtheball.GamePreferences;
import ru.belyaev.holdtheball.ui.ButtonFactory;

public class GameOverScreen extends BaseScreen {

    private final GamePreferences mGamePreferences;

    private final Stage mStage;

    public GameOverScreen(Game game, float time) {
        super(game);
        mGamePreferences = new GamePreferences();

        mStage = new Stage();
        Gdx.input.setInputProcessor(mStage);

        Label timeLabel = ButtonFactory.createLabel("Your time: " + time);
        timeLabel.setPosition(
                Gdx.graphics.getWidth() / 2 - timeLabel.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 + 250
        );
        mStage.addActor(timeLabel);

        float bestTime = mGamePreferences.getBestTime();
        final boolean newBest = time > bestTime;
        bestTime = Math.max(bestTime, time);

        Label bestTimeLabel = ButtonFactory.createLabel("Best time: " + bestTime);
        bestTimeLabel.setPosition(
                Gdx.graphics.getWidth() / 2 - bestTimeLabel.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 + 175
        );
        mStage.addActor(bestTimeLabel);

        if (newBest) {
            mGamePreferences.setBestTime(bestTime);
            Label newBestTimeLabel = ButtonFactory.createLabel("New best!");
            newBestTimeLabel.setPosition(
                    Gdx.graphics.getWidth() / 2 - newBestTimeLabel.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2 + 100
            );
            mStage.addActor(newBestTimeLabel);
        }

        TextButton retryButton = ButtonFactory.createButton("Retry");
        retryButton.setPosition(
                Gdx.graphics.getWidth() / 2 - retryButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2
        );
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new GameScreen(getGame()));
            }
        });
        mStage.addActor(retryButton);
    }

    @Override
    public void render(float deltaTime) {
        clear();
        mStage.act();
        mStage.draw();
    }
}
