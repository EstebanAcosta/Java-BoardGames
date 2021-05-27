package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;

public class PlayingWithPopUpMenu
{
    public static void main(String[] args)
    {
        popUpMenuFrame pu = new popUpMenuFrame();

        pu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pu.setVisible(true);

    }
}

class popUpMenuFrame extends JFrame
{

    public popUpMenuFrame()
    {
        setTitle("PopUpMenu Frame");

        setBounds(600, 300, 600, 350);
        
        add(new popUpMenuPanel());

    }

}

class popUpMenuPanel extends JPanel
{
    public popUpMenuPanel()
    {
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();

        JMenuBar myBar = new JMenuBar();

        JMenu font = new JMenu("Font");

        JMenu style = new JMenu("Style");

        JMenu size = new JMenu("Size");

        myBar.add(font);

        myBar.add(style);

        myBar.add(size);
        
        menuPanel.add(myBar);

        add(menuPanel, BorderLayout.NORTH);

        JTextPane myArea = new JTextPane();

        add(myArea, BorderLayout.CENTER);

        JPopupMenu popup = new JPopupMenu();

        JMenuItem option1 = new JMenuItem("Option 1");

        JMenuItem option2 = new JMenuItem("Option 2");

        JMenuItem option4 = new JMenuItem("Option 4");

        JMenuItem option3 = new JMenuItem("Option 3");

        popup.add(option1);

        popup.add(option2);

        popup.add(option3);

        popup.add(option4);

        myArea.setComponentPopupMenu(popup);

    }
}
