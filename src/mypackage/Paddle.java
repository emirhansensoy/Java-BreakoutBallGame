package mypackage;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Paddle extends Sprite
{
    private int dir_x;

    Paddle()
    {
        initPaddle();
    }

    void initPaddle()
    {
        loadImage();
        getImageDimensions();
        startPosition();
    }

    private void loadImage()
    {
        image = new ImageIcon("./src/resources/paddle.png").getImage();
    }

    void move()
    {
        x += dir_x;

        if (x <= 0)
        {
            x = 0;
        }

        if (x >= Variables.WIDTH - imageWidth)
        {
            x = Variables.WIDTH - imageWidth;
        }
    }

    private void startPosition()
    {
        x = Variables.PADDLE_START_X;
        y = Variables.PADDLE_START_Y;
    }

    void setDir_x(int dir_x)
    {
        this.dir_x = dir_x;
    }

    int getDir_x()
    {
        return dir_x;
    }
}
