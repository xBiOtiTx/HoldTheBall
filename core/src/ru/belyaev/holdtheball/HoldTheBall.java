package ru.belyaev.holdtheball;

import com.badlogic.gdx.Game;

import ru.belyaev.holdtheball.screen.GameScreen;

public class HoldTheBall extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
