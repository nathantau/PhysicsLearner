import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
 * Created by ngj9136 on 12/11/2017.
 */
public class ProgressMenu extends JPanel{

    //variables
    private int rightDisplacement = 1;
    private int leftDisplacement = -1;
    private Mechanics mechanics = new Mechanics();

    private Font titleFont = new Font("TimesRoman", Font.BOLD, 130);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Color grey = new Color(50,50,50);
    private Color red = new Color(255, 0, 0);
    private Color blue = new Color(0, 0, 255);
    private Color green = new Color(0, 255, 0);

    //creates progress menu
    public ProgressMenu() {
        setLayout(null);
        add(new ExitButton().getExitButton());
        add(addReturnButton());

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
                rightDisplacement += 5;
                leftDisplacement -= 5;
            }
        }, 1, 10);
    }

    //add return button to previous section
    public CustomButton addReturnButton(){
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                Mechanics.frame.remove(Mechanics.progressMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.menuPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return returnButton;
    }

    //colour graphics for section
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,1920,1280);

        for (int i = 0; i < 11; i++) {
            g.setColor(red);
            g.fillOval(rightDisplacement, 100 * i, 50, 50);
            if (rightDisplacement > 1920) {
                rightDisplacement = 1;
            }
            g.setColor(green);
            g.fillOval(rightDisplacement * 2, 100 * i, 50, 50);
            if (rightDisplacement > 1920) {
                rightDisplacement = 1;
            }
            g.setColor(green);
            g.fillOval(rightDisplacement * 3, 100 * i, 50, 50);
            if (rightDisplacement > 1920) {
                rightDisplacement = 1;
            }
            g.setColor(blue);
            g.fillOval(leftDisplacement + 1920, 100 * i, 50, 50);
            if (leftDisplacement < -1920) {
                leftDisplacement = -1;
            }
            g.setColor(blue);
            g.fillOval(leftDisplacement * 4 + 1920, 100 * i, 50, 50);
            if (leftDisplacement < -1920) {
                leftDisplacement = -1;
            }
            g.setColor(green);
            g.fillOval(leftDisplacement * 5 + 1920, 100 * i, 50, 50);
            if (leftDisplacement < -1920) {
                leftDisplacement = -1;
            }
            g.setColor(red);
            g.fillOval(leftDisplacement * 10 + 1920, 100 * i, 50, 50);
            if (leftDisplacement < -1920) {
                leftDisplacement = -1;
            }
        }

        g.setColor(grey);
        g.fillRect(350, 250, 1000, 420);

        g.setColor(Color.white);
        g.drawRect(350, 250, 1000, 420);

        g.setFont(titleFont);
        g.drawString("PROGRESS", (int)(mechanics.getScreenWidth()/3.2), 150);
        
        //print out scores
        g.setFont(fieldFont);
        g.drawString ("Kinematics Score: " + IntroPanel.accounts.get(IntroPanel.accountNum-1).getKinematicsScore(), 400, 325);
        g.drawString ("Dynamics Score: " + IntroPanel.accounts.get(IntroPanel.accountNum-1).getDynamicsScore(), 400, 425);
        g.drawString ("Electricity Score: " + IntroPanel.accounts.get(IntroPanel.accountNum-1).getElectricityScore(), 400, 525);
        g.drawString ("Optics Score: " + IntroPanel.accounts.get(IntroPanel.accountNum-1).getOpticsScore(), 400, 625);
        
        drawCircles(g);
    
    }

    //creates circles for progress menu score
    public void drawCircles(Graphics g){
        int e = 900;
        g.setColor (Color.red);
        for (int b =0; b < IntroPanel.accounts.get(IntroPanel.accountNum-1).getKinematicsScore(); b++) {
            g.fillOval (e, 300, 30, 30);
            e = e + 50;
        }

        e = 900;
        g.setColor (Color.blue);
        for (int b =0; b < IntroPanel.accounts.get(IntroPanel.accountNum-1).getDynamicsScore(); b++) {
            g.fillOval (e, 400, 30, 30);
            e = e + 50;
        }

        e = 900;
        g.setColor (Color.green);
        for (int b =0; b < IntroPanel.accounts.get(IntroPanel.accountNum-1).getElectricityScore(); b++) {
            g.fillOval (e, 500, 30, 30);
            e = e + 50;
        }

        e = 900;
        g.setColor (Color.yellow);
        for (int b =0; b < IntroPanel.accounts.get(IntroPanel.accountNum-1).getOpticsScore(); b++) {
            g.fillOval (e, 600, 30, 30);
            e = e + 50;
        }

        //draw scoring circles
        //starting x-value
        e = 900;
        g.setColor(Color.white);
        for (int a = 0; a<8; a++) {
            g.drawOval (e, 300, 30, 30);
            g.drawOval (e, 400, 30, 30);
            g.drawOval (e, 500, 30, 30);
            g.drawOval (e, 600, 30, 30);
            e=e+50;
        }
    }
}