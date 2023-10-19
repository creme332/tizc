import java.awt.Color;
import java.awt.Graphics;
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
    JLabel accuracyText = new JLabel();

    float mediumFontSize = 50;
    int iconSize = 50;

    GameOverScreen() {
        PoppinsFont myFont = new PoppinsFont();

        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        ImageIcon celebrationIcon = new ImageIcon(
                new ImageIcon("resources/icon/celebration.png").getImage().getScaledInstance(100, 100,
                        Image.SCALE_DEFAULT));
        ImageIcon timerIcon = new ImageIcon(
                new ImageIcon("resources/icon/deadline.png").getImage().getScaledInstance(iconSize, iconSize,
                        Image.SCALE_DEFAULT));
        ImageIcon speedometerIcon = new ImageIcon(
                new ImageIcon("resources/icon/speedometer.png").getImage().getScaledInstance(iconSize, iconSize,
                        Image.SCALE_DEFAULT));
        ImageIcon restartIcon = new ImageIcon(
                new ImageIcon("resources/icon/restart.png").getImage().getScaledInstance(iconSize, iconSize,
                        Image.SCALE_DEFAULT));
        ImageIcon accuracyIcon = new ImageIcon(
                new ImageIcon("resources/icon/accuracy.png").getImage().getScaledInstance(iconSize, iconSize,
                        Image.SCALE_DEFAULT));

        // styles for gameOverText
        gameOverText.setIcon(celebrationIcon);
        gameOverText.setFont(myFont.Bold.deriveFont(70f));
        gameOverText.setHorizontalAlignment(JLabel.CENTER);
        gameOverText.setForeground(Color.WHITE);

        // styles for time taken
        setTimeTaken(0);
        timeTakenText.setFont(myFont.Regular.deriveFont(mediumFontSize));
        timeTakenText.setIcon(timerIcon);
        timeTakenText.setHorizontalAlignment(JLabel.CENTER);
        timeTakenText.setForeground(Color.WHITE);

        // styles for speed
        setWPM(0);
        wpmText.setFont(myFont.Regular.deriveFont(mediumFontSize));
        wpmText.setIcon(speedometerIcon);
        wpmText.setHorizontalAlignment(JLabel.CENTER);
        wpmText.setForeground(Color.WHITE);

        // styles for accuracy
        setAccuracy(0);
        accuracyText.setFont(myFont.Regular.deriveFont(mediumFontSize));
        accuracyText.setIcon(accuracyIcon);
        accuracyText.setHorizontalAlignment(JLabel.CENTER);
        accuracyText.setForeground(Color.WHITE);

        // styles for restartButton
        restarButton.setFocusPainted(false);
        restarButton.setIcon(restartIcon);
        restarButton.setFont(myFont.Regular.deriveFont(mediumFontSize));
        restarButton.setContentAreaFilled(false);
        restarButton.setForeground(Color.WHITE);
        // POSITIONING
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // position gameOverText
        gbc.gridx = 0; // column
        gbc.gridy = 0; // row
        gbc.gridwidth = 3;
        gbc.weighty = 0.3;

        this.add(gameOverText, gbc);

        gbc.weighty = 1;

        // position timeTakenText
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(timeTakenText, gbc);

        // position wpmText
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(wpmText, gbc);

        // position accuracyText
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(accuracyText, gbc);

        // position restartButton
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weighty = 0.3;

        this.add(restarButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        ImageIcon img = new ImageIcon("resources/bg.jpg");

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public void setTimeTaken(long time) {
        timeTakenText.setText(String.format("%d s", time));
    }

    public void setWPM(long wpm) {
        wpmText.setText(String.format("%d wpm", wpm));
    }

    public void setAccuracy(long acc) {
        accuracyText.setText(String.format("%d %%", acc));
    }

}
