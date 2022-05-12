package GUI_Chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Chess
{
    public static void main(String[] args)
    {

        chooseDifficulty();

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

    private static void chooseSide(String difficulty)
    {
        JFrame choosingSideFrame = new JFrame();

        choosingSideFrame.setLayout(new BorderLayout());

        Random random = new Random();

        JPanel upperJPanel = new JPanel();

        JLabel prompt = new JLabel("Press the button to randomly choose a side");

        upperJPanel.add(prompt);

        choosingSideFrame.add(upperJPanel, BorderLayout.NORTH);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel lowerJPanel = new JPanel();

        JButton randomButton = new JButton("Randomly Choose A Side");

        randomButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                boolean choice = random.nextBoolean();

                String side = choice == true ? "WHITE" : "BLACK";
                
                JOptionPane.showMessageDialog(new JFrame(), "You're " + side);
                
                choosingSideFrame.dispose();

                chessFrame sf = new chessFrame(difficulty, side);

            }

        });

        lowerJPanel.add(randomButton);

        choosingSideFrame.add(lowerJPanel);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        Toolkit screen = Toolkit.getDefaultToolkit();

        Dimension screenSize = screen.getScreenSize();

        int height = screenSize.height;

        int width = screenSize.width;
        
        choosingSideFrame.setTitle("Randomly Choose Side");

        choosingSideFrame.setBounds(width / 4 + 200, (height / 4) + 100, 500, 120);

        choosingSideFrame.setVisible(true);
        
        choosingSideFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }



}

class chessFrame extends JFrame
{
    public chessFrame(String difficulty, String mySide)
    {
        chessPanel cPanel = new chessPanel(difficulty, mySide);

        setTitle("Chess");

        setBounds(300, 100, 1000, 600);

        add(cPanel);

        setVisible(true);
    }

}
