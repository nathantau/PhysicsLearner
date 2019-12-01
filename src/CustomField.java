import javax.swing.*;
import java.awt.*;

public class CustomField extends JTextField{

    //Colour variable
    private static Color NORMAL_COLOR = new Color(50,50,50);

    //creates a custom button
    public CustomField (final String text) {
        super(text);
        setOpaque(true);
        setBackground(NORMAL_COLOR);
        setForeground(Color.WHITE);
        setText(text);
        setCaretColor(Color.WHITE);
    }
}
