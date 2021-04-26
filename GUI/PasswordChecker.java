package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class PasswordChecker
{
    public static void main(String[] args)
    {
        framePassword fp = new framePassword();

        fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class framePassword extends JFrame
{

    public framePassword()
    {
        setTitle("Password Checker");

        setBounds(600, 300, 600, 350);

        passwordPanel dp = new passwordPanel();

        add(dp);

        setVisible(true);
    }

}

class passwordPanel extends JPanel
{
    JPasswordField password;

    public passwordPanel()
    {
        setLayout(new BorderLayout());

        JPanel top_panel = new JPanel();

        top_panel.setLayout(new GridLayout(2, 2));

        add(top_panel, BorderLayout.NORTH);

        JLabel label1 = new JLabel("User");

        JLabel label2 = new JLabel("Password");

        JTextField user = new JTextField(15);

        password = new JPasswordField(15);
        
        password.getDocument().addDocumentListener(new confirm_password());

        top_panel.add(label1);

        top_panel.add(user);

        top_panel.add(label2);

        top_panel.add(password);

        JButton send = new JButton("Send");

        add(send, BorderLayout.SOUTH);

    }

    private class confirm_password implements DocumentListener
    {

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            char[] passw;

            passw = password.getPassword();

            if (passw.length < 8 || passw.length > 12)
            {
                password.setBackground(Color.red);
            }

            else
            {
                password.setBackground(Color.white);

            }
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            // TODO Auto-generated method stub

        }

    }

}