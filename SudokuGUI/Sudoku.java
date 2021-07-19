package SudokuGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

public class Sudoku
{

    public static void chooseDifficulty()
    {
        JFrame difficulty = new JFrame();

        difficulty.setLayout(new BorderLayout());

        JLabel question = new JLabel("Choose a difficulty for your Sudoku game");

        JPanel upperPanel = new JPanel();

        upperPanel.add(question);

        JPanel centerPanel = new JPanel();

        JComboBox options = new JComboBox();

        options.addItem("Easy");

        options.addItem("Medium");

        options.addItem("Hard");

        centerPanel.add(options);

        JButton submit = new JButton("Submit");

        submit.setHorizontalAlignment(JButton.CENTER);

        submit.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                difficulty.dispose();

                SudokuFrame sf = new SudokuFrame(options.getSelectedItem().toString());

            }

        });

        difficulty.add(upperPanel, BorderLayout.NORTH);

        difficulty.add(centerPanel, BorderLayout.CENTER);

        difficulty.add(submit, BorderLayout.SOUTH);

        Toolkit screen = Toolkit.getDefaultToolkit();

        Dimension screenSize = screen.getScreenSize();

        int height = screenSize.height;

        int width = screenSize.width;

        difficulty.setBounds(width / 4 + 200, (height / 4) + 100, 300, 140);

        difficulty.setTitle("Sudoku Difficulty");

        difficulty.setVisible(true);

        difficulty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public static void main(String[] args)
    {
        chooseDifficulty();
    }

}

class SudokuFrame extends JFrame
{
    public SudokuFrame(String level)
    {
        setTitle("Sudoku");

        setBounds(500, 200, 700, 500);

        JMenuItem exitingGame = new JMenuItem("Exit");

        exitingGame.addActionListener(new exits());

        exitingGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_DOWN_MASK));

        add(new SudokuPanel(level, exitingGame));

        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private class exits implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }

}
