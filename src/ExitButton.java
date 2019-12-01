import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton {

    //Variables
    private CustomButton exitButton;
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);

    //Constructor
    public ExitButton() {
        exitButton = new CustomButton("EXIT");
        exitButton.setFont(fieldFont);
        exitButton.setBounds(20, 20, 200, 40);
        exitButton.addActionListener(new ActionListener() {
            @Override
            //Exit program when clicked
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

    }

    //Get Method
    public CustomButton getExitButton() {
        return exitButton;
    }
}
