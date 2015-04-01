package com.dustinroepsch.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by dusti_000 on 1/21/2015.
 */
public class Mine extends JLabel implements MouseListener{
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlag;
    private int number;
    private int i, j;
    private GameState state;
    private ImageIcon icon;

    public Mine(GameState state, int i, int j ){
        super(" ");
        this.i = i;
        this.j = j;
        this.state = state;


        isFlag = false;

        isMine = false;
        isRevealed = false;
        number = 0;
        setPreferredSize(new Dimension(40,40));

        this.setFont(new Font("Blah",Font.PLAIN,10));


        addMouseListener(this);

        icon = new ImageIcon("images/Empy.png");

        setIcon(icon);


    }


    public boolean isMine() {
        return isMine;
    }

    public void setMine() {
        isMine = true;
    }

    public void count() {
        number++;
    }

    public void white() {
        if (!isRevealed) {
            isRevealed = true;
            setIcon(new ImageIcon("images/clicked.png"));
            if (number != 0) {
                this.setNumber(String.valueOf(number));
            } else {
                state.whiteSpace(i, j);
            }
        }
    }

    private void setNumber(String s) {
        setIcon(new ImageIcon("images/" + s + ".png"));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Mine me = null;
        if (e.getSource() instanceof Mine){
            me = (Mine) e.getSource();
        }

        if (me != null && e.getButton() == MouseEvent.BUTTON1 && !me.isFlag()){
            if (isMine){
                me.state.lose();
            }
            else{
                me.isRevealed = true;
                if (me.number != 0) {
                    me.setIcon(new ImageIcon("images/" + me.number + ".png" ));
                }else{
                    me.setIcon(new ImageIcon("images/clicked.png"));
                    state.whiteSpace(i, j);
                }
               // me.setEnabled(false);

            }
        }
        if (me != null && e.getButton() == MouseEvent.BUTTON3 && !me.isRevealed){
            if (me.isFlag()){
                me.setFlag(false);
            }
            else{
                me.setFlag(true);
            }
        }
        checkWin();
    }

    private void checkWin() {
        state.checkWin();
    }

    private void setFlag(boolean b) {
        if (b){
            setIcon(new ImageIcon("images/flag.png"));
        }else{
            setIcon(new ImageIcon("images/Empy.png"));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isFlag() {
        return isFlag;
    }

    public boolean isRevealed() {
        return isRevealed;
    }
}
