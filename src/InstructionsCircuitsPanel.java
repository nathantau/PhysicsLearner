import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class InstructionsCircuitsPanel extends JPanel {

    //Variables
    private Color grey = new Color(50, 50, 50);
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 60);
    private Font equationFont = new Font("TimesRoman", Font.ITALIC, 35);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 20);

    //Electrion varaible
    private KinematicsBall electron1 = new KinematicsBall(0, 30, 1340, 340);

    //Constructor
    public InstructionsCircuitsPanel() {
        setBackground(Color.black);

        //Timer
        java.util.Timer timer = new java.util.Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //Set the election in circuit
                if (electron1.getxCoordinate() == 1340 && electron1.getyCoordinate() < 840) {
                    electron1.setyCoordinate(electron1.getyCoordinate() + 1);
                } else if (electron1.getyCoordinate() == 840 && electron1.getxCoordinate() < 1840) {
                    electron1.setxCoordinate(electron1.getxCoordinate() + 1);
                } else if (electron1.getxCoordinate() == 1840 && electron1.getyCoordinate() > 340) {
                    electron1.setyCoordinate(electron1.getyCoordinate() - 1);
                } else {
                    electron1.setxCoordinate(electron1.getxCoordinate() - 1);
                }
                repaint();
            }
        }, 0, 1);
    }

    //Paint Component
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1280);

        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("Circuits", 50, 100);

        g.setColor(grey);
        g.fillRect(30, 150, 1250, 200);
        g.fillRect(30, 375, 1250, 250);
        g.fillRect(30, 650, 1250, 150);
        g.fillRect(30, 830, 1250, 130);
        g.setColor(Color.white);
        g.drawRect(30, 150, 1250, 200);
        g.drawRect(30, 375, 1250, 250);
        g.drawRect(30, 650, 1250, 150);
        g.drawRect(30, 830, 1250, 130);

        //Draw the text
        g.setColor(Color.white);
        g.setFont(equationFont);
        g.drawString("Series Circuit", 50, 200);
        g.setFont(fieldFont);
        g.drawString("In a series circuit, there is only one path for the current to travel through.", 50, 230);
        g.drawString("Therefore, the current at every location in the circuit is the same.", 50, 260);
        g.drawString("However, the voltage across different resistors are different, they are governed by V = IR.", 50, 290);
        g.drawString("The total resistance of a series circuit is equal to the sum of the resistances of its resistors.", 50, 320);
        g.setFont(equationFont);
        g.drawString("Parallel Circuit", 50, 420);
        g.setFont(fieldFont);
        g.drawString("In a parallel circuit, there is more than one path for the current to travel through.", 50, 450);
        g.drawString("The current at every location in the circuit is not the same, it's calculated by I = V/R for the resistor in question.", 50, 480);
        g.drawString("No matter which path the current chooses to go through, the voltage gained by it travelling through the battery is lost as it", 50, 510);
        g.drawString("travels through the resistors on that path.", 50, 540);
        g.drawString("Therefore, the voltage across resistors in parallel are all equal to the battery voltage.", 50, 570);
        g.drawString("The total resistance of a parallel circuit follows the relation 1/Rtotal = 1/R1+1/R2+...", 50, 600);

        g.setFont(equationFont);
        g.drawString("Instructions:", 50, 700);

        g.setFont(fieldFont);
        g.drawString("Change up the resistances of the resistors and the battery voltage to observe what happens to the other values.", 50, 730);
        g.drawString("You can verify that the equations work using the values displayed.", 50, 760);

        g.setFont(equationFont);
        g.drawString("Equations:", 50, 870);
        g.drawString("V=IR", 50, 930);
        g.drawString("1/Rtotal = 1/R1 + 1/R2 + ...", 300, 930);

        drawCircuit(g);
    }

    //Draw the Circuit method
    public void drawCircuit(Graphics g) {

        g.setColor(Color.white);

        g.drawLine(1350, 350, 1850, 350);
        g.drawLine(1350, 350, 1350, 850);
        g.drawLine(1350, 850, 1850, 850);
        g.drawLine(1850, 350, 1850, 850);
        g.drawLine(1300, 600, 1400, 600);
        g.drawLine(1325, 610, 1375, 610);

        g.setColor(Color.black);
        g.fillRect(1340, 601, 20, 9);
        g.fillRect(1550, 350, 100, 1);
        g.fillRect(1550, 850, 100, 1);

        g.setColor(Color.white);
        g.drawRect(1550, 340, 100, 20);
        g.drawRect(1550, 840, 100, 20);

        //Draw the electron
        g.setColor(Color.yellow);
        g.fillOval((int) electron1.getxCoordinate(), (int) electron1.getyCoordinate(), 20, 20);

    }
}
