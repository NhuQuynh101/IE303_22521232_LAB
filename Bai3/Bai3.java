package Bai3;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

public class Bai3 {
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

    public static class Pipe {
        private final Image topPipeImage;
        private final Image bottomPipeImage;
        private int x;
        private final int gapY;
        private final int gapHeight;
        private final int pipeWidth = 52;
        private final int topPipeHeight;
        private final int bottomPipeHeight;

        public Pipe(int x, int gapY, int gapHeight) {
            this.x = x;
            this.gapY = gapY;
            this.gapHeight = gapHeight;
            this.topPipeImage = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
            this.bottomPipeImage = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();
            
            // Tính toán chiều cao của ống dựa trên gap
            this.topPipeHeight = gapY;
            this.bottomPipeHeight = 640 - (gapY + gapHeight);
        }

        public void move() {
            x -= 2; // Tốc độ di chuyển của ống
        }

        public void draw(Graphics g) {
            // Vẽ ống trên
            g.drawImage(topPipeImage, x, 0, pipeWidth, topPipeHeight, null);
            // Vẽ ống dưới
            g.drawImage(bottomPipeImage, x, gapY + gapHeight, pipeWidth, bottomPipeHeight, null);
        }

        public boolean isOffscreen() {
            return x < -pipeWidth;
        }

        public int getX() {
            return x;
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
        private final ArrayList<Pipe> pipes = new ArrayList<>();
        private final Random random = new Random();
        private int pipeSpawnTimer = 0;
        private static final int PIPE_SPAWN_INTERVAL = 1500; // 1.5 giây
        private static final int MIN_GAP_HEIGHT = 100;
        private static final int MAX_GAP_HEIGHT = 200;
        private static final int MIN_GAP_Y = 100;
        private static final int MAX_GAP_Y = 400;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
            birdImage = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
            
            setFocusable(true);
            addKeyListener(this);
            
            initializeTimer();
        }

        private void initializeTimer() {
            gameTimer = new Timer(20, e -> {
                updateGame();
                repaint();
            });
            gameTimer.start();
        }

        private void updateGame() {
            updateBird();
            updatePipes();
            spawnPipes();
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

        private void updatePipes() {
            Iterator<Pipe> iterator = pipes.iterator();
            while (iterator.hasNext()) {
                Pipe pipe = iterator.next();
                pipe.move();
                if (pipe.isOffscreen()) {
                    iterator.remove();
                }
            }
        }

        private void spawnPipes() {
            pipeSpawnTimer += 20; // 20ms là interval của Timer
            if (pipeSpawnTimer >= PIPE_SPAWN_INTERVAL) {
                int gapHeight = MIN_GAP_HEIGHT + random.nextInt(MAX_GAP_HEIGHT - MIN_GAP_HEIGHT);
                int gapY = MIN_GAP_Y + random.nextInt(MAX_GAP_Y - MIN_GAP_Y);
                pipes.add(new Pipe(getWidth(), gapY, gapHeight));
                pipeSpawnTimer = 0;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            
            // Vẽ các ống
            for (Pipe pipe : pipes) {
                pipe.draw(g);
            }
            
            // Vẽ chim
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
