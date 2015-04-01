package com.dustinroepsch.minesweeper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dusti_000 on 1/21/2015.
 */
public class MineBar extends JPanel{
    private JLabel remainingMines;
    private static Dimension prSize = new Dimension(50,50);
    public MineBar(int numMines) {
        super();
        remainingMines = new JLabel(String.valueOf(numMines));


        setLayout(new GridLayout(1, 0));

        remainingMines.setPreferredSize(prSize);

        add(remainingMines);


    }
}
