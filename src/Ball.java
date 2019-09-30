
import java.awt.*;
import java.util.Random;

// TODO make bounces more complex

public class Ball implements Runnable {

    int x, y, xDirection, yDirection;

    int p1Score, p2Score;

    Paddle p1 = new Paddle(10, 25, 1);
    Paddle p2 = new Paddle(485, 25, 2);

    Rectangle ball;

    public Ball(int x, int y) {
        p1Score = p2Score = 0;
        this.x = x;
        this.y = y;

        Random r = new Random();
        int randXDir = r.nextInt(2);
        if (randXDir == 0)
            randXDir = -1;
        setXDirection(randXDir);
        int randYDir = r.nextInt(2);
        if (randYDir == 0)
            randYDir = -1;
        setYDirection(randYDir);
        ball = new Rectangle(this.x, this.y, 15, 15);
    }

    public void setXDirection(int direction) {
        xDirection = direction;
    }

    public void setYDirection(int direction) {
        yDirection = direction;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(ball.x, ball.y, ball.width, ball.height);
    }

    public void collision() {
        if (ball.intersects(p1.paddle))
            setXDirection(+1);
        if (ball.intersects(p2.paddle))
            setXDirection(-1);
    }

    public void move() {
        collision();
        ball.x += xDirection;
        ball.y += yDirection;

        if (ball.x <= 0) {
            setXDirection(+1);
            p2Score++;
            System.out.println("Player 2 scored");
            System.out.println("Current score: " + p1Score + " - " + p2Score);
        }
        if (ball.x >= 485) {
            setXDirection(-1);
            p1Score++;
            System.out.println("Player 1 scored");
            System.out.println("Current score: " + p1Score + " - " + p2Score);
        }
        if (ball.y <= 30)
            setYDirection(+1);
        if (ball.y >= 385)
            setYDirection(-1);
    }

    @Override
    public void run() {
        try {
            while (true) {
                move();
                Thread.sleep(7);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
