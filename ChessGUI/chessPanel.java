package ChessGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class chessPanel extends JPanel
{

    final int ROW = 8, COL = 8;

    public chessPanel()
    {
        JButton[][] chessBoard = new JButton[ROW][COL];

        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();

        JMenuBar menuBar = new JMenuBar();

        JMenuItem newGame = new JMenuItem();

        menuBar.add(newGame);

        upperPanel.add(menuBar);

        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(ROW, COL));

        for (int r = 0; r < ROW; r++)
        {
            for (int c = 0; c < COL; c++)
            {
                JButton square = new JButton();

                chessBoard[r][c] = square;

                centerPanel.add(square);
            }
        }

        add(upperPanel, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

    }
}
