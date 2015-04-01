package com.dustinroepsch.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by dusti_000 on 1/21/2015.
 */
public class MineField extends JPanel{
    private Mine[][] mines;
    private Random random;
    private Dimension gameSize;
    public MineField(Dimension gameSize, int numMines, GameState state) {
        super();
        this.gameSize = gameSize;
        mines = new Mine[gameSize.height][gameSize.width];

        random = new Random();

        setLayout(new GridLayout(gameSize.height,gameSize.width));

        for (int i = 0; i < gameSize.height; i++){
            for(int j = 0; j < gameSize.width; j++){
                mines[i][j] = new Mine(state,i,j);

                add(mines[i][j]);
            }
        }

        while (numMines > 0){
            int ri = random.nextInt(gameSize.height);
            int rj = random.nextInt(gameSize.width);

            if (!mines[ri][rj].isMine()){
                mines[ri][rj].setMine();
                numMines--;

                if (ri + 1 < gameSize.height){
                    mines[ri + 1][rj].count();
                }
                if (ri - 1 >= 0){
                    mines[ri - 1][rj].count();
                }
                if (rj + 1 < gameSize.width){
                    mines[ri][rj+1].count();
                }
                if (rj - 1 >= 0){
                    mines[ri][rj-1].count();
                }


                if (ri + 1 < gameSize.height && rj + 1 < gameSize.width){
                    mines[ri + 1][rj + 1].count();
                }
                if (ri + 1 < gameSize.height && rj - 1 >= 0){
                    mines[ri + 1][rj - 1].count();
                }
                if (ri - 1 >= 0 && rj + 1 < gameSize.width ) {
                    mines[ri - 1][rj + 1].count();
                }
                if (ri - 1 >= 0 && rj - 1 >= 0) {
                    mines[ri - 1][rj - 1].count();
                }


            }

        }


    }

    public  void whiteStuff(Point white) {
        if (white.x + 1 < gameSize.height){
            mines[white.x  + 1][white.y].white();
        }
        if (white.x  - 1 >= 0){
            mines[white.x  - 1][white.y].white();
        }
        if (white.y + 1 < gameSize.width){
            mines[white.x ][white.y+ 1].white();
        }
        if (white.y - 1 >= 0){
            mines[white.x ][white.y- 1].white();
        }


        if (white.x  + 1 < gameSize.height && white.y + 1 < gameSize.width){
            mines[white.x  + 1][white.y + 1].white();
        }
        if (white.x  + 1 < gameSize.height && white.y - 1 >= 0){
            mines[white.x  + 1][white.y - 1].white();
        }
        if (white.x  - 1 >= 0 && white.y + 1 < gameSize.width ) {
            mines[white.x  - 1][white.y + 1].white();
        }
        if (white.x  - 1 >= 0 && white.y - 1 >= 0) {
            mines[white.x - 1][white.y - 1].white();
        }

    }

    public void checkWin() {
        boolean won = true;
        for (int i = 0; i < mines.length; i++){
            for (int j = 0; j < mines[i].length; j++){
                if (!mines[i][j].isRevealed() && !mines[i][j].isMine()){
                    won = false;
                }
            }
        }
        if (won){
            JOptionPane.showMessageDialog(getComponent(0),"YOU HAVE WON!");
            System.exit(0);
        }
    }
}
