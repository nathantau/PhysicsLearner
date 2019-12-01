import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by aun4606 on 12/13/2017.
 */
public class ProjectileMotionPanel extends JPanel {

    //Variables
    private Mechanics mechanics = new Mechanics();

    //Fonts and colours
    private Font titleFont = new Font("SansSerif", Font.PLAIN, 100);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Font timeFont = new Font("TimesRoman", Font.BOLD, 60);
    private Font descriptionFont = new Font("TimesRoman", Font.BOLD, 15);
    private Color grey = new Color(50, 50, 50);
    private Color white = new Color(255, 255, 255);

    //Kinematics ball
    private KinematicsBall kinematicsBall = new KinematicsBall(0, 30, 500, 950);

    //Booleans and numbers
    private boolean projectileLaunched = false;
    private boolean projectileLanded = false;
    private double kinematicsBallYVelocity = -5;//50 originally
    private double kinematicsBallAcceleration = 2;
    private double kinematicsBallOriginalY = 950;
    private boolean canLand = false;
    private boolean canLaunch = true;
    private int firstButtonY = 650;
    private int firstButtonX = 150;
    private int descriptionX = 110;
    private int descriptiony = 300;

    //Lists
    private ArrayList<Integer> xCoords = new ArrayList<>();
    private ArrayList<Integer> yCoords = new ArrayList<>();

