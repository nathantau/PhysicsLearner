
import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class InstructionsOneDMotionPanel extends JPanel{

    //Variables
    private Color grey = new Color(50, 50, 50);
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 60);
    private Font equationFont = new Font("TimesRoman", Font.ITALIC, 35);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 20);
    private KinematicsBall kinematicsBall = new KinematicsBall(0, 30, 100, 950);

    //Constructor
    public InstructionsOneDMotionPanel(){
        setBackground(Color.black);
        kinematicsBall.setaX(1);

        //Timer
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(kinematicsBall.getxCoordinate()<1600) {
                    kinematicsBall.setxCoordinate(kinematicsBall.getxCoordinate() + kinematicsBall.getvX());
                    kinematicsBall.setvX(kinematicsBall.getvX() + kinematicsBall.getaX());
                    repaint();
                }
                else{
                    kinematicsBall.setxCoordinate(100);
                    kinematicsBall.setvX(0);
                    repaint();
                }
            }
        }, 100, 30);
    }

    //Paint Component
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1280);

        g.setFont(titleFont);
        g.setColor(Color.white);
        g.drawString("One D Motion", 50, 100);

        //Draw the backgrounds
        g.setColor(grey);
        g.fillRect(30, 260, 900, 150);
        g.fillRect(30, 430, 900, 150);
        g.fillRect(30, 600, 900, 170);
        g.setColor(Color.white);
        g.drawRect(30, 260, 900, 150);
        g.drawRect(30, 430, 900, 150);
        g.drawRect(30, 600, 900, 170);

        //Draw the text for instructions
        g.setFont(fieldFont);
        g.drawString("In 1-D motion, the object only has velocity and/or acceleration in one direction", 50, 150);
        g.drawString("(x-direction in this case). The equation d = vt is used to calculate displacement", 50, 180);
        g.drawString("occurring at constant velocity, while the equation d = v0t + 0.5at² is used to calculate", 50, 210);
        g.drawString("the displacement of an object with an initial velocity of v0 and an acceleration of a. ", 50, 240);

        g.setFont(equationFont);
        g.drawString("Questions:", 50, 300);
        g.setFont(fieldFont);
        g.drawString("What happens to the dots when you set acceleration to 1?", 50, 330);
        g.drawString("What happens to the dots when you set velocity to 1?", 50, 360);
        g.drawString("What happens when you continue increasing velocity and acceleration?", 50, 390);

        g.setFont(equationFont);
        g.drawString("Equations: ", 50, 470);
        g.drawString("d = vt", 50, 520);
        g.drawString("d = v0t + 0.5at²", 50, 560);

        g.drawString("Instructions", 50, 640);
        g.setFont(fieldFont);
        g.drawString("Change velocity and acceleration by clicking + and - buttons.", 50, 690);
        g.drawString("Click Launch to launch a ball. Click reset to reset all variables.", 50, 720);
        g.drawString("Examine what happens during the launch.", 50, 750);

        g.drawLine(100, 950, 1600, 950);

        g.fillOval((int) kinematicsBall.getxCoordinate(), (int) kinematicsBall.getyCoordinate() - 50, 50, 50);

    }

}
