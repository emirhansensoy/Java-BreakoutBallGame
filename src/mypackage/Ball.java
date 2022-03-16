package mypackage;

import javax.swing.*;

public class Ball extends Sprite
{
    private int dir_x;
    private int dir_y;
    private int velocity_x;
    private int velocity_y;

    Ball()
    {
        initBall();
    }

    void initBall()
    {
        dir_x = 1;
        dir_y = -1;
        velocity_x = 1;
        velocity_y = 7;

        loadImage();
        getImageDimensions();
        startPosition();
    }

    private void loadImage()
    {
        image = new ImageIcon("./src/resources/ball.png").getImage();
    }

    void move()
    {
        x += (dir_x * velocity_x);
        y += (dir_y * velocity_y);

        if (x <= 0)
        {
            setDir_x(1);
        }

        if (x >= Variables.WIDTH - imageWidth)
        {
            setDir_x(-1);
        }

        if(y <= 0)
        {
            setDir_y(1);
        }
    }

    private void startPosition()
    {
        x = Variables.BALL_START_X;
        y = Variables.BALL_START_Y;
    }

    void setDir_x(int dir_x)
    {
        this.dir_x = dir_x;
    }

    void setDir_y(int dir_y)
    {
        this.dir_y = dir_y;
    }

    int getDir_Y()
    {
        return dir_y;
    }

    int getDir_X()
    {
        return dir_x;
    }

    void setVelocityX(int velocity_x)
    {
        this.velocity_x = velocity_x;
    }

    void setVelocityY(int velocity_y)
    {
        this.velocity_y = velocity_y;
    }

    int getVelocityX()
    {
        return velocity_x;
    }

    int getVelocityY()
    {
        return velocity_y;
    }
}
