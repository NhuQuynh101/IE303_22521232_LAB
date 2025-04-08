package Bai1;

import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;

public class Bai1 {
    public static void main(String[] args) {
        FlappyBirdWindow window = new FlappyBirdWindow();
    }

    public static class FlappyBirdWindow extends JFrame {
        public FlappyBirdWindow() {
            setTitle("Flappy Bird");
            setSize(360, 640);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            setContentPane(new BackgroundPanel());

            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    public static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
