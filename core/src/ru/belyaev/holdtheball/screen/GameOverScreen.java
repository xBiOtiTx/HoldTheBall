package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import ru.belyaev.holdtheball.GamePreferences;
import ru.belyaev.holdtheball.ui.ButtonFactory;
import ru.belyaev.holdtheball.ui.Styles;


public class GameOverScreen extends BaseScreen {

    private final DecimalFormat mDecimalFormat = new DecimalFormat("0.000");
    private final GamePreferences mGamePreferences;

    private final Stage mStage;

    public GameOverScreen(Game game, float time) {
        super(game);
        mGamePreferences = new GamePreferences();

        mStage = new Stage();
        Gdx.input.setInputProcessor(mStage);

        final VerticalGroup verticalGroup = new VerticalGroup();
        final float bestTime = Math.max(time, mGamePreferences.getBestTime());
        mGamePreferences.setBestTime(bestTime);

        Label label1 = ButtonFactory.createLabel(String.format(Locale.US, "Your time: %.3f", time));
        label1.setScale(5);
        Label label2 = ButtonFactory.createLabel(String.format(Locale.US, "Best time: %.3f", bestTime));
        TextButton button = ButtonFactory.createButton("RETRY");

        button.pad(Styles.toDpi(8), Styles.toDpi(16), Styles.toDpi(8), Styles.toDpi(16));
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new GameScreen(getGame()));
            }
        });
        verticalGroup.addActor(label1);
        verticalGroup.addActor(label2);
        verticalGroup.addActor(button);

        verticalGroup.space(Styles.toDpi(16));

        Container<VerticalGroup> container = new Container<VerticalGroup>(verticalGroup);
        container.setFillParent(true);
        mStage.addActor(container);
    }

    @Override
    public void render(float deltaTime) {
        clear();
        mStage.act();
        mStage.draw();
    }
}
