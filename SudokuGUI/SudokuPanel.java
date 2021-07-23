package SudokuGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import com.sun.glass.events.KeyEvent;

public class SudokuPanel extends JPanel
{

    JButton[][] sudoku;

    JButton finalSubmit;

    JLabel lvl;

    ArrayList<JPanel> boxes = new ArrayList<JPanel>();

    JLabel timerLabel = new JLabel();
    
    int min = 20;

    int timeLeft = min * 60 * 1000;
    
    Timer timer;

    public SudokuPanel(String level, JMenuItem exiting)
    {

        sudoku = sudokuGenerator();

        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();

        upperPanel.setLayout(new BorderLayout());

        JPanel lowerPanel = new JPanel();

        JMenu newGame = new JMenu("New Game");

        JMenuItem startNewGame = new JMenuItem("New Game");

        startNewGame.addActionListener(new startingNewGame());

        startNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_DOWN_MASK));

        newGame.add(startNewGame);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu resetGame = new JMenu("Reset Game");

        JMenuItem resettingGame = new JMenuItem("Reset Game");

        resettingGame.addActionListener(new resettingTheGame());

        resettingGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));

        resetGame.add(resettingGame);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu difficulty = new JMenu("Adjust Difficulty");

        JMenuItem hard = new JMenuItem("Hard");

        hard.addActionListener(new confirmChangeInDifficulty());

        hard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));

        JMenuItem medium = new JMenuItem("Medium");

        medium.addActionListener(new confirmChangeInDifficulty());

        medium.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));

        JMenuItem easy = new JMenuItem("Easy");

        easy.addActionListener(new confirmChangeInDifficulty());

        easy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));

        difficulty.add(hard);

        difficulty.add(medium);

        difficulty.add(easy);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu exit = new JMenu("Exit");

        exit.add(exiting);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu time = new JMenu("Time");

        JMenu addTime = new JMenu("Add Time");

        JMenuItem fifteenSec = new JMenuItem("15 secs");

        fifteenSec.addActionListener(new addTime(15));

        JMenuItem thirtySec = new JMenuItem("30 secs");

        thirtySec.addActionListener(new addTime(30));

        JMenuItem oneMinute = new JMenuItem("60 secs");

        oneMinute.addActionListener(new addTime(60));

        JMenuItem resetTime = new JMenuItem("Reset Time");

        addTime.add(fifteenSec);

        addTime.add(thirtySec);

        addTime.add(oneMinute);

        resetTime.addActionListener(new resetTime());

        resetTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.SHIFT_DOWN_MASK));

        time.add(resetTime);

        time.add(addTime);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel menuPanel = new JPanel();

        JMenuBar menu = new JMenuBar();

        menu.add(newGame);

        menu.add(resetGame);

        menu.add(difficulty);

        menu.add(time);

        menu.add(exit);

        menuPanel.add(menu);

        upperPanel.add(menuPanel, BorderLayout.NORTH);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel lvlPanel = new JPanel();

        lvl = new JLabel("Level: " + level);

        lvl.setHorizontalAlignment(JLabel.CENTER);

        lvlPanel.add(lvl);

        upperPanel.add(lvl, BorderLayout.CENTER);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        timer = new Timer(2000, new countDown(timeLeft));

        timer.start();

        JPanel timerPanel = new JPanel();

        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        timerPanel.add(timerLabel);

        upperPanel.add(timerPanel, BorderLayout.SOUTH);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel game = new JPanel();

        game.setLayout(new GridLayout(3, 3));

        int startRow = 0;

        int startCol = 0;

        int countBox = 0;

        // loop through each row in the sudoku board
        for (int row = 0; row < 3; row++)
        {
            // loop through each column in the sudoku board
            for (int col = 0; col < 3; col++)
            {

                // create a panel that will store each of the six boxes in the sudoku board
                JPanel box = new JPanel(new GridLayout(3, 3));

                box.setBorder(LineBorder.createGrayLineBorder());

                // loop through each row of the box
                for (int boxRow = startRow; boxRow < startRow + 3; boxRow++)
                {
                    // loop through each column of the box
                    for (int boxCol = startCol; boxCol < startCol + 3; boxCol++)
                    {
                        int thisRow = boxRow;

                        int thisCol = boxCol;

                        int thisBox = countBox;

                        // create a new button
                        JButton tile = new JButton();

                        tile.addActionListener(new ActionListener()
                        {

                            public void actionPerformed(ActionEvent e)
                            {

                                JButton sourceButton = (JButton) e.getSource();

                                JFrame chooseNumFrame = new JFrame();

                                JPanel upperPanel = new JPanel();

                                JLabel question = new JLabel("Please click on a number for this box in row " + (thisRow + 1) + " and column " + (thisCol + 1));

                                question.setHorizontalAlignment(JLabel.CENTER);

                                upperPanel.add(question);

                                JPanel centerPanel = new JPanel();

                                centerPanel.setLayout(new GridLayout(3, 3));

                                for (int i = 0; i < 9; i++)
                                {

                                    JButton setNumber = new JButton(Integer.toString(i + 1));

                                    setNumber.addActionListener(new ActionListener()
                                    {

                                        public void actionPerformed(ActionEvent e)
                                        {
                                            sourceButton.setText(setNumber.getText());

                                            int newNum = Integer.parseInt(setNumber.getText());

                                            if (placedRepeatedNumberInRow(sudoku, thisRow, newNum) || placedRepeatedNumberInColumn(sudoku, thisCol, newNum)
                                            || placedRepeatedNumberInABox(thisBox, newNum))
                                            {

                                                sourceButton.setForeground(Color.RED);

                                                sourceButton.setOpaque(true);
                                            }

                                            else
                                            {
                                                if (sourceButton.getForeground() == Color.RED)
                                                {
                                                    sourceButton.setForeground(Color.BLACK);
                                                }
                                            }

                                            chooseNumFrame.dispose();
                                        }

                                    });

                                    centerPanel.add(setNumber);

                                }

                                JPanel lowerPanel = new JPanel();

                                chooseNumFrame.pack();

                                chooseNumFrame.setResizable(false);

                                chooseNumFrame.setTitle("Set Number");

                                chooseNumFrame.setLayout(new BorderLayout());

                                chooseNumFrame.setBounds(200, 200, 400, 130);

                                chooseNumFrame.setVisible(true);

                                chooseNumFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                                chooseNumFrame.add(upperPanel, BorderLayout.NORTH);

                                chooseNumFrame.add(centerPanel, BorderLayout.CENTER);

                                chooseNumFrame.add(lowerPanel, BorderLayout.SOUTH);

                            }

                        });

                        // add this button to the box
                        box.add(tile);

                        // store the button to the 2D array
                        sudoku[boxRow][boxCol] = tile;
                    }
                }

                countBox++;

                // since we are keeping track of all the boxes in the grid, add the box to the list of boxes
                boxes.add(box);

                // add this panel to the main grid panel
                game.add(box);

                // if we are on the last box
                if (startCol == 6)
                {
                    // reset the starting column number to the first column of the sudoku grid
                    startCol = 0;

                    // add three to the starting row since we are going to start looping at the next row of boxes
                    startRow += 3;
                }

                // if we are on the first two boxes of the row
                else
                {
                    // add three to starting column # to get to the next box in that row
                    startCol += 3;
                }

            }

        }

        finalSubmit = new JButton("Submit");

        finalSubmit.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                if (isSudokuCompleted(sudoku))
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Congratulatons On Completing The Sudoku Puzzle",
                    "Puzzle Completed", JOptionPane.INFORMATION_MESSAGE);
                }

                else
                {
                }
            }

        });

        lowerPanel.add(finalSubmit);

        add(upperPanel, BorderLayout.NORTH);

        add(game, BorderLayout.CENTER);

        add(lowerPanel, BorderLayout.SOUTH);
    }

    public JButton[][] sudokuGenerator()
    {
        JButton[][] randSudoku = new JButton[9][9];

        return randSudoku;
    }

    /**
     * This method checks to see if the entire board is completed, if each row only has unique numbers,
     * if each column only has unique numbers and if each box only has unique numbers.
     * @param sudoku
     * @return true if the board is completed and unique numbers only appear in each row, in each column and in each box
     *         false if none of these tests pass
     */
    public boolean isSudokuCompleted(JButton[][] sudoku)
    {

        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                if (sudoku[row][col].getText() == "")
                {
                    return false;
                }
            }
        }

        if (doesEachRowHaveUniqueNumbers(sudoku) || doesEachColumnHaveUniqueNumbers(sudoku)
        && doesEachBoxHaveUniqueNumbers(sudoku))
        {
            return true;
        }

        return false;

    }

    public JButton[] getAllNumbersInCol(int col)
    {
        // create an empty array list
        JButton[] eachCol = new JButton[9];

        int count = 0;

        // loop through each row
        for (int row = 0; row < sudoku.length; row++)
        {
            // add the number in that column to the array
            eachCol[count] = sudoku[row][col];

            // move to the next position
            count++;
        }

        return eachCol;
    }

    public JButton[] getAllNumbersInBox(JPanel box)
    {
        // get the components in the panel and store it in the list of components
        Component[] components = box.getComponents();

        // create a new JButton list whiich stores all the JButtons in that box
        JButton[] boxNums = new JButton[9];

        int count = 0;

        // loop through each component
        for (Component c : components)
        {
            // if this component is a JButton
            if (c instanceof JButton)
            {
                // cast each component to a JButton and store it in the list of JButtons
                boxNums[count] = (JButton) c;

                count++;

            }
        }

        return boxNums;

    }

    /***
     * @param numbers
     * @return
     */
    public boolean isUniqueSet(JButton[] numbers)
    {
        // create an empty array list
        ArrayList<Integer> checking = new ArrayList<Integer>();

        for (JButton number : numbers)
        {
            int num = (number.getText() == "" ? 0 : Integer.parseInt(number.getText()));

            // if the number that we are currently on isn't in the empty array list
            if (!checking.contains(num))
            {
                // add it to the list
                checking.add(num);
            }

            else
            {
                return false;
            }
        }

        return true;
    }

    public boolean placedRepeatedNumberInRow(JButton[][] sudoku, int row, int number)
    {

        JButton[] thisRow = sudoku[row];

        int countThisNum = 0;

        for (JButton eachNumInRow : thisRow)
        {
            int numInRow = (eachNumInRow.getText() == "" ? 0 : Integer.parseInt(eachNumInRow.getText()));

            if (numInRow == number)
            {
                countThisNum++;
            }

        }

        return countThisNum > 1 ? true : false;

    }

    public boolean placedRepeatedNumberInColumn(JButton[][] sudoku, int col, int number)
    {

        JButton[] thisCol = getAllNumbersInCol(col);

        int countThisNum = 0;

        for (JButton eachNumInCol : thisCol)
        {
            int numInCol = (eachNumInCol.getText() == "" ? 0 : Integer.parseInt(eachNumInCol.getText()));

            if (numInCol == number)
            {
                countThisNum++;
            }
        }

        return countThisNum > 1 ? true : false;

    }

    public boolean placedRepeatedNumberInABox(int box, int number)
    {
        JButton[] thisBox = getAllNumbersInBox(boxes.get(box));

        int countThisNum = 0;

        for (JButton eachNumInBox : thisBox)
        {
            int numInBox = (eachNumInBox.getText() == "" ? 0 : Integer.parseInt(eachNumInBox.getText()));

            if (numInBox == number)
            {
                countThisNum++;
            }
        }

        return countThisNum > 1 ? true : false;

    }

    /***
     * Method goes through each row of the board and checks if each row doesn't have any repeated numbers
     * @param sudoku
     * @return
     */
    public boolean doesEachRowHaveUniqueNumbers(JButton[][] sudoku)
    {
        for (int row = 0; row < sudoku.length; row++)
        {
            // grab this row from the sudoku board
            JButton[] eachRow = sudoku[row];

            // loop through each number in this specific row
            for (int rowNum = 0; rowNum < eachRow.length; rowNum++)
            {
                // check if the list of row numbers doens't have any repeated numbers
                if (isUniqueSet(eachRow))
                {
                    // move on to the next row
                    continue;
                }

                // if the rows do have repeated numbers
                else
                {

                    return false;
                }

            }
        }

        return true;
    }

    /***
     * Method goes through each column of the board and checks if each column doesn't have any repeated numbers
     * @param sudoku
     * @return
     */
    public boolean doesEachColumnHaveUniqueNumbers(JButton[][] sudoku)
    {

        // loop through each column
        for (int col = 0; col < sudoku[0].length; col++)
        {

            // check if the list of column numbers doens't have any repeated numbers
            if (isUniqueSet(getAllNumbersInCol(col)))
            {
                // move on to the next column
                continue;
            }

            // if the columns do have repeated numbers
            else
            {

                return false;
            }

        }

        return true;

    }

    /***
     * Methods goes through each box of the board and checks if each box doesn't have any repeated numbers
     * @param sudoku
     * @return
     */
    public boolean doesEachBoxHaveUniqueNumbers(JButton[][] sudoku)
    {

        // loop through each box in the six boxes
        for (JPanel box : boxes)
        {

            // check if the list of box numbers doens't have any repeated numbers
            if (isUniqueSet(getAllNumbersInBox(box)))
            {

                // move on to the next row
                continue;

            }

            // if the box has repeated numbers
            else
            {

                return false;
            }

        }
        return true;

    }

    private class addTime implements ActionListener
    {
        int addTime;

        public addTime(int time)
        {
            addTime = time;
        }

        public void actionPerformed(ActionEvent e)
        {
            
        }

    }

    private class countDown implements ActionListener
    {

        int timeLeft;

        public countDown(int timeLeft)
        {

            this.timeLeft = timeLeft;
        }

        public void actionPerformed(ActionEvent e)
        {
            timeLeft -= 1000;

            Timer timer = (Timer) e.getSource();

            SimpleDateFormat df = new SimpleDateFormat("mm:ss");

            timerLabel.setText("Timer: " + df.format(timeLeft));

            if (timeLeft <= 0)
            {

                timer.stop();

            }

        }

    }

    private class resetTime implements ActionListener
    {

        int originalX = 300;

        int originalY = 300;

        public void actionPerformed(ActionEvent e)
        {
            JMenuItem source = (JMenuItem) e.getSource();

            JFrame questionUserFrame = new JFrame();

            questionUserFrame.setLayout(new BorderLayout());

            JLabel question = new JLabel("Are you sure you want to reset the timer?");

            question.setHorizontalAlignment(JLabel.CENTER);

            JPanel centerPanel = new JPanel();

            centerPanel.add(question);

            JPanel lowerPanel = new JPanel();

            JButton yes = new JButton("Yes");

            yes.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    timer.stop();
                    
                    System.out.println(timeLeft);
                }
            });

            JButton no = new JButton("No");

            no.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    questionUserFrame.dispose();
                }

            });

            lowerPanel.add(yes);

            lowerPanel.add(no);

            questionUserFrame.setTitle("Confirmation of Reset Timer");

            questionUserFrame.add(centerPanel, BorderLayout.CENTER);

            questionUserFrame.add(lowerPanel, BorderLayout.SOUTH);

            questionUserFrame.setBounds(originalX, originalX, 400, 100);;

            questionUserFrame.setVisible(true);

            questionUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }

    private class startingNewGame implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            Sudoku s = new Sudoku();

            s.chooseDifficulty();
        }

    }

    private class resettingTheGame implements ActionListener
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

            JLabel question = new JLabel("Are you sure you want to reset the game?");

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

    private class confirmChangeInDifficulty implements ActionListener
    {

        int originalX = 300;

        int originalY = 300;

        public void actionPerformed(ActionEvent e)
        {
            JMenuItem source = (JMenuItem) e.getSource();

            JFrame questionUserFrame = new JFrame();

            questionUserFrame.setLayout(new BorderLayout());

            JLabel question = new JLabel("Are you sure you want to change the difficulty to " + source.getText() + "?");

            question.setHorizontalAlignment(JLabel.CENTER);

            JPanel centerPanel = new JPanel();

            centerPanel.add(question);

            JPanel lowerPanel = new JPanel();

            JButton yes = new JButton("Yes");

            if (source.getText().equalsIgnoreCase("Easy"))
            {
                yes.addActionListener(new setToEasy(questionUserFrame));

            }

            else if (source.getText().equalsIgnoreCase("Medium"))
            {
                yes.addActionListener(new setToMedium(questionUserFrame));

            }

            else
            {
                yes.addActionListener(new setToHard(questionUserFrame));

            }

            JButton no = new JButton("No");

            no.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    questionUserFrame.dispose();
                }

            });

            lowerPanel.add(yes);

            lowerPanel.add(no);

            questionUserFrame.setTitle("Confirmation of Change In Difficulty");

            questionUserFrame.add(centerPanel, BorderLayout.CENTER);

            questionUserFrame.add(lowerPanel, BorderLayout.SOUTH);

            questionUserFrame.setBounds(originalX, originalX, 400, 100);;

            questionUserFrame.setVisible(true);

            questionUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }

    private class setToHard implements ActionListener
    {

        JFrame confirm;

        public setToHard(JFrame confirm)
        {
            this.confirm = confirm;
        }

        public void actionPerformed(ActionEvent e)
        {
            confirm.dispose();

            lvl.setText("Level: Hard");
        }

    }

    private class setToMedium implements ActionListener
    {

        JFrame confirm;

        public setToMedium(JFrame confirm)
        {
            this.confirm = confirm;
        }

        public void actionPerformed(ActionEvent e)
        {
            confirm.dispose();

            lvl.setText("Level: Medium");
        }

    }

    private class setToEasy implements ActionListener
    {

        JFrame confirm;

        public setToEasy(JFrame confirm)
        {
            this.confirm = confirm;
        }

        public void actionPerformed(ActionEvent e)
        {
            confirm.dispose();

            lvl.setText("Level: Easy");

        }

    }

}
