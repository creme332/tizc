import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomeScreen extends JPanel {
    public JButton startGameButton = new JButton("Play");

    HomeScreen() {
        this.setLayout(new GridBagLayout());

        Font myFont = new PoppinsFont().Black;

        startGameButton.setPreferredSize(new Dimension(200, 100));
        startGameButton.setFont(myFont.deriveFont(30f));
        startGameButton.setFocusPainted(false);
        // startGameButton.setBorderPainted(false);
        startGameButton.setOpaque(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setBorderPainted(false);

        this.add(startGameButton);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        ImageIcon img = new ImageIcon("resources/bg1.jpg");

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
