import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class InstructionsProjectilePanel extends JPanel {

    //Variables
    private Color grey = new Color(50, 50, 50);
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 60);
    private Font equationFont = new Font("TimesRoman", Font.ITALIC, 35);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 20);
    private double kinematicsBallAcceleration = 2;
    private KinematicsBall kinematicsBall = new KinematicsBall(0, 30, 1300, 700);

    //constructor
    public InstructionsProjectilePanel() {
        setBackground(Color.black);
        kinematicsBall.setvX(5);
        kinematicsBall.setvY(-40);

        //Timer which draws the kinematics ball
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            if(kinematicsBall.getyCoordinate()<= 700) {
                kinematicsBall.setxCoordinate(kinematicsBall.getxCoordinate() + kinematicsBall.getvX());
                kinematicsBall.setyCoordinate(kinematicsBall.getyCoordinate() + kinematicsBall.getvY());
                kinematicsBallAcceleration = 2;
                kinematicsBall.setvY(kinematicsBallAcceleration + kinematicsBall.getvY());
                repaint();
            }
            else{
                kinematicsBall.setxCoordinate(1300);
                kinematicsBall.setyCoordinate(700);
                kinematicsBall.setvY(-40);
            }

            }


        }, 1, 30);
    }

    //Paint method
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1280);
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("Projectile Motion", 50, 100);
        g.setFont(fieldFont);

        //Draws the text
        g.drawString("Projectile motion occurs when an object is launched diagonally with a velocity component in both the x and y directions.", 50, 150);
        g.drawString("Once the object is launched, it is essentially in free-fall as the only acceleration it experiences is the acceleration", 50, 180);
        g.drawString("due to gravity (9.8 m/s² [down]).", 50, 210);
        g.drawString("Take note that the object reaches its maximum height when the vertical component of its velocity is 0.", 50, 240);
        g.drawString("Also take note that when the object hits the ground, the vertical component of its velocity is exactly the opposite to", 50, 270);
        g.drawString("the initial vertical velocity component.", 50, 300);

        //Draws the background
        g.setColor(grey);
        g.fillRect(30, 340, 900, 190);
        g.fillRect(30, 550, 900, 130);
        g.setColor(Color.white);
        g.drawRect(30, 340, 900, 190);
        g.drawRect(30, 550, 900, 130);

        //Draws additional text
        g.setFont(equationFont);
        g.drawString("Instructions:", 50, 380);
        g.setFont(fieldFont);
        g.drawString("Using the buttons on the side panel, change the x-velocity and y-velocity", 50, 410);
        g.drawString("Note there is a minimum and maximum setting.", 50, 440);
        g.drawString("Click Launch to launch a ball. Click reset to reset all variables.", 50, 470);
        g.drawString("Examine what happens to the dots during the launch.", 50, 500);

        g.setFont(equationFont);
        g.drawString("Equations: ", 50, 590);
        g.drawString("d = vt", 50, 630);
        g.drawString("d = v0t + 0.5at²", 50, 660);

        //Draw the ball
        g.fillOval((int)kinematicsBall.getxCoordinate(), (int)kinematicsBall.getyCoordinate(), 50,50);
        g.drawLine(1300, 800, 1600, 800);

    }

}
