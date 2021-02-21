package GUI;

import java.awt.GraphicsEnvironment;

import javax.swing.*;

public class Tries
{

    public static void main(String[] args)
    {
        String font = JOptionPane.showInputDialog("show font");
        
        boolean installfont = false;
        
        String [] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        
        for(String name : fontNames)
        {
            if(name.equalsIgnoreCase(font))
            {
                installfont = true;
            }
        }
        
        if(installfont)
        {
            System.out.println("font installed");
        }
        
        else
        {
            System.out.println("font not installed");
        }
    }
}
