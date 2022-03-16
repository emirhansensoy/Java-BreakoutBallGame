package mypackage;

import javax.swing.*;
import javax.swing.plaf.multi.MultiPanelUI;

public class MyFrame extends JFrame
{
    MyFrame()
    {
        add(new MyPanel());
        setTitle("Breakout Ball");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
