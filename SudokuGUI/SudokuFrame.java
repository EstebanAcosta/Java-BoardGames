package SudokuGUI;

import javax.swing.JFrame;


public class SudokuFrame extends JFrame
{
    public SudokuFrame()
    {
        setTitle("Sudoku");
        
        setBounds(500, 200, 700, 500);

        add(new SudokuPanel());
        
        setVisible(true);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
