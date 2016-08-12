package ru.belyaev.holdtheball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GamePreferences {
    private static final String GAME_PREFERENCES = "GAME_PREFERENCES";
    private static final String BEST_TIME = "BEST_TIME";

    private final Preferences mPreferences = Gdx.app.getPreferences(GAME_PREFERENCES);

    public float getBestTime() {
        return mPreferences.getFloat(BEST_TIME, 0.0f);
    }

    public void setBestTime(float time) {
        mPreferences.putFloat(BEST_TIME, time);
        mPreferences.flush();
    }
}
