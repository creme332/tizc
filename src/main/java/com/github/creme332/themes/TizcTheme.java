package com.github.creme332.themes;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import com.formdev.flatlaf.FlatDarkLaf;

public class TizcTheme extends FlatDarkLaf {
    public static boolean setup() {
        installFonts();
        return setup(new TizcTheme());
    }

    @Override
    public String getName() {
        return "TizcTheme";
    }

    private static void installFonts() {
        File font_file;
        try {
            // setup Poppins Black font
            font_file = new File(TizcTheme.class.getResource("/font/Poppins/Poppins-Black.ttf").getPath());
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));

            font_file = new File(TizcTheme.class.getResource("/font/Poppins/Poppins-Bold.ttf").getPath());
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));

            font_file = new File(TizcTheme.class.getResource("/font/Poppins/Poppins-Light.ttf").getPath());
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));

            font_file = new File(TizcTheme.class.getResource("/font/Poppins/Poppins-Medium.ttf").getPath());
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));

            font_file = new File(TizcTheme.class.getResource("/font/Poppins/Poppins-Regular.ttf").getPath());
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}