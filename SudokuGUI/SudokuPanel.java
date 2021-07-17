package SudokuGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class SudokuPanel extends JPanel
{
    public SudokuPanel()
    {
        setLayout(new BorderLayout());
        
        JPanel game = new JPanel();
        
        game.setLayout(new GridLayout(9,9));
        
        JPanel upperPanel = new JPanel();

        JMenuBar menu = new JMenuBar();
        
        JMenu newGame = new JMenu("New Game");
        
        JMenu restartGame = new JMenu();
        
        JButton [][] sudoku = new JButton[9][9];
        
        // loop through each row in the button grid
        for (int row = 0; row < 9; row++)
        {
            // loop through each column in the button grid
            for (int col = 0; col < 9; col++)
            {
                // create a new button
                JButton tile = new JButton();
                
//                tile.add();
                
                sudoku[row][col] = tile;
                
                game.add(tile);
                
            }
            
        }
        
              
        add(upperPanel, BorderLayout.NORTH);
        
        add(game,BorderLayout.CENTER);
    }
}
