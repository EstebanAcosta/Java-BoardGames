package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PlayingWithJMenu
{

    public static void main(String[] args)
    {
        menuFrame sf = new menuFrame();

        sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sf.setVisible(true);

    }
}

class menuFrame extends JFrame
{

    public menuFrame()
    {
        setTitle("Menu Frame");

        setBounds(600, 300, 600, 350);

        add(new menuPanel());

    }

}

class menuPanel extends JPanel
{
    public menuPanel()
    {
        JMenuBar mybar = new JMenuBar();

        JMenu archive = new JMenu("Archive");

        JMenu edition = new JMenu("Edition");

        JMenu tools = new JMenu("Tools");

        JMenu options = new JMenu("Options");

        JMenuItem save = new JMenuItem("Save");

        JMenuItem save_as = new JMenuItem("Save As");

        JMenuItem cut = new JMenuItem("Cut");

        JMenuItem copy = new JMenuItem("Copy");

        JMenuItem paste = new JMenuItem("Paste");

        JMenuItem general = new JMenuItem("General");

        JMenuItem option1 = new JMenuItem("option 1");

        JMenuItem option2 = new JMenuItem("option 2");

        options.add(option1);

        options.add(option2);

        archive.add(save);

        archive.add(save_as);

        edition.add(cut);

        edition.add(copy);

        edition.add(paste);

        edition.addSeparator();

        edition.add(options);

        tools.add(general);

        mybar.add(archive);

        mybar.add(edition);

        mybar.add(tools);

        add(mybar);

    }
}
