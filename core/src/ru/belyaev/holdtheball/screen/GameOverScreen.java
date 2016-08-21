package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import ru.belyaev.holdtheball.util.Assets;
import ru.belyaev.holdtheball.util.Dimens;
import ru.belyaev.holdtheball.GamePreferences;
import ru.belyaev.holdtheball.HoldTheBall;
import ru.belyaev.holdtheball.util.Strings;

public class GameOverScreen extends BaseScreen {

    private final GamePreferences mGamePreferences;
    private final Stage mGameOverUI;

    public GameOverScreen(HoldTheBall game, float time) {
        super(game);
        mGamePreferences = new GamePreferences();

        mGameOverUI = new Stage();
        Gdx.input.setInputProcessor(mGameOverUI);

        final VerticalGroup verticalGroup = new VerticalGroup();
        final float bestTime = Math.max(time, mGamePreferences.getBestTime());
        mGamePreferences.setBestTime(bestTime);

        final Label yourTimeLabel = new Label(Strings.getYourTimeString(time), Assets.sSkin, "black-label-style");
        final Label bestTimeLabel = new Label(Strings.getBestTimeString(bestTime), Assets.sSkin, "black-label-style");
        final TextButton retryButton = new TextButton(Strings.RETRY_STRING, Assets.sSkin, "button-style");
        retryButton.pad(Dimens.toDpi(8), Dimens.toDpi(16), Dimens.toDpi(8), Dimens.toDpi(16));
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new GameScreen(getGame()));
            }
        });
        verticalGroup.addActor(yourTimeLabel);
        verticalGroup.addActor(bestTimeLabel);
        verticalGroup.addActor(retryButton);

        verticalGroup.space(Dimens.toDpi(16));

        final Container<VerticalGroup> container = new Container<VerticalGroup>(verticalGroup);
        container.setFillParent(true);
        mGameOverUI.addActor(container);

//        if (time < 1) {
//            final Label tipLabel = new Label(Strings.TIP_STRING, Assets.sSkin, "black-label-style");
//            tipLabel.setAlignment(Align.center);
//            tipLabel.setPosition(
//                    mGameOverUI.getWidth() / 2 - tipLabel.getWidth() / 2,
//                    Dimens.toDpi(16)
//            );
//            mGameOverUI.addActor(tipLabel);
//        }
    }

    @Override
    public void render(float deltaTime) {
        clear();
        mGameOverUI.act();
        mGameOverUI.draw();
    }
}
