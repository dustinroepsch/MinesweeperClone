package com.dustinroepsch.minesweeper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dusti_000 on 1/22/2015.
 */
public class GameState {
    private boolean lost;
    private Component c;
    private Runnable runnable;
    private Point currentWhite;
    public GameState(Component c, Runnable runnable){
        lost = false;

        this.c  = c;

        currentWhite = null;
        this.runnable = runnable;
    }

    public void lose(){
        JOptionPane.showMessageDialog(c, "You have lost");

        System.exit(0);
    }


    public void whiteSpace(int i, int j) {
        currentWhite = new Point(i, j);
        runnable.run();
    }

    public Point getPoint() {
        return currentWhite;
    }

    public void checkWin() {
        if (runnable instanceof Main){
            Main m = (Main) runnable;

            m.checkWin();

        }
    }
}
