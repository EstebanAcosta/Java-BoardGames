package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class PlayingWithDocuments
{
    public static void main(String[] args)
    {
        frameDocument fd = new frameDocument();

        fd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class frameDocument extends JFrame
{

    public frameDocument()
    {
        setTitle("Text Frame");

        setBounds(600, 300, 600, 350);

        documentPanel dp = new documentPanel();

        add(dp);

        setVisible(true);
    }

}

class documentPanel extends JPanel
{
    public documentPanel()
    {
        JTextField myField = new JTextField(20);
        
        Document myDoc = myField.getDocument();
        
        listenText event = new listenText();
        
        myDoc.addDocumentListener(event);

        add(myField);
    }

    private class listenText implements DocumentListener
    {

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            System.out.println("Inserted Text");
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {

            System.out.println("Erased Text");
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            // TODO Auto-generated method stub

        }

    }
}
