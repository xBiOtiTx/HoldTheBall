package ru.belyaev.holdtheball.util;

import java.util.Locale;

public class Strings {
    public static final String TIME_FORMAT = "%.3f";

    public static final String TIME_STRING = "Time: " + TIME_FORMAT;
    public static final String YOUR_TIME_STRING = "Your time: " + TIME_FORMAT;
    public static final String BEST_TIME_STRING = "Best time: " + TIME_FORMAT;
    public static final String HOLD_THE_BALL_STRING = "Hold the ball";
    public static final String RETRY_STRING = "RETRY";
    public static final String TIP_1_STRING = "Press and hold your finger inside the ball";
    public static final String TIP_2_STRING = "Do not let the ball";

    public static String getTimeString(float time) {
        return String.format(Locale.US, TIME_STRING, time);
    }

    public static String getYourTimeString(float time) {
        return String.format(Locale.US, YOUR_TIME_STRING, time);
    }

    public static String getBestTimeString(float time) {
        return String.format(Locale.US, BEST_TIME_STRING, time);
    }
}
