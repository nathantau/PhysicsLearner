import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class InstructionsTwoDMotionPanel extends JPanel {

    //variables
    private Color grey = new Color(50, 50, 50);
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 60);
    private Font equationFont = new Font("TimesRoman", Font.ITALIC, 35);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 20);
    private KinematicsBall kinematicsBall = new KinematicsBall(0, 30, 1300, 750);

    //Constructor
    public InstructionsTwoDMotionPanel(){
        setBackground(Color.black);
        kinematicsBall.setvX(2);
        kinematicsBall.setvY(-2);

        //timer to draw kinematics ball
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(kinematicsBall.getxCoordinate()<1600) {
                    kinematicsBall.setxCoordinate(kinematicsBall.getxCoordinate() + kinematicsBall.getvX());
                    kinematicsBall.setyCoordinate(kinematicsBall.getyCoordinate() + kinematicsBall.getvY());
                    repaint();
                }
                else{
                    kinematicsBall.setxCoordinate(1300);
                    kinematicsBall.setyCoordinate(750);

                    repaint();
                }
            }
        }, 100, 30);
    }

    //paint component
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1280);

        g.setColor(Color.white);

        //draw text
        g.setFont(titleFont);
        g.drawString("Two D Motion", 50, 100);
        g.setFont(fieldFont);
        g.drawString("In 2-D motion, the components of the object’s velocity and/or acceleration occur in ",50,150);
        g.drawString("both the x and the y direction. In this case, the object’s net velocity is the vector", 50, 180);
        g.drawString("sum of its velocity components in the x and y directions.",50,210);
        g.drawString("The same applies to its net acceleration.",50,240);
        g.drawString("When encountered with multiple diagonal vector quantities, it is advised that you first break",50,270);
        g.drawString("them down into their horizontal and vertical components, calculate the total velocity/acceleration",50,300);
        g.drawString("quantity in the x and y directions separately, then compute the vector sum using Pythagorean Theorem.", 50, 330);
        g.drawString("The same kinematics equations in 1-D motion apply.", 50, 360);

        //draw background
        g.setColor(grey);
        g.fillRect(30, 390, 900, 160);
        g.fillRect(30, 570, 900, 130);
        g.fillRect(30, 720, 900, 160);
        g.setColor(Color.white);
        g.drawRect(30, 390, 900, 160);
        g.drawRect(30, 570, 900, 130);
        g.drawRect(30, 720, 900, 160);

        //draw instructions text
        g.setFont(equationFont);
        g.drawString("Questions:", 50, 430);
        g.setFont(fieldFont);
        g.drawString("What happens to the x-distance when you add 1 to the x-velocity?", 50, 470);
        g.drawString("What happens to the y-distance when you add 1 to the y-velocity?", 50, 500);
        g.drawString("What happens to the x and y distances when you add to both velocities?", 50, 530);

        g.setFont(equationFont);
        g.drawString("Equations: ", 50, 610);
        g.drawString("d = vt", 50, 650);
        g.drawString("d = v0t + 0.5at²", 50, 680);

        g.drawString("Instructions:", 50, 760);
        g.setFont(fieldFont);
        g.drawString("Use the buttons to change the x-velocity and y-velocity", 50, 800);
        g.drawString("Click Reset to reset all values and click Launch to launch projectile", 50, 830);
        g.drawString("Examine what happens during the launch.", 50, 860);

        //draw ball
        g.fillOval((int) kinematicsBall.getxCoordinate(), (int) kinematicsBall.getyCoordinate() - 50, 50, 50);
        g.drawRect(1300, 300, 350, 500);
    }

}
