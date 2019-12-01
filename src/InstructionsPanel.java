import javax.swing.*;
import java.awt.*;

public class InstructionsPanel  extends JPanel{

    //Variables
    private Color grey = new Color(50, 50, 50);
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 80);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 20);
    private Font bigFieldFont = new Font("TimesRoman", Font.BOLD, 35);
    private Font questionFont = new Font("TimesRoman", Font.BOLD, 60);
    private Font italicFont = new Font("TimesRoman", Font.ITALIC, 30);

    //Constructor
    public InstructionsPanel(){
        setBackground(Color.black);
    }

    //paint method
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("Welcome", 50, 100);
        g.setFont(fieldFont);

        //Draw text for instructions
        g.drawString("The purpose of this program is to aid students in learning physics concepts.",50,150);
        g.drawString("Students can use visual simulations, work on practice problems, and track their progress.",50,180);
        g.drawString("Contents covered in this program are divided into the sections of:",50,210);
        g.setFont(italicFont);
        g.drawString("Kinematics, Dynamics, Electric Circuits, and Refraction.", 50, 250);

        g.setColor(grey);
        g.fillRect(30, 300, 930, 180);

        g.setColor(Color.white);
        g.drawRect(30, 300, 930, 180);

        g.setFont(bigFieldFont);
        g.drawString("PROGRESS:  Tracks your progress in questions", 50, 350);
        g.drawString("TUTORIAL:  Learn physics skills in interactive tutorials", 50, 400);
        g.drawString("QUESTIONS:  Do questions to practice your skills", 50, 450);

        g.setColor(Color.gray);
        g.fillRect(50, 500, 100, 100);
        g.setColor(Color.white);

        g.setFont(questionFont);
        g.drawString("?", 80, 570);

        g.setFont(fieldFont);
        g.drawString("<-- Click on these buttons in the tutorials to guide you through the program.", 200, 550);
    }

}
