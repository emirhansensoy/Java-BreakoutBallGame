package mypackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MyPanel extends JPanel
{
    String message = "Game Over";
    Image bgImage;
    Paddle paddle;
    Ball ball;
    Brick[] bricks;
    Timer timer;
    boolean isOver;

    MyPanel()
    {
        initMyPanel();
        initGame();
    }

    void initMyPanel()
    {
        setPreferredSize(new Dimension(Variables.WIDTH, Variables.HEIGHT));
        setFocusable(true);
        addKeyListener(new MyKeyListener());
    }

    void initGame()
    {
        isOver = false;
        paddle = new Paddle();
        ball = new Ball();

        bricks = new Brick[Variables.NO_OF_BRICKS];

        int k = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                bricks[k] = new Brick(j * 95 + 65, i * 35 + 50);
                k++;
            }
        }

        bgImage = new ImageIcon("./src/resources/bgImage.jpg").getImage();

        timer = new Timer(Variables.DELAY, new GameCycle());
        timer.start();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(bgImage, 0, 0, null);

        if(isOver)
        {
            gameOver(g2D);
        }
        else
        {
            g2D.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),paddle.getImageWidth(), paddle.getImageHeight(), null);
            g2D.drawImage(ball.getImage(), ball.getX(), ball.getY(),ball.getImageWidth(), ball.getImageHeight(), null);

            for (int i = 0; i < Variables.NO_OF_BRICKS; i++)
            {
                if (!bricks[i].isDestroyed())
                {
                    g2D.drawImage(bricks[i].getImage(), bricks[i].getX(),bricks[i].getY(), bricks[i].getImageWidth(),bricks[i].getImageHeight(), null);
                }
            }
        }

    }

    private class MyKeyListener implements KeyListener
    {
        @Override
        public void keyReleased(KeyEvent e)
        {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT)
            {
                paddle.setDir_x(0);
            }
            if (key == KeyEvent.VK_RIGHT)
            {
                paddle.setDir_x(0);
            }
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT)
            {
                paddle.setDir_x(-5);
            }
            if (key == KeyEvent.VK_RIGHT)
            {
                paddle.setDir_x(5);
            }
        }

        @Override
        public void keyTyped(KeyEvent e)
        {

        }
    }

    private class GameCycle implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            runGame();
        }
    }

    private void runGame()
    {
        ball.move();
        paddle.move();
        checkCollision();
        repaint();
    }

    void checkCollision()
    {
        if (ball.getRect().getMaxY() > Variables.BOTTOM_EDGE)
        {
            stopGame();
        }

        for (int i = 0, j = 0; i < Variables.NO_OF_BRICKS; i++)
        {
            if (bricks[i].isDestroyed())
            {
                j++;
            }

            if (j == Variables.NO_OF_BRICKS)
            {
                message = "Congratulations!";
                stopGame();
            }
        }

        //ball-paddle collision
        if ((paddle.getRect()).intersects(ball.getRect()))
        {
            if(ball.getRect().getY() < paddle.getRect().getY())//collision with the front side
            {
                if(ball.getDir_X() < 0 && paddle.getDir_x() > 0)//paddle is moving right, ball is moving left
                {
                    ball.setVelocityX(5);
                }
                else if(ball.getDir_X() > 0 && paddle.getDir_x() < 0)//paddle is moving left, ball is moving right
                {
                    ball.setVelocityX(1);
                }
                else if(ball.getDir_X() > 0 && paddle.getDir_x() > 0)//paddle and ball are moving right
                {
                    ball.setVelocityX(5);
                }
                else if(ball.getDir_X() < 0 && paddle.getDir_x() < 0)//paddle and ball are moving left
                {
                    ball.setVelocityX(5);
                }
                ball.setDir_y(-1);
            }
            else//collision with the sides
            {
                if(ball.getDir_X() < 0 && paddle.getDir_x() > 0)//paddle is moving right, ball is moving left
                {
                    ball.setDir_x(1);
                    ball.setVelocityX(10);
                    ball.setDir_y(-1);
                }
                else if(ball.getDir_X() > 0 && paddle.getDir_x() < 0)//paddle is moving left, ball is moving right
                {
                    ball.setDir_x(-1);
                    ball.setVelocityX(10);
                    ball.setDir_y(-1);
                }
                else if(ball.getDir_X() < 0 && paddle.getDir_x() < 0)//paddle and ball are moving left
                {
                    ball.setDir_x(1);
                    ball.setVelocityX(4);
                    ball.setDir_y(-1);
                }
                else if(ball.getDir_X() > 0 && paddle.getDir_x() > 0)//paddle and ball are moving right
                {
                    ball.setDir_x(-1);
                    ball.setVelocityX(4);
                    ball.setDir_y(-1);
                }
            }
        }


        //ball-brick collision
        for (int i = 0; i < Variables.NO_OF_BRICKS; i++)
        {
            if ((ball.getRect()).intersects(bricks[i].getRect()))
            {
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                if (!bricks[i].isDestroyed())
                {
                    if(ball.getRect().intersects(bricks[i].getRect()))
                    {
                        if(ball.getX() + ballWidth + 1 <= bricks[i].getX() - 1 || ball.getX() -10 >= bricks[i].getX() + bricks[i].getImageWidth() + 1)
                        {
                            ball.setDir_x(-ball.getDir_X());
                        }
                        else
                        {
                            ball.setDir_y(-ball.getDir_Y());
                        }
                    }
                    bricks[i].setDestroyed(true);
                }
            }
        }
    }

    private void gameOver(Graphics2D g2D)
    {
        Font font = new Font("Consolas",Font.BOLD,26);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2D.setColor(Color.BLACK);
        g2D.setFont(font);
        g2D.drawString(message,(Variables.WIDTH - fontMetrics.stringWidth(message) )/ 2,Variables.WIDTH/2);
    }

    private void stopGame()
    {
        isOver = true;
        timer.stop();
    }
}
