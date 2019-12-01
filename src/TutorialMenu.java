import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by ngj9136 on 12/11/2017.
 */
public class TutorialMenu extends JPanel {

    //variables
    private int rightDisplacement = 1;
    private int leftDisplacement = -1;
    private Mechanics mechanics = new Mechanics();

    //fonts
    private Font titleFont = new Font("SansSerif", Font.PLAIN, 130);
    private Font unitFont = new Font("SansSerif", Font.PLAIN, 50);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Color grey = new Color(50, 50, 50);
    private Color red = new Color(255, 0, 0);
    private Color blue = new Color(0, 0, 255);
    private Color green = new Color(0, 255, 0);

    //add buttons for menu in tutorial section
    public TutorialMenu() {
        //set up menu
        setLayout(null);
        setFocusable(true);
        add(new ExitButton().getExitButton());
        add(addReturnButton());
        addAllButtons();
        //timer for the background
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

    //add the buttons for the tutorial menu
    public void addAllButtons(){
        //one D button
        CustomButton oneDPanel = new CustomButton("1-D MOTION");
        oneDPanel.setFont(fieldFont);
        oneDPanel.setBounds((int)(mechanics.getScreenWidth()/20), 340, 300, 80);
        oneDPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mechanics.frame.remove(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.oneDMotionPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });
        add(oneDPanel);

        //two d button
        CustomButton twoDPanel = new CustomButton("2-D MOTION");
        twoDPanel.setFont(fieldFont);
        twoDPanel.setBounds((int)(mechanics.getScreenWidth()/20), 440, 300, 80);
        twoDPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mechanics.frame.remove(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.twoDMotionPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });
        add(twoDPanel);

        //projectile motion button
        CustomButton projectilePanel = new CustomButton("PROJECTILE");
        projectilePanel.setFont(fieldFont);
        projectilePanel.setBounds((int)(mechanics.getScreenWidth()/20), 540, 300, 80);
        projectilePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mechanics.frame.remove(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.projectileMotionPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });
        add(projectilePanel);

        //dynamics button
        CustomButton dynamicsPanel = new CustomButton("DYNAMICS");
        dynamicsPanel.setFont(fieldFont);
        dynamicsPanel.setBounds((int)(mechanics.getScreenWidth()/3.4), 340, 300, 80);
        dynamicsPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mechanics.frame.remove(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.dynamicsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });
        add(dynamicsPanel);

        // circuit panel button
        CustomButton circuitPanel = new CustomButton("CIRCUITS");
        circuitPanel.setFont(fieldFont);
        circuitPanel.setBounds((int)(mechanics.getScreenWidth()/1.8), 340, 300, 80);
        circuitPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mechanics.frame.remove(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.circuitsPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });
        add(circuitPanel);

        //snells panel button
        CustomButton snellsPanel = new CustomButton("REFRACTION");
        snellsPanel.setFont(fieldFont);
        snellsPanel.setBounds((int)(mechanics.getScreenWidth()/1.25), 340, 300, 80);
        snellsPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mechanics.frame.remove(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.snellsPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });
        add(snellsPanel);
    }

    //add button to return to previous section
    public CustomButton addReturnButton(){
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Mechanics.frame.remove(Mechanics.tutorialMenu);
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

        g.setColor(grey);
        g.fillRect(mechanics.getScreenWidth()/20-10, 330, 320, 300);
        g.fillRect((int)(mechanics.getScreenWidth()/3.4)-10, 330, 320, 300);
        g.fillRect((int)(mechanics.getScreenWidth()/1.8)-10, 330, 320, 300);
        g.fillRect((int)(mechanics.getScreenWidth()/1.25)-10, 330, 320, 300);

        //draw titles
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("TUTORIALS", (int)(mechanics.getScreenWidth()/3.2), 150);
        g.setFont(unitFont);
        g.drawString("KINEMATICS", (int)(mechanics.getScreenWidth()/18.5), 300);
        g.drawString("DYNAMICS", (int)(mechanics.getScreenWidth()/3.3), 300);
        g.drawString("ELECTRICITY", (int)(mechanics.getScreenWidth()/1.8), 300);
        g.drawString("OPTICS", (int)(mechanics.getScreenWidth()/1.2), 300);

    }

}