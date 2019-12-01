import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by aun4606 on 12/11/2017.
 */
public class CustomButton extends JButton {

    //Colours
    private Color pressedColor = Color.GREEN;
    private Color rolloverColor = Color.RED;
    private Color normalColor = Color.BLUE;

    //creates a custom button
    public CustomButton (String text) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(normalColor);
        setForeground(Color.WHITE);
        setText(text);

        addChangeListener(new ChangeListener() {
            
            //creates states for the button
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressedColor);
                } else if (getModel().isRollover()) {
                    setBackground(rolloverColor);
                } else {
                    setBackground(normalColor);
                }
            }
        });

    }

    //Set colour method
    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }
}