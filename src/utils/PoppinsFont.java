package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class PoppinsFont {
    public Font Black;
    public Font Bold;
    public Font Light;
    public Font Medium;
    public Font Regular;

    public PoppinsFont() {
        try {
            File font_file;

            font_file = new File("resources/font/Poppins/Poppins-Black.ttf");
            Black = Font.createFont(Font.TRUETYPE_FONT, font_file);

            font_file = new File("resources/font/Poppins/Poppins-Bold.ttf");
            Bold = Font.createFont(Font.TRUETYPE_FONT, font_file);

            font_file = new File("resources/font/Poppins/Poppins-Light.ttf");
            Light = Font.createFont(Font.TRUETYPE_FONT, font_file);

            font_file = new File("resources/font/Poppins/Poppins-Medium.ttf");
            Medium = Font.createFont(Font.TRUETYPE_FONT, font_file);

            font_file = new File("resources/font/Poppins/Poppins-Regular.ttf");
            Regular = Font.createFont(Font.TRUETYPE_FONT, font_file);

        } catch (FontFormatException | IOException ex) {
            System.out.println(ex);
        }
    }
}
