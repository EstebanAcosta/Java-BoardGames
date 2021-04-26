package GUI_Chess;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI_Chess
{
    public static void main(String[] args)
    {
        chess chess = new chess();
        
    }
}

class chess extends JFrame
{
    public chess()
    {
        setTitle("Chess");
        
        setVisible(true);
        
        setBounds(400,200,700,500);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(new chessPanel());
    }
}

class chessPanel extends JPanel
{
    public chessPanel()
    {
        
    }
}
