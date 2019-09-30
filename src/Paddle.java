
import java.awt.*;
import java.awt.event.*;

// TODO add acceleration system

public class Paddle implements Runnable {

    int x, y, yDirection, id, yVelocity;

    Rectangle paddle;

    public Paddle(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        paddle = new Rectangle(x, y, 10, 50);
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
        default:
            System.out.println("Enter your Paddle ID in the constructor");
            break;
        case 1:
            if (e.getKeyCode() == KeyEvent.VK_W) {
                setYDirection(-1);
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                setYDirection(1);
            }
            break;
        case 2:
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                setYDirection(-1);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                setYDirection(1);
            }
            break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
        default:
            System.out.println("Enter your Paddle ID in the constructor");
            break;
        case 1:
            if (e.getKeyCode() == e.VK_W) {
                setYDirection(0);
            }
            if (e.getKeyCode() == e.VK_S) {
                setYDirection(0);
            }
            break;
        case 2:
            if (e.getKeyCode() == e.VK_UP) {
                setYDirection(0);
            }
            if (e.getKeyCode() == e.VK_DOWN) {
                setYDirection(0);
            }
            break;
        }
    }

    public void setYDirection(int direction) {
        yDirection = direction;
    }

    public void move() {
        // +1 == up, 0 == still, -1 == down
        paddle.y += yDirection;
        if (paddle.y <= 15)
            paddle.y = 15;
        if (paddle.y >= 350)
            paddle.y = 350;
    }

    public void draw(Graphics g) {
        switch (id) {
        default:
            System.out.println("Enter your Paddle ID in the constructor");
            break;
        case 1:
            g.setColor(Color.BLUE);
            g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
            break;
        case 2:
            g.setColor(Color.RED);
            g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
            break;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                move();
                Thread.sleep(6);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
