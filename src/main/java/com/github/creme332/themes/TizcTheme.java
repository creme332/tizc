package com.github.creme332.themes;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

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
        InputStream inputStream;
        String[] fontStyle = { "Black", "Bold", "Light", "Medium", "Regular" };

        for (String style : fontStyle) {
            try {
                String path = String.format("/font/Poppins/Poppins-%s.ttf", style);
                inputStream = TizcTheme.class.getResourceAsStream(path);
                GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .registerFont(Font.createFont(Font.TRUETYPE_FONT, inputStream));

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}