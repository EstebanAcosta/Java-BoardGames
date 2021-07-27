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

import TicTacToeGUI.TicTacToe;

public class SudokuPanel extends JPanel
{

    ArrayList<JPanel> boxes = new ArrayList<JPanel>();

    JButton[][] sudoku, solution;

    JButton finalSubmit, autoFill;

    JLabel lvl;

    JLabel timerLabel = new JLabel();

    int min, timeLeft;

    Timer timer;

    public SudokuPanel(String level, JMenuItem exiting, JButton endGameButton)
    {

        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();

        upperPanel.setLayout(new BorderLayout());

        JPanel lowerPanel = new JPanel();

        /////////////////////////////////////// NEW GAME MENU ///////////////////////////////////////////////////////////////////////

        JMenu newGame = new JMenu("New Game");

        JMenuItem startNewGame = new JMenuItem("New Game");

        startNewGame.addActionListener(new startingNewGame());

        startNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_DOWN_MASK));

        newGame.add(startNewGame);

        ////////////////////////////////////////// RESET GAME MENU ////////////////////////////////////////////////////////////////////

        JMenu resetGame = new JMenu("Reset Game");

        JMenuItem resettingGame = new JMenuItem("Reset Game");

        resettingGame.addActionListener(new resettingTheGame());

        resettingGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));

        resetGame.add(resettingGame);

        ////////////////////////////////////////////// ADJUST DIFFICULTY ////////////////////////////////////////////////////////////////

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

        ///////////////////////////////////////////////// EXIT MENU ///////////////////////////////////////////////////////////

        JMenu exit = new JMenu("Exit");

        exit.add(exiting);

        ///////////////////////////////////////////////// STARTING TIMER ///////////////////////////////////////////////////////////

        if (level == "Hard")
        {
            min = 20;
        }

        else if (level == "Medium")
        {
            min = 25;
        }

        else
        {
            min = 30;
        }

        timeLeft = min * 60 * 1000;

        timer = new Timer(1000, new countDown(timeLeft));

        timer.start();

        JPanel timerPanel = new JPanel();

        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        timerPanel.add(timerLabel);

        upperPanel.add(timerPanel, BorderLayout.SOUTH);

        ///////////////////////////////////////////////// TIME MENU/////////////////////////////////////////////////////////////

        JMenu time = new JMenu("Time");

        JMenuItem pauseOrResume = new JMenuItem("Pause");

        pauseOrResume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));

        pauseOrResume.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                JMenuItem pause_or_resume = (JMenuItem) e.getSource();

                if (pause_or_resume.getText().equalsIgnoreCase("Pause"))
                {
                    timer.stop();

                    pause_or_resume.setText("Resume");

                    pause_or_resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));

                }

                else
                {
                    timer.start();

                    pause_or_resume.setText("Pause");

                    pause_or_resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));

                }

            }

        });

        time.add(pauseOrResume);

        ////////////////////////////////////////////////// ADDING ALL THE COMPONENTS TO THE PANEL ////////////////////////////////////////////////////////////

        JPanel menuPanel = new JPanel();

        JMenuBar menu = new JMenuBar();

        menu.add(newGame);

        menu.add(resetGame);

        menu.add(difficulty);

        menu.add(time);

        menu.add(exit);

        menuPanel.add(menu);

        upperPanel.add(menuPanel, BorderLayout.NORTH);

        //////////////////////////////////////////////////// SETTUNG UP LABELS AT THE TOP OF THE PUZZLE //////////////////////////////////////////////////////////

        JPanel lvlPanel = new JPanel();

        JLabel labelHeader = new JLabel("Level:");

        lvl = new JLabel(level);

        lvl.setHorizontalAlignment(JLabel.CENTER);

        lvlPanel.add(labelHeader);

        lvlPanel.add(lvl);

        upperPanel.add(lvlPanel, BorderLayout.CENTER);

        //////////////////////////////////////////////////// CREATING 81 CELLS IN THE SUDOKU PUZZLE //////////////////////////////////////////////////////////

        JPanel game = new JPanel();

        sudokuGenerator(lvl.getText());

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

                        // create a new button for each cell
                        JButton tile = new JButton();

                        tile.addActionListener(new ActionListener()
                        {

                            public void actionPerformed(ActionEvent e)
                            {

                                JButton sourceButton = (JButton) e.getSource();

                                // create a new frame
                                JFrame chooseNumFrame = new JFrame();

                                JPanel upperPanel = new JPanel();

                                JLabel question = new JLabel("Please click on a number for this box in row " + (thisRow + 1) + " and column " + (thisCol + 1));

                                question.setHorizontalAlignment(JLabel.CENTER);

                                upperPanel.add(question);

                                JPanel centerPanel = new JPanel();

                                centerPanel.setLayout(new GridLayout(3, 3));

                                // loop nine times to create nine buttons
                                for (int i = 0; i < 9; i++)
                                {

                                    // Give each a button a number
                                    JButton setNumber = new JButton(Integer.toString(i + 1));

                                    // Make sure that each buttpn has a listener
                                    setNumber.addActionListener(new ActionListener()
                                    {

                                        public void actionPerformed(ActionEvent e)
                                        {
                                            // When the user presses this button in the new window, whatever number that is stored in the pressed button
                                            // will be stored in the sudoku cell
                                            sourceButton.setText(setNumber.getText());

                                            // Get the number the user pressed and convert it into an integer
                                            int newNum = Integer.parseInt(setNumber.getText());

                                            // if the number the user put in that new cell happens to be in the same row, column or box as the same number
                                            if (placedRepeatedNumberInRow(sudoku, thisRow, newNum) || placedRepeatedNumberInColumn(sudoku, thisCol, newNum)
                                            || placedRepeatedNumberInABox(thisBox, newNum))
                                            {

                                                // Color the number red
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

                        if (sudoku[boxRow][boxCol].getText() == "0")
                        {
                            tile.setText("");

                        }

                        else
                        {
                            tile.setText(sudoku[boxRow][boxCol].getText());

                            tile.setForeground(Color.blue);

                        }

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

                    JFrame continueGame = new JFrame();

                    continueGame.setTitle("Continue Game");

                    JLabel continueQuestion = new JLabel("Would you like to start another game?");

                    continueQuestion.setHorizontalAlignment(JLabel.CENTER);

                    continueGame.setLayout(new BorderLayout());

                    JPanel questionArea = new JPanel();

                    questionArea.add(continueQuestion);

                    continueGame.add(questionArea, BorderLayout.NORTH);

                    JButton yes = new JButton("Yes");

                    yes.addActionListener(new startingNewGame(continueGame));

                    JPanel submitArea = new JPanel();

                    submitArea.add(yes);

                    submitArea.add(endGameButton);

                    continueGame.add(submitArea, BorderLayout.SOUTH);

                    continueGame.setBounds(400, 400, 400, 100);

                    continueGame.setVisible(true);

                    continueGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                }

                else
                {

                    if (isMissingNumbers(sudoku))
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "There are some cells that haven't been filled in yet",
                        "Puzzle Not Completed", JOptionPane.INFORMATION_MESSAGE);
                    }

                    else if (doesEachRowHaveUniqueNumbers(sudoku))
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "A number has appearede more than once in the same row",
                        "Puzzle Not Completed", JOptionPane.INFORMATION_MESSAGE);
                    }

                    else if (doesEachColumnHaveUniqueNumbers(sudoku))
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "A number has appeared more than once in the same column",
                        "Puzzle Not Completed", JOptionPane.INFORMATION_MESSAGE);
                    }

                    else if (doesEachBoxHaveUniqueNumbers(sudoku))
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "A number has appeared more than once in the same box",
                        "Puzzle Not Completed", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

        });

        autoFill = new JButton("Autofill");

        autoFill.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                for (int r = 0; r < 9; r++)
                {
                    for (int col = 0; col < 9; col++)
                    {
                        sudoku[r][col].setText(solution[r][col].getText());
                    }
                }
            }

        });

        lowerPanel.add(finalSubmit);

        lowerPanel.add(autoFill);

        add(upperPanel, BorderLayout.NORTH);

        add(game, BorderLayout.CENTER);

        add(lowerPanel, BorderLayout.SOUTH);
    }

    public void sudokuGenerator(String level)
    {
        int N = 9, K = 0;

        if (level == "Hard")
        {
            K = 50;
        }

        else if (level == "Medium")
        {
            K = 40;
        }

        else
        {
            K = 30;
        }

        SudokuGenerator generator = new SudokuGenerator(N, K);

        sudoku = generator.generateIncompleteSudoku();

        solution = generator.returnSudokuSolution();

    }

    public boolean isMissingNumbers(JButton[][] sudoku)
    {
        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                if (sudoku[row][col].getText() == "")
                {
                    return true;
                }
            }
        }

        return false;
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

        return !isMissingNumbers(sudoku) && doesEachRowHaveUniqueNumbers(sudoku) && doesEachColumnHaveUniqueNumbers(sudoku) &&
        doesEachBoxHaveUniqueNumbers(sudoku);

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
     * Method checks to see if a given list on;y has unique numbers(if the numbers don't appear in a given list more than once)
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
                if (isSudokuCompleted(sudoku))
                {

                }

                timer.stop();

            }

        }

    }

    /**
     * Methods starts a new game
     * @author estebanacosta
     */
    private class startingNewGame implements ActionListener
    {

        private JFrame newGameFrame;
        
        public startingNewGame()
        {
        }
        
        public startingNewGame(JFrame frame)
        {
            newGameFrame = frame;
        }
        public void actionPerformed(ActionEvent e)
        {
            Sudoku s = new Sudoku();
            
            if(newGameFrame != null)
            {
                newGameFrame.dispose();
            }

            s.chooseDifficulty();
        }

    }

    /**
     * Method erases all the numbers in each cell and returns an empty sudoku board
     * @author estebanacosta
     */
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

                            if (sudoku[row][col].getForeground() != Color.blue)
                            {
                                sudoku[row][col].setText("");

                            }
                        }
                    }

                    if (lvl.getText() == "Hard")
                    {
                        min = 20;
                    }

                    else if (lvl.getText() == "Medium")
                    {
                        min = 25;
                    }

                    else
                    {
                        min = 30;
                    }

                    timeLeft = min * 60 * 1000;

                    timer.stop();

                    timer = new Timer(1000, new countDown(timeLeft));

                    timer.start();

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

    /**
     * A confirmation window will appear whenever the player decides to change the difficulty of the game
     * @author estebanacosta
     */
    private class confirmChangeInDifficulty implements ActionListener
    {

        int originalX = 300;

        int originalY = 300;

        public void actionPerformed(ActionEvent e)
        {
            JMenuItem newLevel = (JMenuItem) e.getSource();

            JFrame questionUserFrame = new JFrame();

            questionUserFrame.setLayout(new BorderLayout());

            JLabel question = new JLabel("Are you sure you want to change the difficulty to " + newLevel.getText() + "?");

            question.setHorizontalAlignment(JLabel.CENTER);

            JPanel centerPanel = new JPanel();

            centerPanel.add(question);

            JPanel lowerPanel = new JPanel();

            JButton yes = new JButton("Yes");

            yes.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    questionUserFrame.dispose();

                    lvl.setText(newLevel.getText());

                    timer.stop();

                    if (newLevel.getText() == "Hard")
                    {
                        min = 20;

                    }

                    else if (newLevel.getText() == "Medium")
                    {
                        min = 25;
                    }

                    else
                    {
                        min = 30;
                    }

                    sudokuGenerator(lvl.getText());

                    for (int r = 0; r < 9; r++)
                    {
                        for (int col = 0; col < 9; col++)
                        {
                            System.out.print(sudoku[r][col].getText() + " ");
                        }

                        System.out.println();
                    }

                    timeLeft = min * 60 * 1000;

                    timer = new Timer(1000, new countDown(timeLeft));

                    timer.start();
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

            questionUserFrame.setTitle("Confirmation of Change In Difficulty");

            questionUserFrame.add(centerPanel, BorderLayout.CENTER);

            questionUserFrame.add(lowerPanel, BorderLayout.SOUTH);

            questionUserFrame.setBounds(originalX, originalX, 400, 100);;

            questionUserFrame.setVisible(true);

            questionUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }

}
