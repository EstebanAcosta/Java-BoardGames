package GUI_Chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

public class chessPanel extends JPanel
{

    final int ROW = 8, COL = 8;

    Timer timer;

    JLabel timerLabel = new JLabel();

    int min, timeLeft;

    public chessPanel(String level, String mySide)
    {

        JButton[][] chessBoard = new JButton[ROW][COL];

        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();

        upperPanel.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();

        JMenuBar menuBar = new JMenuBar();

        ////////////////////////////// OPENING A SAVED FILE/////////////////////////////////////////////

        JMenu openFile = new JMenu("Open File");

        JMenuItem openFileOption = new JMenuItem("Open File");

        openFile.add(openFileOption);

        menuBar.add(openFile);

        /////////////////////////////// SAVING CURRENT GAME////////////////////////////////////////////

        JMenu saveGame = new JMenu("Save Game");

        JMenuItem saveGameOption = new JMenuItem("Save Game");

        saveGame.add(saveGameOption);

        menuBar.add(saveGame);

        ///////////////////////////////// CREATING A NEW GAME//////////////////////////////////////////

        JMenu newGame = new JMenu("New Game");

        JMenuItem newGameOption = new JMenuItem("New Game");

        newGame.add(newGameOption);

        menuBar.add(newGame);

        ////////////////////////////////// UNDOING THE LAST MOVE/////////////////////////////////////////

        JMenu undoMove = new JMenu("Undo Move");

        JMenuItem undoMoveOption = new JMenuItem("Undo Move");

        undoMove.add(undoMoveOption);

        menuBar.add(undoMove);

        /////////////////////////////////// SETTING GAME DIFFICULTY////////////////////////////////////////

        JMenu setDifficulty = new JMenu("Set Difficulty");

        JMenuItem easy = new JMenuItem("Easy");

        JMenuItem medium = new JMenuItem("Medium");

        JMenuItem expert = new JMenuItem("Expert");

        setDifficulty.add(easy);

        setDifficulty.add(medium);

        setDifficulty.add(expert);

        menuBar.add(setDifficulty);

        /////////////////////////////////// EXITING GAME ////////////////////////////////////////

        JMenu exit = new JMenu("Exit");

        JMenuItem exiting = new JMenuItem("Exit Game");

        exit.add(exiting);

        menuBar.add(exit);

        /////////////////////////////////// ADDED MENU BAR TO TOP PANEL ////////////////////////////////////////

        menuPanel.add(menuBar);

        upperPanel.add(menuPanel, BorderLayout.NORTH);

        //////////////////////////////////////////////////// SETTING UP LABELS AT THE TOP //////////////////////////////////////////////////////////

        JPanel lvlPanel = new JPanel();

        lvlPanel.setLayout(new BorderLayout());

        //create a label that shows the game's level
        
        JLabel labelHeader = new JLabel("Level:" + level);

        labelHeader.setHorizontalAlignment(JLabel.CENTER);

        lvlPanel.add(labelHeader, BorderLayout.NORTH);

        JLabel whoseTurnLabel = new JLabel("It's " + (mySide == "WHITE" ? "Your " : "Computer's ") + "turn");

        whoseTurnLabel.setHorizontalAlignment(JLabel.CENTER);

        lvlPanel.add(whoseTurnLabel, BorderLayout.CENTER);

        /////////////////////////////////// ADDED LEVEL LABEL TO TOP PANEL ////////////////////////////////////////

        upperPanel.add(lvlPanel, BorderLayout.CENTER);

        ////////////////////////////////////// SETTING UP TIMER///////////////////////////////////////////

        if (level == "Easy")
        {
            min = 30;
        }

        else if (level == "Medium")
        {
            min = 20;
        }

        else
        {
            min = 10;
        }

        timeLeft = min * 60 * 1000;

        // timer = new Timer(1000, new countDown(timeLeft));

        // timer.start();

        JPanel timerPanel = new JPanel();

        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        timerPanel.add(timerLabel);

        /////////////////////////////////// ADDED TIMER TO TOP PANEL ////////////////////////////////////////

        upperPanel.add(timerPanel, BorderLayout.SOUTH);

        ///////////////////////////////////////////////////////////////////////////

        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(ROW, COL));

        Color startColor;

        for (int r = 0; r < ROW; r++)
        {

            if (r % 2 == 0)
                startColor = Color.BLACK;
            else
                startColor = Color.WHITE;

            for (int c = 0; c < COL; c++)
            {

                JButton square = new JButton();

                square.setOpaque(true);

                square.setBorderPainted(false);

                square.setBackground(startColor);

                chessBoard[r][c] = square;

                centerPanel.add(square);

                startColor = (startColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
            }
        }

        add(upperPanel, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

    }
}
