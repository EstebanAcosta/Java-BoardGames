package NBAGUI;

import javax.swing.JFrame;

public class NBA_Stats
{
    public static void main(String[] args)
    {
        NBA_StatsFrame ns = new NBA_StatsFrame();
    }
}

class NBA_StatsFrame extends JFrame
{
    public NBA_StatsFrame()
    {
        setTitle("NBA Stats");

        add(new NBA_StatsPanel());

        pack();

        setLocation(500, 200);
        
        setSize(500, 500);

        setResizable(false);

        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
