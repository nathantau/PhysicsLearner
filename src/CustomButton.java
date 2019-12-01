import javax.swing.*;
import java.awt.*;

/**
 * Created by aun4606 on 12/11/2017.
 */
public class CustomButton extends JButton {

    // Colours
    private Color PRESSED_COLOR = Color.GREEN;
    private Color ROLLOVER_COLOR = Color.RED;
    private Color normalColor = Color.BLUE;

    // Creates a custom button
    public CustomButton (final String text) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(normalColor);
        setForeground(Color.WHITE);
        setText(text);

        // Creates states for the button
        addChangeListener(evt -> {
            if (getModel().isPressed()) {
                setBackground(PRESSED_COLOR);
            } else if (getModel().isRollover()) {
                setBackground(ROLLOVER_COLOR);
            } else {
                setBackground(normalColor);
            }
        });
    }

    // Set colour method
    public void setNormalColor(final Color normalColor) {
        this.normalColor = normalColor;
    }
}