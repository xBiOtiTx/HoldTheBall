package ru.belyaev.holdtheball.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Styles {
    public static final float DPI = Gdx.graphics.getDensity();
    public static final int TEXT_SIZE_NORMAL = (int) (28 * DPI);

    public static final int TEXT_SIZE_BUTTON = (int) (14 * DPI);
    public static final int TEXT_SIZE_SMALL = (int) (14 * DPI);
    public static final int TEXT_SIZE_MEDIUM = (int) (24 * DPI);
    public static final int TEXT_SIZE_LARGE = (int) (36 * DPI);

    public static final TextButton.TextButtonStyle TEXT_BUTTON_STYLE = null;
    public static final Label.LabelStyle LABEL_STYLE = null;

    public static int dp(float unit) {
        return (int) (unit * DPI);
    }

    public static final String ROBOTO_REGULAR = "fonts/Roboto-Regular.ttf";
    public static final String ROBOTO_MEDIUM = "fonts/Roboto-Medium.ttf";
    public static final String[] FONTS = {
            ROBOTO_REGULAR,
            ROBOTO_MEDIUM
    };

    public static BitmapFont getFont(final float sp) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(ROBOTO_REGULAR));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Styles.TEXT_SIZE_NORMAL; // TODO тут ошибка
        BitmapFont font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        return font;
    }
}
