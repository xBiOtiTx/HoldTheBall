package ru.belyaev.holdtheball.effect;

import ru.belyaev.holdtheball.Ball;

public class ScaleEffect implements Effect {
    private static final float BASE_SCALE = 1.0f;
    private static final float DELTA_SCALE = 0.25f;
    private static final float MIN_SCALE = BASE_SCALE - DELTA_SCALE;
    private static final float MAX_SCALE = BASE_SCALE + DELTA_SCALE;

    private float mTime = 0.0f;

    @Override
    public void apply(Ball ball, float deltaTime) {
        mTime += deltaTime;
        ball.setScale((float) (BASE_SCALE + Math.sin(mTime * 2 * Math.PI) * DELTA_SCALE));
    }
}
