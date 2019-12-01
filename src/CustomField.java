import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static com.sun.java.accessibility.util.SwingEventMonitor.addChangeListener;

public class CustomField extends JTextField{

    //Colour variable
    private Color normalColor = new Color(50,50,50);

    //creates a custom button
    public CustomField (String text) {
        super(text);

        setOpaque(true);
        setBackground(normalColor);
        setForeground(Color.WHITE);
        setText(text);
        setCaretColor(Color.WHITE);
    }

}
