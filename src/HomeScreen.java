import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomeScreen extends JPanel {
    public JButton startGameButton = new JButton("Play");

    HomeScreen(Font myFont) {
        startGameButton.setPreferredSize(new Dimension(200, 100));
        startGameButton.setFont(myFont.deriveFont(30f));
        startGameButton.setFocusPainted(false);
        // startGameButton.setBorderPainted(false);
        startGameButton.setOpaque(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setForeground(Color.BLACK);
        startGameButton.setBackground(Color.BLACK);

        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon("resources/icon/player-play-filled.png").getImage().getScaledInstance(20, 20,
                        Image.SCALE_DEFAULT));

        startGameButton.setIcon(imageIcon);

        this.add(startGameButton);
    }
}
