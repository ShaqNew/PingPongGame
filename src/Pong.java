
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Pong extends JFrame {

    int screenWidth = 500;
    int screenHeight = 400;
    Dimension screenSize = new Dimension(screenWidth, screenHeight);

    static Ball ball = new Ball(250, 200);

    public Pong() {
        this.setTitle("Pong");
        this.setSize(screenSize);
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
    }

    @Override
    public void paint(Graphics g) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbGraphics = dbImage.getGraphics();
        draw(dbGraphics);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void draw(Graphics g) {
        ball.draw(g);
        ball.p1.draw(g);
        ball.p2.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("" + ball.p1Score, 15, 20);
        g.drawString("" + ball.p2Score, 385, 20);

        repaint();
    }

    public class KL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            ball.p1.keyPressed(e);
            ball.p2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            ball.p1.keyReleased(e);
            ball.p2.keyReleased(e);
        }
    }

    public static void main(String[] args) {
        Pong pong = new Pong();

        Thread ballThread = new Thread(ball);
        ballThread.start();
        Thread p1 = new Thread(ball.p1);
        Thread p2 = new Thread(ball.p2);
        p1.start();
        p2.start();
    }

}
