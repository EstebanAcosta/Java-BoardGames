
package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.text.StyledEditorKit;

import com.sun.glass.events.KeyEvent;

public class WordProcessorII
{

    public static void main(String[] args)
    {
        wordProcessorFrameII wpf = new wordProcessorFrameII();

        wpf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        wpf.setVisible(true);

    }
}

class wordProcessorFrameII extends JFrame
{

    public wordProcessorFrameII()
    {
        setTitle("Slider Frame");

        setBounds(600, 300, 600, 350);

        add(new wordProcessorPanelII());

    }

}

class wordProcessorPanelII extends JPanel
{

    private JTextPane areaText;

    private JMenu font, style, size;

    private Font letters;

    private String[] fonts = { "Arial", "Verdana", "Times", "Courier", "Times New Roman" };

    private int[] sizes = { 22 };

    public wordProcessorPanelII()
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

        // JCheckBoxMenuItem bold = new JCheckBoxMenuItem("Bold");
        //
        // JCheckBoxMenuItem italic = new JCheckBoxMenuItem("Italic");
        //
        // bold.addActionListener(new StyledEditorKit.BoldAction());
        //
        // italic.addActionListener(new StyledEditorKit.ItalicAction());
        //
        // style.add(bold);
        //
        // style.add(italic);

        ////////////////////////////////////////////////////////////////////////////
        //
        // for (int s : sizes)
        // {
        // configureMenu(String.valueOf(s), "size", "", 9, s);
        // }

        ButtonGroup siz = new ButtonGroup();

        for (int s : sizes)
        {
            JRadioButtonMenuItem another = new JRadioButtonMenuItem(String.valueOf(s));

            another.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.SHIFT_DOWN_MASK));

            another.addActionListener(new StyledEditorKit.FontSizeAction("Change_Size", s));

            siz.add(another);

            size.add(another);
        }

        areaText = new JTextPane();

        menubar.add(font);

        menubar.add(style);

        menubar.add(size);

        menuPanel.add(menubar);

        add(menuPanel, BorderLayout.NORTH);

        add(areaText, BorderLayout.CENTER);

        JPopupMenu popup = new JPopupMenu();

        JMenuItem b = new JMenuItem("Bold");

        JMenuItem i = new JMenuItem("Italic");

        b.addActionListener(new StyledEditorKit.BoldAction());

        i.addActionListener(new StyledEditorKit.ItalicAction());

        popup.add(b);

        popup.add(i);

        areaText.setComponentPopupMenu(popup);
        
        JToolBar tool = new JToolBar();
        
        JButton boldButton = new JButton("Bold Button");
        
        JButton italicButton = new JButton("Italic Button");
        
        tool.add(boldButton);
        
        tool.add(italicButton);

    }

    public void configureMenu(String menuItemText, String menu, String fontText, int styleText, int sizeText)
    {
        JMenuItem elem_menu = new JMenuItem(menuItemText);

        if (menu.equalsIgnoreCase("font"))
        {
            font.add(elem_menu);

            elem_menu.addActionListener(new StyledEditorKit.FontFamilyAction("Change_Letter", fontText));
        }

        else if (menu.equalsIgnoreCase("style"))
        {
            style.add(elem_menu);

            if (styleText == Font.BOLD)
            {
                elem_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_DOWN_MASK));

                elem_menu.addActionListener(new StyledEditorKit.BoldAction());

            }
            else
            {
                elem_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.SHIFT_DOWN_MASK));

                elem_menu.addActionListener(new StyledEditorKit.ItalicAction());

            }

        }

        else if (menu.equalsIgnoreCase("size"))
        {
            size.add(elem_menu);

            elem_menu.addActionListener(new StyledEditorKit.FontSizeAction("Change_Size", sizeText));
        }

    }

}
