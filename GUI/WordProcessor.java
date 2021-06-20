package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class WordProcessor
{

    public static void main(String[] args)
    {
        wordProcessorFrame wpf = new wordProcessorFrame();

        wpf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        wpf.setVisible(true);

    }
}

class wordProcessorFrame extends JFrame
{

    public wordProcessorFrame()
    {
        setTitle("Slider Frame");

        setBounds(600, 300, 600, 350);

        add(new wordProcessorPanel());

    }

}

class wordProcessorPanel extends JPanel
{

    private JTextPane areaText;

    private JMenu font, style, size;

    private Font letters;

    private String[] fonts = { "Arial", "Verdana", "Times", "Courier", "Times New Roman" };

    private int[] sizes = { 12, 13, 14, 15, 16, 17 };

    public wordProcessorPanel()
    {
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();

        JMenuBar menubar = new JMenuBar();

        font = new JMenu("Font");

        style = new JMenu("Style");

        size = new JMenu("Size");

        for (String f : fonts)
        {
            configureMenu(f, "font", f, 9, 9);
        }

        ////////////////////////////////////////////////////////////////////////

        configureMenu("Bold", "style", "", Font.BOLD, 1);

        configureMenu("Italic", "style", "", Font.ITALIC, 1);

        ////////////////////////////////////////////////////////////////////////////

        for (int s : sizes)
        {
            configureMenu(String.valueOf(s), "size", "", 9, s);
        }

        areaText = new JTextPane();

        menubar.add(font);

        menubar.add(style);

        menubar.add(size);

        menuPanel.add(menubar);

        add(menuPanel, BorderLayout.NORTH);

        add(areaText, BorderLayout.CENTER);

    }

    public void configureMenu(String menuItemText, String menu, String fontText, int styleText, int sizeText)
    {
        JMenuItem elem_menu = new JMenuItem(menuItemText);

        if (menu.equalsIgnoreCase("font"))
        {
            font.add(elem_menu);
        }

        else if (menu.equalsIgnoreCase("style"))
        {
            style.add(elem_menu);
        }

        else if (menu.equalsIgnoreCase("size"))
        {
            size.add(elem_menu);
        }

        elem_menu.addActionListener(new manageEvents(menuItemText, fontText, styleText, sizeText));

    }

    private class manageEvents implements ActionListener
    {
        String fontText, menuItem;

        int textStyle, textSize;

        public manageEvents(String menuItem, String fontText, int textStyle, int textSize)
        {
            this.fontText = fontText;

            this.textStyle = textStyle;

            this.textSize = textSize;

            this.menuItem = menuItem;

        }

        public void actionPerformed(ActionEvent e)
        {
            
            letters = areaText.getFont();

            if (menuItem.matches("[0-9]+"))
            {
                textStyle = letters.getStyle();

                fontText = letters.getFontName();
            }

            else if (menuItem == "Italic" || menuItem == "Bold")
            {

                if (letters.getStyle() == Font.BOLD || letters.getStyle() == Font.ITALIC)
                {
                    textStyle = 3;
                }
                fontText = letters.getFontName();

                textSize = letters.getSize();
            }

            else
            {
                for (String s : fonts)
                {
                    if (s.equalsIgnoreCase(menuItem))
                    {
                        textStyle = letters.getStyle();

                        textSize = letters.getSize();
                    }
                }
            }

            areaText.setFont(new Font(fontText, textStyle, textSize));
        }

    }

}
