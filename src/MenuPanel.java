import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by aun4606 on 12/7/2017.
 */
public class MenuPanel extends JPanel {

    //Variables
    private int rightDisplacement = 1;
    private int leftDisplacement = -1;
    private Mechanics mechanics = new Mechanics();

    //Colors and fonts
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 130);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Font timeFont = new Font("TimesRoman", Font.BOLD, 60);
    private Color grey = new Color(50, 50, 50);
    private Color red = new Color(255, 0, 0);
    private Color blue = new Color(0, 0, 255);
    private Color green = new Color(0, 255, 0);

    //creates menu panel
    public MenuPanel(){
        setLayout(null);
        add(new ExitButton().getExitButton());
        add(addReturnButton());
        add(addProgressButton());
        add(addTutorialButton());
        add(addQuestionsButton());
        addInstructionsButton();

        //Timer
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

    //Add the instructions button
    public void addInstructionsButton(){
        CustomButton instructionsButton = new CustomButton("?");
        instructionsButton.setFont(timeFont);
        instructionsButton.setBounds(250, 25, 100, 100);
        instructionsButton.setBackground(Color.gray);
        instructionsButton.setNormalColor(Color.gray);
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InstructionsFrame(0);
            }
        });
        add(instructionsButton);
    }

    //adds a return button for section
    public CustomButton addReturnButton(){
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {

            //override for what the button does
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                Mechanics.frame.remove(Mechanics.menuPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.introPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return returnButton;
    }

    //add a button for progress
    public CustomButton addProgressButton(){
        CustomButton progressButton = new CustomButton("PROGRESS");
        progressButton.setFont(fieldFont);
        progressButton.setBounds((int)(mechanics.getScreenWidth()/2.7), 300, 500, 100);
        progressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mechanics.frame.remove(Mechanics.menuPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.progressMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return progressButton;
    }

    //add tutorial button for section
    public CustomButton addTutorialButton(){
        CustomButton tutorialButton = new CustomButton("TUTORIALS");
        tutorialButton.setFont(fieldFont);
        tutorialButton.setBounds((int)(mechanics.getScreenWidth()/2.7), 400, 500, 100);
        tutorialButton.addActionListener(new ActionListener() {

            //add button to override button function
            @Override
            public void actionPerformed(ActionEvent e) {

                Mechanics.frame.remove(Mechanics.menuPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return tutorialButton;
    }

    //add button for questions in section
    public CustomButton addQuestionsButton(){
        CustomButton questionsButton = new CustomButton("QUESTIONS");
        questionsButton.setFont(fieldFont);
        questionsButton.setBounds((int)(mechanics.getScreenWidth()/2.7), 500, 500, 100);
        questionsButton.addActionListener(new ActionListener() {

            //override function of button
            @Override
            public void actionPerformed(ActionEvent e) {

                Mechanics.frame.remove(Mechanics.menuPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.questionsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return questionsButton;
    }

    //colour graphics for section
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,1920,1280);

        for (int i = 0; i < 11; i++) {


            //Draw the background
            g.setColor(red);
            g.fillRect(rightDisplacement, 100 * i, 50, 50);
            if (rightDisplacement > 1920) {
                rightDisplacement = 1;
            }
            g.setColor(green);
            g.fillRect(rightDisplacement * 2, 100 * i, 50, 50);
            if (rightDisplacement > 1920) {
                rightDisplacement = 1;
            }
            g.setColor(green);
            g.fillRect(rightDisplacement * 3, 100 * i, 50, 50);
            if (rightDisplacement > 1920) {
                rightDisplacement = 1;
            }
            g.setColor(blue);
            g.fillRect(leftDisplacement + 1920, 100 * i, 50, 50);
            if (leftDisplacement < -1920) {
                leftDisplacement = -1;
            }
            g.setColor(blue);
            g.fillRect(leftDisplacement * 4 + 1920, 100 * i, 50, 50);
            if (leftDisplacement < -1920) {
                leftDisplacement = -1;
            }
            g.setColor(green);
            g.fillRect(leftDisplacement * 5 + 1920, 100 * i, 50, 50);
            if (leftDisplacement < -1920) {
                leftDisplacement = -1;
            }
            g.setColor(red);
            g.fillRect(leftDisplacement * 10 + 1920, 100 * i, 50, 50);
            if (leftDisplacement < -1920) {
                leftDisplacement = -1;
            }

        }
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("MAIN MENU", (int)(mechanics.getScreenWidth()/3.2), 150);

        g.setColor(grey);
        g.fillRect((int)(mechanics.getScreenWidth()/2.7-10), 290, 520, 330);

    }

}