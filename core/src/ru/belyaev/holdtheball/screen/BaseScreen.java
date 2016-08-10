package ru.belyaev.holdtheball.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen, InputProcessor {
    private final Game mGame;

    public BaseScreen(Game game) {
        mGame = game;
    }

    // =============================================================================================
    // Screen implementation
    // =============================================================================================

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    // =============================================================================================
    // InputProcessor implementation
    // =============================================================================================

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    // =============================================================================================
    // getters
    // =============================================================================================


    public Game getGame() {
        return mGame;
    }
}
