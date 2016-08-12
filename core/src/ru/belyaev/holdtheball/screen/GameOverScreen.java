package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ru.belyaev.holdtheball.ui.ButtonFactory;

public class GameOverScreen extends BaseScreen {

    private final Stage mStage;

    public GameOverScreen(Game game) {
        super(game);
        mStage = new Stage();
        Gdx.input.setInputProcessor(mStage);

        Label gameOverLabel = ButtonFactory.createLabel("Game over");
        gameOverLabel.setPosition(
                Gdx.graphics.getWidth() / 2 - gameOverLabel.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 + 100
        );
        mStage.addActor(gameOverLabel);

        TextButton retryButton = ButtonFactory.createButton("button");
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
