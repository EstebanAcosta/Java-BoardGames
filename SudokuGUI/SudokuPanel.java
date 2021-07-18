package SudokuGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import com.sun.glass.events.KeyEvent;

public class SudokuPanel extends JPanel
{

    JButton[][] sudoku;

    JButton submit;

    public SudokuPanel(JMenuItem exiting)
    {
        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();

        upperPanel.setLayout(new BorderLayout());

        JPanel lowerPanel = new JPanel();

        submit = new JButton("Submit");

        lowerPanel.add(submit);

        JMenu newGame = new JMenu("New Game");

        JMenuItem startNewGame = new JMenuItem("New Game");

        startNewGame.addActionListener(new startingNewGame());

        startNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_DOWN_MASK));

        newGame.add(startNewGame);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu restartGame = new JMenu("Restart Game");

        JMenuItem restartingGame = new JMenuItem("Restart Game");

        restartingGame.addActionListener(new restartingTheGame());

        restartingGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));

        restartGame.add(restartingGame);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu difficulty = new JMenu("Adjust Difficulty");

        JMenuItem hard = new JMenuItem("Hard");

        hard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));

        JMenuItem medium = new JMenuItem("Medium");

        medium.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));

        JMenuItem easy = new JMenuItem("Easy");

        easy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));

        difficulty.add(hard);

        difficulty.add(medium);

        difficulty.add(easy);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu exit = new JMenu("Exit");

        exit.add(exiting);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu time = new JMenu("Time");

        JMenuItem addTime = new JMenuItem("Add Time");

        addTime.addActionListener(new addTime());

        addTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_DOWN_MASK));

        JMenuItem resetTime = new JMenuItem("Reset Time");

        resetTime.addActionListener(new resetTime());

        resetTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.SHIFT_DOWN_MASK));

        time.add(addTime);

        time.add(resetTime);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel menuPanel = new JPanel();

        JMenuBar menu = new JMenuBar();

        menu.add(newGame);

        menu.add(restartGame);

        menu.add(difficulty);

        menu.add(time);

        menu.add(exit);

        menuPanel.add(menu);

        upperPanel.add(menuPanel, BorderLayout.NORTH);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JLabel timer = new JLabel("Timer:");

        JPanel timerPanel = new JPanel();

        timer.setHorizontalAlignment(JLabel.CENTER);

        timerPanel.add(timer);

        upperPanel.add(timerPanel, BorderLayout.CENTER);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        sudoku = new JButton[9][9];

        JPanel game = new JPanel();

        game.setLayout(new GridLayout(3, 3));

        // loop through each row in the button grid
        for (int row = 0; row < 3; row++)
        {
            // loop through each column in the button grid
            for (int col = 0; col < 3; col++)
            {

                JPanel box = new JPanel(new GridLayout(3, 3));

                box.setBorder(LineBorder.createGrayLineBorder());

                for (int i = 0; i < 9; i++)
                {
                    // create a new button
                    JButton tile = new JButton();

                    tile.addActionListener(new setNumber());

                    box.add(tile);

                    // sudoku[][] = tile;
                }

                game.add(box);

            }

        }

        add(upperPanel, BorderLayout.NORTH);

        add(game, BorderLayout.CENTER);

        add(lowerPanel, BorderLayout.SOUTH);
    }

    private class addTime implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

        }

    }

    private class resetTime implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

        }

    }

    private class startingNewGame implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

        }

    }

    private class restartingTheGame implements ActionListener
    {

        int originalX = 400;

        int originalY = 400;

        public void actionPerformed(ActionEvent e)
        {

            JFrame restartGame = new JFrame();

            JButton yes = new JButton("Yes");

            JButton no = new JButton("No");

            yes.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    for (int row = 0; row < sudoku.length; row++)
                    {
                        for (int col = 0; col < sudoku[row].length; col++)
                        {

                            sudoku[row][col].setText("");
                        }
                    }

                    restartGame.dispose();
                }

            });

            no.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    restartGame.dispose();
                }
            });

            JLabel question = new JLabel("Are you sure you want to restart the game?");

            question.setHorizontalAlignment(JLabel.CENTER);

            restartGame.setBounds(originalX, originalX, 400, 100);

            restartGame.setVisible(true);

            restartGame.setTitle("Restart Game?");

            JPanel centerPanel = new JPanel();

            restartGame.add(centerPanel);

            centerPanel.setLayout(new BorderLayout());

            JPanel lowerPanel = new JPanel();

            lowerPanel.add(yes);

            lowerPanel.add(no);

            centerPanel.add(question, BorderLayout.CENTER);

            centerPanel.add(lowerPanel, BorderLayout.SOUTH);

            restartGame.add(centerPanel);

            originalX += 50;

            originalY += 50;

        }

    }

    private class changeToHard implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

        }

    }

    private class changeToMedium implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

        }

    }

    private class changeToEasy implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

        }

    }

    private class setNumber implements ActionListener
    {
        int originalX = 300;

        int originalY = 300;

        public void actionPerformed(ActionEvent e)

        {
            JButton sourceButton = (JButton) e.getSource();

            JFrame num = new JFrame();

            JPanel spinnerPanel = new JPanel();

            spinnerPanel.setLayout(new BorderLayout());

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 9, 1));

            spinner.setPreferredSize(new Dimension(200, 20));

            spinnerPanel.add(spinner, BorderLayout.NORTH);

            JPanel lowerPanel = new JPanel();

            JButton submit = new JButton("Submit");

            submit.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {

                    sourceButton.setText(spinner.getValue().toString());

                    num.dispose();
                }

            });

            lowerPanel.add(submit);

            num.setTitle("Set Number");

            num.setLayout(new BorderLayout());

            num.setBounds(originalX, originalX, 300, 100);

            num.setVisible(true);

            num.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            num.add(spinnerPanel, BorderLayout.CENTER);

            num.add(lowerPanel, BorderLayout.SOUTH);

            originalX += 50;

            originalY += 50;
        }

    }

}
