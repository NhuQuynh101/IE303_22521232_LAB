package Bai2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Bai2 {
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

    public static class BackgroundPanel extends JPanel implements KeyListener {
        private final Image backgroundImage;
        private final Image birdImage;
        private int birdY = 300; // Vị trí ban đầu của chim
        private int yVelocity = 0;
        private final int GRAVITY = 1;
        private final int JUMP_FORCE = -15;
        private Timer gameTimer;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
            birdImage = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
            
            setFocusable(true);
            addKeyListener(this);
            
            initializeTimer();
        }

        private void initializeTimer() {
            gameTimer = new Timer(20, e -> {
                updateBird();
                repaint();
            });
            gameTimer.start();
        }

        private void updateBird() {
            yVelocity += GRAVITY;
            birdY += yVelocity;
            
            // Giới hạn chim không bay ra khỏi màn hình
            if (birdY > getHeight() - 40) {
                birdY = getHeight() - 40;
                yVelocity = 0;
            }
            if (birdY < 0) {
                birdY = 0;
                yVelocity = 0;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g.drawImage(birdImage, 50, birdY, 40, 40, this);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                yVelocity = JUMP_FORCE;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}
