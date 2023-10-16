import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverScreen extends JPanel {
    JButton restarButton = new JButton("Restart");
    JLabel gameOverText = new JLabel("Game Over");
    JLabel timeTakenText = new JLabel();
    JLabel wpmText = new JLabel();
    JLabel cpmText = new JLabel();
    float mediumFontSize = 20;

    GameOverScreen(Font myFont) {
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        ImageIcon celebrationIcon = new ImageIcon(
                new ImageIcon("resources/icon/celebration.png").getImage().getScaledInstance(100, 100,
                        Image.SCALE_DEFAULT));
        ImageIcon timerIcon = new ImageIcon(
                new ImageIcon("resources/icon/deadline.png").getImage().getScaledInstance(40, 40,
                        Image.SCALE_DEFAULT));
        ImageIcon speedometerIcon = new ImageIcon(
                new ImageIcon("resources/icon/speedometer.png").getImage().getScaledInstance(40, 40,
                        Image.SCALE_DEFAULT));
        ImageIcon restartIcon = new ImageIcon(
                new ImageIcon("resources/icon/restart.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_DEFAULT));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gameOverText.setIcon(celebrationIcon);
        gameOverText.setFont(myFont.deriveFont(50f));
        this.add(gameOverText, gbc);

        // styles for time taken
        gbc.gridx = 0;
        gbc.gridy = 1;
        setTimeTaken(0);
        timeTakenText.setFont(myFont.deriveFont(mediumFontSize));

        timeTakenText.setIcon(timerIcon);
        this.add(timeTakenText, gbc);

        // styles for speed
        gbc.gridx = 1;
        gbc.gridy = 1;
        setWPM(0);
        wpmText.setFont(myFont.deriveFont(mediumFontSize));

        wpmText.setIcon(speedometerIcon);
        this.add(wpmText, gbc);

        // styles for restartButton
        restarButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        restarButton.setIcon(restartIcon);
        this.add(restarButton, gbc);

    }

    public void setTimeTaken(long time) {
        timeTakenText.setText(String.format("%d s", time));
    }

    public void setWPM(long wpm) {
        wpmText.setText(String.format("%d wpm", wpm));
    }

}
