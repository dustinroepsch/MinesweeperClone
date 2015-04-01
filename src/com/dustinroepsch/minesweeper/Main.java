package com.dustinroepsch.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dusti_000 on 1/21/2015.
 */
public class Main extends JFrame implements Runnable{
    private MineBar mineBar;
    private MineField mineField;
    private String difficulty;
    private int numMines;
    private Dimension gameSize;

    private GameState state;



    public Main(){
        super("Minesweeper clone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        setLayout(new BorderLayout());

        Object[] options = {"Beginner", "Intermediate", "Expert", "Custom"};

        Object selectedValue = null;

        while(selectedValue == null){
            selectedValue = JOptionPane.showInputDialog(null, "Select Difficulty","Level",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

        }
        if (selectedValue instanceof String){
            difficulty = (String) selectedValue;

        }else{
            throw new AssertionError("Difficulty was not a string for some reason");
        }

        switch (difficulty){
            case "Beginner":
                numMines = 10;
                gameSize = new Dimension(8,8);
                break;
            case "Intermediate":
                numMines = 40;
                gameSize = new Dimension(16,16);
                break;
            case "Expert":
                numMines = 99;
                gameSize = new Dimension(30,16);
                break;
            case "Custom":
                JTextField rows = new JTextField(3);
                JTextField cols = new JTextField(3);
                JTextField mines = new JTextField(3);
                JPanel panel = new JPanel();
                panel.add(new JLabel("Rows: "));
                panel.add(rows);
                panel.add(new JLabel("Cols: "));
                panel.add(cols);
                panel.add(new JLabel("Mines: "));
                panel.add(mines);

                int result;
                do{
                    result = JOptionPane.showConfirmDialog(null,panel,"Custom Puzzle",JOptionPane.OK_CANCEL_OPTION);
                }while (!(result == JOptionPane.OK_OPTION));

                numMines = Integer.valueOf(mines.getText());
                gameSize = new Dimension(Integer.valueOf(cols.getText()),Integer.valueOf(rows.getText()));
                break;

            default:
                throw new AssertionError("Switch statemeant has wrong strings");
        }

        mineBar = new MineBar(numMines);
        state = new GameState(getComponent(0), this);


        mineField = new MineField(gameSize,numMines,state);
        add(mineBar,BorderLayout.NORTH);
        add(mineField,BorderLayout.CENTER);
        pack();


    }
    public static void main(String[] args){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    Main main = new Main();
                    main.setVisible(true);
                    main.requestFocus();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Point white = state.getPoint();

        mineField.whiteStuff(white);

    }

    public void checkWin() {
        mineField.checkWin();
    }
}
