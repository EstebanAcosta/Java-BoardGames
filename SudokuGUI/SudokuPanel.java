package SudokuGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import com.sun.glass.events.KeyEvent;

public class SudokuPanel extends JPanel
{
    public SudokuPanel(JMenuItem exiting)
    {
        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();

        upperPanel.setLayout(new BorderLayout());

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

        JPanel menuPanel = new JPanel();

        JMenuBar menu = new JMenuBar();

        menu.add(newGame);

        menu.add(restartGame);

        menu.add(difficulty);

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

        JButton[][] sudoku = new JButton[9][9];

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

                    box.add(tile);
                }

                game.add(box);

            }

        }

        add(upperPanel, BorderLayout.NORTH);

        add(game, BorderLayout.CENTER);
    }

    private class startingNewGame implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            
        }

    }

    private class restartingTheGame implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

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

}
