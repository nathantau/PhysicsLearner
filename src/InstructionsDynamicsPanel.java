import javax.swing.*;
import java.awt.*;

public class InstructionsDynamicsPanel extends JPanel {

    //Variables
    private Color grey = new Color(50, 50, 50);
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 60);
    private Font equationFont = new Font("TimesRoman", Font.ITALIC, 35);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 20);

    //Constructor
    public InstructionsDynamicsPanel() {
        setBackground(Color.black);
    }

    //colour/graphics method (displays all the instructions)
    public void paintComponent(Graphics g) {
        g.setFont(titleFont);
        g.setColor(Color.white);
        g.drawString("Dynamics", 50, 100);

        g.setColor(grey);
        g.fillRect(30, 120, 1200, 200);
        g.fillRect(30, 330, 900, 150);
        g.fillRect(30, 500, 900, 250);
        g.setColor(Color.white);
        g.drawRect(30, 120, 1200, 200);
        g.drawRect(30, 330, 900, 150);
        g.drawRect(30, 500, 900, 250);

        //Draw the text of the panel
        g.setFont(fieldFont);
        g.drawString("For an object to have an acceleration, there must be a net force being applied.", 50, 150);
        g.drawString("Newton’s second law states that F = ma, meaning that acceleration is directly proportional to the force.", 50, 180);
        g.drawString("Another force to be considered is friction force, which will oppose the object’s direction of motion", 50, 210);
        g.drawString("This is calculated using the formula Ff = uFn, where Ff is friction force, u is the coefficient of friction", 50, 240);
        g.drawString("(a number to demonstrate relationship between Ff and Fn), and Fn is the normal force.", 50, 270);
        g.drawString("On a flat surface, such in this demonstration, the magnitude of Fn is equal to the magnitude of Fg, the weight of the object.", 50, 300);

        g.setFont(equationFont);
        g.drawString("Equations: ", 50, 370);
        g.drawString("F = ma", 50, 420);
        g.drawString("Ff = uFn", 50, 460);

        g.drawString("Instructions", 50, 540);
        g.setFont(fieldFont);
        g.drawString("Change values by clicking their corresponding buttons on the left side of the screen.", 50, 590);
        g.drawString("Click Launch to launch a ball. Click reset to reset all variables.", 50, 620);
        g.drawString("The top three buttons refer to the force being applied.", 50, 650);
        g.drawString("The next two buttons refer to the mass of the object.", 50, 680);
        g.drawString("The last two buttons refer to the coefficient of friction between the surface and the object.", 50, 710);

    }

}