    //create menu panel for projectile motion section
    public ProjectileMotionPanel() {
        setLayout(null);
        //   setFocusable(true);
        add(new ExitButton().getExitButton());
        add(addReturnButton());
        add(addResetButton());
        add(addLaunchButton());
        add(addPlusXButton());
        add(addPlusYButton());
        add(addMinusXButton());
        add(addMinusYButton());
        addInstructionsButton();


        kinematicsBall.setvX(5);

        //timers
        java.util.Timer timer = new java.util.Timer();
        java.util.Timer paintLineTimer = new java.util.Timer();

        //determine the ball
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (projectileLaunched && canLaunch) {
                    canLand = true;
                    kinematicsBall.setxCoordinate(kinematicsBall.getxCoordinate() + kinematicsBall.getvX());
                    kinematicsBall.setyCoordinate(kinematicsBall.getyCoordinate() + kinematicsBallYVelocity);
                    kinematicsBallAcceleration = 2;
                    kinematicsBallYVelocity += kinematicsBallAcceleration;
                    kinematicsBall.setvY(kinematicsBallYVelocity);

                    if (kinematicsBall.getyCoordinate() == kinematicsBallOriginalY) {
                        projectileLaunched = false;
                        canLaunch = false;
                        projectileLanded = true;

                    }
                }
                repaint();
            }


        }, 1, 30);

        paintLineTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (projectileLaunched) {
                    xCoords.add((int) kinematicsBall.getxCoordinate() + 20);
                    yCoords.add((int) kinematicsBall.getyCoordinate() + 20);
                }
            }
        }, 1000, 100);

    }

    //add instructions button
    public void addInstructionsButton() {
        CustomButton instructionsButton = new CustomButton("?");
        instructionsButton.setFont(timeFont);
        instructionsButton.setBounds(250, 25, 100, 100);
        instructionsButton.setBackground(Color.gray);
        instructionsButton.setNormalColor(Color.gray);
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InstructionsFrame(3);
            }
        });
        add(instructionsButton);
    }

    //add button to reset demonstration
    public CustomButton addResetButton() {
        CustomButton resetButton = new CustomButton("RESET");
        resetButton.setFont(fieldFont);
        resetButton.setBounds(firstButtonX, firstButtonY, 200, 40);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xCoords.clear();
                yCoords.clear();
                kinematicsBall.setxCoordinate(500);
                kinematicsBall.setyCoordinate(950);
                kinematicsBall.setvX(5);

                kinematicsBallYVelocity = -5; //was originally -25

                projectileLanded = false;
                kinematicsBallAcceleration = 2;
                projectileLaunched = false;
                canLand = false;
                canLaunch = true;

                repaint();
            }
        });
        return resetButton;
    }

    //add button to control launcher
    public CustomButton addLaunchButton() {
        CustomButton launchButton = new CustomButton("LAUNCH");
        launchButton.setFont(fieldFont);
        launchButton.setBounds(firstButtonX, firstButtonY + 60, 200, 40);
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectileLaunched = true;
            }
        });
        return launchButton;
    }

    //Add x velocity button
    public CustomButton addPlusXButton() {
        CustomButton plusButton = new CustomButton("+ Vx");
        plusButton.setFont(fieldFont);
        plusButton.setBounds(firstButtonX, firstButtonY + 120, 200, 40);
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!projectileLanded) {
                    if ((!projectileLaunched) && kinematicsBall.getvX() < 25) {
                        kinematicsBall.setvX(kinematicsBall.getvX() + 5);
                    }
                }

            }
        });
        return plusButton;
    }

    //add y velocity button
    public CustomButton addPlusYButton() {
        CustomButton plusButton = new CustomButton("+ Vy");
        plusButton.setFont(fieldFont);
        plusButton.setBounds(firstButtonX, firstButtonY + 180, 200, 40);
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!projectileLanded) {
                    if ((!projectileLaunched) && kinematicsBallYVelocity > -50) {
                        kinematicsBallYVelocity -= 5;
                    }
                }

            }
        });
        return plusButton;

    }

    //minus x velocity button
    public CustomButton addMinusXButton() {
        CustomButton minusButton = new CustomButton("- Vx");
        minusButton.setFont(fieldFont);
        minusButton.setBounds(firstButtonX, firstButtonY + 240, 200, 40);
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!projectileLanded) {
                    if (!projectileLaunched) {
                        if (kinematicsBall.getvX() >= 10) {
                            kinematicsBall.setvX(kinematicsBall.getvX() - 5);
                        }
                    }
                }

            }
        });
        return minusButton;

    }

    //minus y velocity button
    public CustomButton addMinusYButton() {
        CustomButton minusButton = new CustomButton("- Vy");
        minusButton.setFont(fieldFont);
        minusButton.setBounds(firstButtonX, firstButtonY + 300, 200, 40);
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!projectileLanded) {
                    if (!projectileLaunched) {
                        if (kinematicsBallYVelocity <= -10) {
                            kinematicsBallYVelocity += 5;
                        }
                    }
                }
            }
        });
        return minusButton;

    }

    //add return button to previous section
    public CustomButton addReturnButton() {
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xCoords.clear();
                yCoords.clear();

                Mechanics.frame.remove(Mechanics.projectileMotionPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);

                kinematicsBall.setvX(5);
                kinematicsBall.setvY(-5);
                kinematicsBallYVelocity = -5;
                kinematicsBallAcceleration = 0;
                kinematicsBall.setxCoordinate(500);
                kinematicsBall.setyCoordinate(950);
                projectileLaunched = false;
                canLand = false;
                canLaunch = true;

            }
        });
        return returnButton;
    }

    //colour graphics for section
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1280);
        g.setColor(Color.white);

        g.fillRect(firstButtonX - 10, firstButtonY - 10, 220, 360);

        g.setColor(grey);
        g.fillRect(descriptionX - 50, descriptiony - 60, 400, 350);
        g.setColor(white);
        //horizontal
        g.drawLine(descriptionX - 50, descriptiony - 60, descriptionX + 350, descriptiony - 60);
        g.drawLine(descriptionX - 50, descriptiony + 290, descriptionX + 350, descriptiony + 290);
        //vertical
        g.drawLine(descriptionX - 50, descriptiony - 60, descriptionX - 50, descriptiony + 290);
        g.drawLine(descriptionX + 350, descriptiony - 60, descriptionX + 350, descriptiony + 290);

        g.setFont(titleFont);
        g.drawString("PROJECTILE MOTION", (int) (mechanics.getScreenWidth() / 3.5), 120);

        g.setFont(fieldFont);
        g.drawString("Velocity (X) = " + String.valueOf(kinematicsBall.getvX()), descriptionX, descriptiony + 100);

        g.drawString("Acceleration (X) = " + String.valueOf(0), descriptionX, descriptiony + 200);
        g.drawString("Acceleration (Y) = " + String.valueOf(-9.8), descriptionX, descriptiony + 250);
        if (canLand && kinematicsBall.getyCoordinate() == kinematicsBallOriginalY) {
            g.drawString("Velocity (Y) = " + String.valueOf(-kinematicsBallYVelocity + 2), descriptionX, descriptiony + 150);
        } else {
            if (kinematicsBallYVelocity == 0) {
                g.drawString("Velocity (Y) = " + String.valueOf(kinematicsBallYVelocity), descriptionX, descriptiony + 150);
            } else {
                g.drawString("Velocity (Y) = " + String.valueOf(-kinematicsBallYVelocity), descriptionX, descriptiony + 150);
            }

        }
        g.drawString("Position (X) = " + String.valueOf(kinematicsBall.getxCoordinate() - 500), descriptionX, descriptiony);
        g.drawString("Position (Y) = " + String.valueOf(kinematicsBall.getyCoordinate() - 950), descriptionX, descriptiony + 50);


        g.setColor(grey);
        g.fillRect(500, 200, 1350, 800);

        g.setColor(white);
        //Horizontal LInes
        g.drawLine(500, 1000, 1850, 1000);
        g.drawLine(500, 200, 1850, 200);

        g.setColor(Color.gray);
        for (int i = 250; i < 1000; i += 50) {
            g.drawLine(500, i, 1850, i);
        }

        //Vertical LInes
        g.setColor(Color.white);
        g.drawLine(500, 1000, 500, 200);
        g.drawLine(1850, 1000, 1850, 200);

        g.setColor(Color.gray);
        for (int i = 550; i < 1850; i += 50) {
            g.drawLine(i, 1000, i, 200);
        }

        g.setColor(Color.white);

        g.setFont(descriptionFont);
        g.drawString("100m", 578, 1030);
        g.drawString("200m", 678, 1030);
        g.drawString("300m", 778, 1030);
        g.drawString("400m", 878, 1030);
        g.drawString("500m", 978, 1030);
        g.drawString("600m", 1078, 1030);
        g.drawString("700m", 1178, 1030);
        g.drawString("800m", 1278, 1030);
        g.drawString("900m", 1378, 1030);
        g.drawString("1000m", 1478, 1030);
        g.drawString("1100m", 1578, 1030);
        g.drawString("1200m", 1678, 1030);
        g.drawString("1300m", 1778, 1030);

        g.drawString("100m", 465, 910);
        g.drawString("200m", 465, 810);
        g.drawString("300m", 465, 710);
        g.drawString("400m", 465, 610);
        g.drawString("500m", 465, 510);
        g.drawString("600m", 465, 410);
        g.drawString("700m", 465, 310);
        g.drawString("800m", 465, 210);

        g.fillOval((int) kinematicsBall.getxCoordinate(), (int) kinematicsBall.getyCoordinate(), 50, 50);

        //Draw the white dots
        for (int i = 0; i < xCoords.size(); i++) {
            g.fillOval(xCoords.get(i), yCoords.get(i), 10, 10);
        }

    }

}