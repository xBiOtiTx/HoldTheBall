package ru.belyaev.holdtheball.util;

import com.badlogic.gdx.Gdx;

public class Dimens {
    public static final float DPI = Gdx.graphics.getDensity();

    public static final float TEXT_SIZE_DEFAULT_DP = 28;
    public static final int TEXT_SIZE_DEFAULT_PX = (int) (TEXT_SIZE_DEFAULT_DP * DPI);

    public static final float BALL_RADIUS_DP = 56;
    public static final int BALL_RADIUS_PX = toDpi(BALL_RADIUS_DP);

    public static final float BALL_BORDER_WIDTH_DP = 8;
    public static final int BALL_BORDER_WIDTH_PX = toDpi(BALL_BORDER_WIDTH_DP);

    public static final float HIT_ERROR_DP = 56;
    public static final float HIT_ERROR_PX = toDpi(HIT_ERROR_DP);

    public static int toDpi(float unit) {
        return (int) (unit * DPI);
    }
}
