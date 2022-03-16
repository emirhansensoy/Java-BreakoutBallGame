package mypackage;

import javax.swing.*;

public class Brick extends Sprite
{
    private boolean destroyed;

    Brick(int x, int y)
    {
        initBrick(x, y);
    }

    void initBrick(int x, int y)
    {
        this.x = x;
        this.y = y;

        destroyed = false;

        loadImage();
        getImageDimensions();
    }

    private void loadImage()
    {
        image = new ImageIcon("./src/resources/brick.jpg").getImage();
    }

    public void setDestroyed(boolean destroyed)
    {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }
}
