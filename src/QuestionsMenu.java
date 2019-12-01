import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
 * Created by ngj9136 on 12/11/2017.
 */
public class QuestionsMenu extends JPanel {

    //variables
    private int rightDisplacement = 1;
    private int leftDisplacement = -1;
    private Mechanics mechanics = new Mechanics();

    //fonts and colours
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 130);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Color red = new Color(255, 0, 0);
    private Color blue = new Color(0, 0, 255);
    private Color green = new Color(0, 255, 0);

    //create menu for questions
    public QuestionsMenu() {
        setLayout(null);
        add(new ExitButton().getExitButton());
        add(addReturnButton());
        add(addKinematicsButton());
        add(addDynamicsButton());
        add(addElectricityButton());
        add(addOpticsButton());

        //timers
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

    //add button to return to previous section
    public CustomButton addReturnButton(){
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                Mechanics.frame.remove(Mechanics.questionsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.menuPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return returnButton;
    }

    //add button for kinematics section
    public CustomButton addKinematicsButton(){
        CustomButton kinematicsButton = new CustomButton("KINEMATICS");
        kinematicsButton.setFont(fieldFont);
        kinematicsButton.setBounds((int)(mechanics.getScreenWidth()/2.7), 300, 500, 100);
        kinematicsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                Mechanics.frame.remove(Mechanics.questionsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.questionsKinematics);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return kinematicsButton;
    }

    //add button for dynamics questions
    public CustomButton addDynamicsButton(){
        CustomButton dynamicsButton = new CustomButton("DYNAMICS");
        dynamicsButton.setFont(fieldFont);
        dynamicsButton.setBounds((int)(mechanics.getScreenWidth()/2.7), 400, 500, 100);
        dynamicsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                Mechanics.frame.remove(Mechanics.questionsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.questionsDynamics);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return dynamicsButton;
    }

    //add button for electricity questions
    public CustomButton addElectricityButton(){
        CustomButton electricityButton = new CustomButton("ELECTRICITY");
        electricityButton.setFont(fieldFont);
        electricityButton.setBounds((int)(mechanics.getScreenWidth()/2.7), 500, 500, 100);
        electricityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                Mechanics.frame.remove(Mechanics.questionsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.questionsElectricity);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return electricityButton;
    }

    //add button for optics questions
    public CustomButton addOpticsButton(){
        CustomButton opticsButton = new CustomButton("OPTICS");
        opticsButton.setFont(fieldFont);
        opticsButton.setBounds((int)(mechanics.getScreenWidth()/2.7), 600, 500, 100);
        opticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                Mechanics.frame.remove(Mechanics.questionsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.questionsOptics);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return opticsButton;
    }

    //colour graphics for section
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,1920,1280);

        //draw the background
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

        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("QUESTIONS", (int)(mechanics.getScreenWidth()/3.2), 150);

        g.setColor(new Color(50,50,50));
        g.fillRect((int)(mechanics.getScreenWidth()/2.7)-10, 290, 520, 430);
    }
}