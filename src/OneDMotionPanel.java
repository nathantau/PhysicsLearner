import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by ngj9136 on 12/12/2017.
 */

public class OneDMotionPanel extends JPanel {

    //declares variables for section
    private Font descriptionFont = new Font("TimesRoman", Font.BOLD, 15);
    private Mechanics mechanics = new Mechanics();
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 130);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Font timeFont = new Font("TimesRoman", Font.BOLD, 60);
    private Font buttonFont = new Font("TimesRoman", Font.BOLD, 20);

    //Colour
    private Color grey = new Color(50, 50, 50);

    //Boolean & ball variables
    private boolean projectileLaunched = false;
    private KinematicsBall kinematicsBall = new KinematicsBall(5, 30, 200, 850);

    //Time
    private double time;
    private String roundedTime = "";
    private String roundedVelocity = "";
    private String roundedAcceleration = "";

    //Labels
    private JLabel vLabel = new JLabel("Velocity: ");
    private JLabel aLabel = new JLabel("Acceleration: ");
    private JLabel timeLabel = new JLabel("Time: " + roundedTime);

    //Buttons
    private CustomButton velocityIncrease = new CustomButton("+");
    private CustomButton velocityDecrease = new CustomButton("-");
    private CustomButton accIncrease = new CustomButton("+");
    private CustomButton accDecrease = new CustomButton("-");

    private DecimalFormat df = new DecimalFormat("#.#");

    //Lists
    private ArrayList<Integer> xCoords = new ArrayList<>();
    private ArrayList<Integer> yCoords = new ArrayList<>();


    //create menu panel for section
    public OneDMotionPanel() {
        //Add buttons and set the panel
        setLayout(null);
        setFocusable(true);
        add(new ExitButton().getExitButton());
        addVelocityButtons();
        addAccelerationButtons();
        add(addReturnButton());
        add(addLaunchButton());
        add(addResetButton());
        addInstructionsButton();
        addTimeLabel(timeLabel);

        //Add labels
        addLabel(vLabel, 600, 300);
        addLabel(aLabel, 600, 400);

        setLabels();

        //Timers
        java.util.Timer timer = new java.util.Timer();
        java.util.Timer paintLineTimer = new java.util.Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (projectileLaunched) {

                    if (kinematicsBall.getxCoordinate() < 1200) {
                        time += 0.03;
                        roundedTime = df.format(time);

                        timeLabel.setText("Time: " + roundedTime + " seconds");
                        timeLabel.repaint();
                        kinematicsBall.setvX(kinematicsBall.getvX() + kinematicsBall.getaX());
                        kinematicsBall.setxCoordinate(kinematicsBall.getxCoordinate() + kinematicsBall.getvX());
                        repaint();
                    } else {
                        kinematicsBall.setvX(0);
                        projectileLaunched = false;
                    }
                }
            }
        }, 0, 30);

        paintLineTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (projectileLaunched) {
                    xCoords.add((int) kinematicsBall.getxCoordinate());
                    yCoords.add((int) kinematicsBall.getyCoordinate() - 20);
                }
            }
        }, 2000, 300);
    }

    //Add instructions button
    public void addInstructionsButton(){
        CustomButton instructionsButton = new CustomButton("?");
        instructionsButton.setFont(timeFont);
        instructionsButton.setBounds(250, 25, 100, 100);
        instructionsButton.setBackground(Color.gray);
        instructionsButton.setNormalColor(Color.gray);
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InstructionsFrame(1);
            }
        });
        add(instructionsButton);
    }

    //Add velocity buttons
    public void addVelocityButtons() {

        velocityIncrease.setFont(buttonFont);
        velocityDecrease.setFont(buttonFont);

        velocityIncrease.setBounds(950, 300, 75, 50);
        velocityDecrease.setBounds(1050, 300, 75, 50);

        //Action listeners
        velocityIncrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double velocity = kinematicsBall.getvX() + 3;
                kinematicsBall.setvX(velocity);
                roundedVelocity = df.format(kinematicsBall.getvX() / 3);
                vLabel.setText("Velocity: " + roundedVelocity + "m/s");
                repaint();
            }
        });
        velocityDecrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kinematicsBall.getvX() > 0) {
                    double velocity = kinematicsBall.getvX() - 3;
                    kinematicsBall.setvX(velocity);

                    roundedVelocity = df.format(kinematicsBall.getvX() / 3);
                    vLabel.setText("Velocity: " + roundedVelocity + "m/s");
                    repaint();
                }
            }
        });

        //Add to panel
        add(velocityIncrease);
        add(velocityDecrease);
    }

    //Add acceleration buttons
    public void addAccelerationButtons() {

        accIncrease.setFont(buttonFont);
        accDecrease.setFont(buttonFont);

        accIncrease.setBounds(950, 400, 75, 50);
        accDecrease.setBounds(1050, 400, 75, 50);

        //Add action listeners
        accIncrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double acc = kinematicsBall.getaX() + 0.089;
                kinematicsBall.setaX(acc);

                roundedAcceleration = df.format(kinematicsBall.getaX() * 1000 / 89);
                aLabel.setText("Acceleration: " + roundedAcceleration + "m/s²");
                repaint();
            }
        });
        accDecrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kinematicsBall.getaX() > 0) {
                    double acc = kinematicsBall.getaX() - 0.089;
                    kinematicsBall.setaX(acc);

                    roundedAcceleration = df.format(kinematicsBall.getaX() * 1000 / 89);
                    aLabel.setText("Acceleration: " + roundedAcceleration + "m/s²");
                    repaint();
                }
            }
        });

        //Add to panel
        add(accIncrease);
        add(accDecrease);
    }

    //add button with return function
    public CustomButton addReturnButton() {
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xCoords.clear();
                yCoords.clear();

                //Set panel to frame
                Mechanics.frame.remove(Mechanics.oneDMotionPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);

                //Add labels
                setLabels();
                add(velocityIncrease);
                add(velocityDecrease);
                add(accIncrease);
                add(accDecrease);
                resetVariables();
                kinematicsBall.setxCoordinate(200);
                projectileLaunched = false;
                timeLabel.setText("Time: ");
                repaint();
            }
        });
        return returnButton;
    }

    //Set labels
    public void setLabels() {
        kinematicsBall.setvX(0);
        kinematicsBall.setaX(0);
        roundedVelocity = df.format(kinematicsBall.getvX());
        vLabel.setText("Velocity: " + roundedVelocity + "m/s");

        roundedAcceleration = df.format(kinematicsBall.getaX());
        aLabel.setText("Acceleration: " + roundedAcceleration + "m/s²");
        repaint();
    }

    //add button to reset demonstration
    public CustomButton addResetButton() {
        CustomButton resetButton = new CustomButton("RESET");
        resetButton.setFont(fieldFont);
        resetButton.setBounds(220, 300, 200, 40);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xCoords.clear();
                yCoords.clear();
                setLabels();
                add(velocityIncrease);
                add(velocityDecrease);
                add(accIncrease);
                add(accDecrease);
                resetVariables();
                kinematicsBall.setxCoordinate(200);
                projectileLaunched = false;
                timeLabel.setText("Time: ");
                repaint();
            }
        });
        return resetButton;
    }

    //add button to control launcher
    public CustomButton addLaunchButton() {
        CustomButton launchButton = new CustomButton("LAUNCH");
        launchButton.setFont(fieldFont);
        launchButton.setBounds(220, 400, 200, 40);
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectileLaunched = true;
                remove(velocityIncrease);
                remove(velocityDecrease);
                remove(accIncrease);
                remove(accDecrease);
                repaint();
            }
        });
        return launchButton;
    }

    //colour graphics for section
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1280);

        //Background for the ball
        g.setColor(grey);
        g.fillRect(160, 750, 1190, 200);
        g.setColor(Color.white);
        g.drawRect(160, 750, 1190, 200);

        for (int i = 0; i < xCoords.size(); i++) {
            g.fillOval(xCoords.get(i), yCoords.get(i), 10, 10);
        }

        g.setColor(grey);
        g.fillRect(160, 520, 310, 150);

        g.setColor(Color.white);
        g.drawRect(160, 520, 310, 150);
        g.setFont(fieldFont);
        g.drawString("Ball Statistics:", 190, 570);
        g.setFont(descriptionFont);
        g.drawString("Displacement: " + df.format((kinematicsBall.getxCoordinate()-200)/100.3) + " m", 190, 600);
        g.drawString("Velocity: " + df.format(kinematicsBall.getvX()/3) + " m/s", 190, 620);
        g.drawString("Acceleration: " + df.format(kinematicsBall.getaX()* 1000 / 89) + "m/s²", 190, 640);

        g.setColor(grey);
        g.fillRect(160, 270, 1190, 210);
        g.fillRect(580, 520, 770, 150);
        g.setColor(Color.white);
        g.drawRect(160, 270, 1190, 210);
        g.drawRect(580, 520, 770, 150);

        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("1D MOTION", (int) (mechanics.getScreenWidth() / 3.2), 150);

        g.setFont(fieldFont);
        g.drawLine(200, 850, 1250, 850);
        g.drawLine(200, 845, 200, 855);
        g.drawLine(463, 845, 463, 855);
        g.drawLine(726, 845, 726, 855);
        g.drawLine(989, 845, 989, 855);
        g.drawLine(1250, 845, 1250, 855);
        g.drawString("0m", 200, 900);
        g.drawString("2.5m", 443, 900);
        g.drawString("5m", 716, 900);
        g.drawString("7.5m", 969, 900);
        g.drawString("10m", 1200, 900);

        //Draw ball
        if (kinematicsBall != null) {
            g.fillOval((int) kinematicsBall.getxCoordinate(), (int) kinematicsBall.getyCoordinate() - 50, 50, 50);
        }
    }

    //Add label
    public void addLabel(JLabel label, int x, int y) {
        label.setFont(fieldFont);
        label.setBounds(x, y, 800, 50);
        label.setForeground(Color.white);
        add(label);
    }

    //Add the time label
    public void addTimeLabel(JLabel label) {
        label.setFont(timeFont);
        label.setBounds(610, 545, 900, 100);
        label.setForeground(Color.white);
        add(label);
    }

    //Reset label method
    public void resetVariables() {
        time = 0;
        roundedTime = "";
        roundedVelocity = "";
        roundedAcceleration = "";
    }


}