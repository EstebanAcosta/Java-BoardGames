package SudokuGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

public class Sudoku
{
    public static void main(String[] args)
    {
        SudokuFrame sF = new SudokuFrame();

    }

}

class SudokuFrame extends JFrame
{
    public SudokuFrame()
    {
        setTitle("Sudoku");

        setBounds(500, 200, 700, 500);

        JMenuItem exitingGame = new JMenuItem("Exit");

        exitingGame.addActionListener(new exits());

        exitingGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_DOWN_MASK));

        add(new SudokuPanel(exitingGame));

        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private class exits implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }

}
