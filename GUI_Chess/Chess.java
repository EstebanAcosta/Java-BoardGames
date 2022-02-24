package GUI_Chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chess
{
    public static void main(String[] args)
    {

        chooseDifficulty();

    }

    private static void chooseSide(String difficulty)
    {
        JFrame choosingSideFrame= new JFrame();
        
        choosingSideFrame.setVisible(true);
    }

    private static void chooseDifficulty()
    {

        JFrame chooseDifficultyFrame = new JFrame();

        /////////////////////////////////////// UPPER PANEL CONTAINS THE QUESTION //////////////////////////////////////////////////

        JLabel question = new JLabel("Which difficulty do you want to choose for your chess match ?");

        JPanel upperPanel = new JPanel();

        upperPanel.add(question);

        //////////////////////////////////////// CENTER PANEL CONTAINS THE OPTIONS ////////////////////////////////////////////////

        JPanel centerPanel = new JPanel();

        JComboBox options = new JComboBox();

        options.addItem("Easy");

        options.addItem("Intermediate");

        options.addItem("Expert");

        centerPanel.add(options);

        ///////////////////////////////////////// LOWER PANEL CONTAINS THE SUBMIT BUTTON //////////////////////////////////////

        JButton submit = new JButton("Submit");

        submit.setHorizontalAlignment(JButton.CENTER);

        submit.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                chooseDifficultyFrame.dispose();
                
                String choice = options.getSelectedItem().toString();

                chooseSide(choice);

                // SudokuFrame sf = new SudokuFrame(options.getSelectedItem().toString());

            }

        });

        //////////////////////////////////////// ADDING PANELS TO FRAME //////////////////////////////////////

        chooseDifficultyFrame.add(upperPanel, BorderLayout.NORTH);

        chooseDifficultyFrame.add(centerPanel, BorderLayout.CENTER);

        chooseDifficultyFrame.add(submit, BorderLayout.SOUTH);

        //////////////////////////////////////// SETTING FRAME SIZE //////////////////////////////////////

        Toolkit screen = Toolkit.getDefaultToolkit();

        Dimension screenSize = screen.getScreenSize();

        int height = screenSize.height;

        int width = screenSize.width;

        chooseDifficultyFrame.setBounds(width / 4 + 200, (height / 4) + 100, 500, 140);

        chooseDifficultyFrame.setTitle("Choosing Difficulty");

        chooseDifficultyFrame.setVisible(true);

        chooseDifficultyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}

class chessFrame extends JFrame
{
    public chessFrame(String difficulty, Color side)
    {
        chessPanel cPanel = new chessPanel();

        setTitle("Chess");

        setBounds(300, 100, 1000, 600);

        add(cPanel);

        setVisible(true);
    }

}
