import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by ZhaoA0388 on 12/12/2017.
 */
public class DynamicsMenu extends JPanel {

    //variables and other foundations 
    private double time = 0;
    private boolean launch = false;
    private ArrayList xCoords = new ArrayList<DynamicsObject>();
    private Mechanics mechanics = new Mechanics();
    private Color grey = new Color(50, 50, 50);

    private Font titleFont = new Font("TimesRoman", Font.BOLD, 130);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Font timeFont = new Font("TimesRoman", Font.BOLD, 60);

    private Dynamics dynamics = new Dynamics();
    private DynamicsObject dynamicsObject = new DynamicsObject();
    private DecimalFormat df = new DecimalFormat("#.###");

    //Labels
    private JLabel forceLabel = new JLabel("Force Applied: " + dynamics.getForce() + "N");
    private JLabel massLabel = new JLabel("Mass: " + dynamics.getMass() + "kg");
    private JLabel coefficientLabel = new JLabel("Coefficient of friction: " + dynamics.getCoefficient());
    private JLabel timeLabel = new JLabel("Time: " + time + "s");
    private JLabel displayLabel = new JLabel("Net Force: " + dynamics.getNetForce());
    private JLabel displayLabel2 = new JLabel("Frictional Force " + df.format(dynamics.getMass() * dynamics.getCoefficient() * 9.81));
    private JLabel displayLabel3 = new JLabel("Object Acceleration: " + dynamics.getxAcceleration() + "m/s^2");
    private JLabel infoLabel = new JLabel("Velocity at the last dot: 0m/s");
    private JLabel infoLabel2 = new JLabel("Time of last dot: 0s");

    private CustomButton coefficientTwoButton;
    private CustomButton coefficientOneButton;
    private CustomButton forceTwentyButton;
    private CustomButton forceThirtyButton;
    private CustomButton forceTwentyFiveButton;
    private CustomButton massTwoButton;
    private CustomButton massFourButton;
    private CustomButton launchButton;
    private CustomButton returnButton;
    private CustomButton resetButton;


    //create menu buttons
    public DynamicsMenu() {
        dynamicsObject.setvX("0");
        dynamicsObject.setTime("0");

        setLayout(null);
        setFocusable(true);
        add(new ExitButton().getExitButton());
        add(addReturnButton());
        add(ForceThirtyButton());
        add(ForceFortyButton());
        add(ForceFiftyButton());
        add(MassTwoButton());
        add(MassFourButton());
        add(LaunchButton());
        DynamicsStructure();
        add(ResetButton());
        add(CoefficientTwoButton());
        add(CoefficientOneButton());

        addInfoLabel(displayLabel, 420, 550);
        addInfoLabel(displayLabel2, 420, 600);
        addInfoLabel(displayLabel3, 420, 650);
        addInfoLabel(infoLabel, 420, 700);
        addInfoLabel(infoLabel2, 420, 750);

        addLabel(forceLabel, 420, 300);
        addLabel(massLabel, 420, 350);
        addLabel(coefficientLabel, 420, 400);
        addLabel(timeLabel, 420, 450);
        addInstructionsButton();
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
                new InstructionsFrame(6);
            }
        });
        add(instructionsButton);
    }

    //dynamics menu foundational structure build
    public void DynamicsStructure() {
        java.util.Timer timer = new java.util.Timer();
        java.util.Timer paintLineTimer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (launch == true) {
                    if (dynamics.getxCoordinate() < 1450) {
                        time = time + 0.017;
                        dynamics.setTime(time);
                        dynamics.setNetForce();
                        dynamics.setaX();
                        dynamics.setvX(dynamics.getXVelocity(), time);
                        dynamics.setxCoordinate((int) dynamics.getHorizontalDisplacement(time) + 100);
                        timeLabel.setText("Time: " + dynamics.getTime() + "s");
                        addInfoLabel(displayLabel, 420, 550);
                        addInfoLabel(displayLabel2, 420, 600);
                        addInfoLabel(displayLabel3, 420, 650);
                        displayLabel.setText("Net Force: " + dynamics.getNetForce());
                        displayLabel2.setText("Frictional Force " + df.format(dynamics.getMass() * dynamics.getCoefficient() * 9.81));
                        displayLabel3.setText("Object Acceleration: " + dynamics.getxAcceleration() + "m/s^2");

                    } else {
                        dynamics.setaXReset();
                    }
                    repaint();
                }
            }
        }, 1, 10);

        paintLineTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (launch == true && dynamics.getxCoordinate() < 1450) {
                    dynamicsObject = new DynamicsObject(dynamics.getxCoordinate(), dynamics.getTime(), dynamics.getvXInstant());
                    xCoords.add(dynamicsObject);
                    addInfoLabel(infoLabel, 420, 700);
                    addInfoLabel(infoLabel2, 420, 750);
                    infoLabel.setText("Velocity at last dot: " + dynamicsObject.getvX() + "m/s");
                    infoLabel2.setText("Time of last dot: " + dynamicsObject.getTime() + "s");
                }
            }
        }, 1000, 1000);
    }

    //reset all affected labels to starting position
    public void setLabels() {
        dynamics.setForce(0);
        dynamics.setMass(0);
        dynamics.setNetForce();
        dynamics.setvX();
        dynamics.setaXReset();
        dynamics.setCoefficient(0);
        dynamics.setTime(0);
        forceLabel.setText("Force Applied: " + dynamics.getForce() + "N");
        massLabel.setText("Mass: " + dynamics.getMass() + "kg");
        coefficientLabel.setText("Coefficient of friction: " + dynamics.getCoefficient());
        timeLabel.setText("Time: " + dynamics.getTime() + "s");
        displayLabel.setText("Net Force: " + dynamics.getNetForce());
        displayLabel2.setText("Frictional Force " + df.format(dynamics.getMass() * dynamics.getCoefficient() * 9.81));
        displayLabel3.setText("Object Acceleration: " + dynamics.getxAcceleration() + "m/s^2");
        infoLabel.setText("Velocity at last dot: " + dynamicsObject.getvX() + "m/s");
        infoLabel2.setText("Time of last dot: " + dynamicsObject.getTime() + "s");
    }

    //add button to return
    public CustomButton addReturnButton() {
        returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Mechanics.frame.remove(Mechanics.dynamicsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return returnButton;
    }

    //add coefficient of friction 0.1
    public CustomButton CoefficientOneButton() {
        coefficientTwoButton = new CustomButton("0.1");
        coefficientTwoButton.setFont(fieldFont);
        coefficientTwoButton.setBounds(20, 730, 200, 40);
        coefficientTwoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamics.setCoefficient(0.1);
                coefficientLabel.setText("Coefficient of friction: " + dynamics.getCoefficient());
            }
        });

        return (coefficientTwoButton);
    }

    //add coefficient of friction 0.2
    public CustomButton CoefficientTwoButton() {
        coefficientOneButton = new CustomButton("0.2");
        coefficientOneButton.setFont(fieldFont);
        coefficientOneButton.setBounds(20, 790, 200, 40);
        coefficientOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamics.setCoefficient(0.2);
                coefficientLabel.setText("Coefficient of friction: " + dynamics.getCoefficient());
            }
        });

        return (coefficientOneButton);
    }

    //add force 30N button
    public CustomButton ForceThirtyButton() {
        forceTwentyButton = new CustomButton("30N");
        forceTwentyButton.setFont(fieldFont);
        forceTwentyButton.setBounds(20, 240, 200, 40);
        forceTwentyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamics.setForce(30);
                forceLabel.setText("Force Applied: " + dynamics.getForce() + "N");
            }
        });

        return (forceTwentyButton);
    }

    //add force 40N button
    public CustomButton ForceFortyButton() {
        forceThirtyButton = new CustomButton("40N");
        forceThirtyButton.setFont(fieldFont);
        forceThirtyButton.setBounds(20, 295, 200, 40);
        forceThirtyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamics.setForce(40);
                forceLabel.setText("Force Applied: " + dynamics.getForce() + "N");
            }
        });

        return (forceThirtyButton);
    }

    //add force 50N button
    public CustomButton ForceFiftyButton() {
        forceTwentyFiveButton = new CustomButton("50N");
        forceTwentyFiveButton.setFont(fieldFont);
        forceTwentyFiveButton.setBounds(20, 350, 200, 40);
        forceTwentyFiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamics.setForce(50);
                forceLabel.setText("Force Applied: " + dynamics.getForce() + "N");
            }
        });

        return (forceTwentyFiveButton);
    }

    //add mass 2kg button
    public CustomButton MassTwoButton() {
        massTwoButton = new CustomButton("2kg");
        massTwoButton.setFont(fieldFont);
        massTwoButton.setBounds(20, 480, 200, 40);
        massTwoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamics.setMass(2);
                massLabel.setText("Mass: " + dynamics.getMass() + "kg");
            }
        });

        return (massTwoButton);
    }

    //add mass 4kg button
    public CustomButton MassFourButton() {
        massFourButton = new CustomButton("4kg");
        massFourButton.setFont(fieldFont);
        massFourButton.setBounds(20, 540, 200, 40);
        massFourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamics.setMass(4);
                massLabel.setText("Mass: " + dynamics.getMass() + "kg");
            }
        });

        return (massFourButton);
    }

    //add launch button
    public CustomButton LaunchButton() {
        launchButton = new CustomButton("LAUNCH");
        launchButton.setFont(fieldFont);
        launchButton.setBounds(1100, 450, 200, 40);
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launch = true;
                remove(forceTwentyButton);
                remove(forceThirtyButton);
                remove(forceTwentyFiveButton);
                remove(massTwoButton);
                remove(massFourButton);
                remove(coefficientTwoButton);
                remove(coefficientOneButton);
                repaint();
            }
        });
        return (launchButton);
    }

    //add reset button
    public CustomButton ResetButton() {
        resetButton = new CustomButton("RESET");
        resetButton.setFont(fieldFont);
        resetButton.setBounds(1100, 510, 200, 40);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamics.setxCoordinate(100);

                time = 0;
                launch = false;
                dynamicsObject.setvX("0");
                dynamicsObject.setTime("0");

                add(forceTwentyButton);
                add(forceThirtyButton);
                add(forceTwentyFiveButton);
                add(massTwoButton);
                add(massFourButton);
                add(coefficientTwoButton);
                add(coefficientOneButton);
                xCoords.clear();
                setLabels();
                repaint();

            }
        });
        return (resetButton);
    }

    //create label
    public void addLabel(JLabel label, int x, int y) {
        label.setForeground(Color.white);
        label.setFont(fieldFont);
        label.setBounds(x, y, 500, 40);
        add(label);
    }

    //create info label
    public void addInfoLabel(JLabel label, int x, int y) {
        label.setForeground(Color.white);
        label.setFont(fieldFont);
        label.setBounds(x, y, 1000, 100);

        add(label);
    }

    //creates colour graphics
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1980);

        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("DYNAMICS", (int) (mechanics.getScreenWidth() / 3.2), 150);

        g.setFont(fieldFont);

        g.fillRect(100, 950, 1450, 20);

        g.setColor(Color.white);
        g.fillRect(10, 225, 220, 178);
        g.fillRect(10, 470, 220, 118);
        g.fillRect(10, 720, 220, 118);

        g.setColor(grey);
        g.fillRect(375, 225, 525, 620);
        g.fillRect(1090, 440, 220, 130);

        g.setColor(Color.white);
        g.drawRect(375, 225, 525, 620);
        g.drawRect(1090, 440, 220, 130);

        g.setColor(Color.white);
        g.drawString("Choose Force", 20, 210);
        g.drawString("Choose Mass", 20, 460);
        g.drawString("Choose ", 20, 640);
        g.drawString("Coefficient", 20, 675);
        g.drawString("of Friction", 20, 710);

        g.drawString("Launch Statistics: ", 400, 280);
        g.drawString("Ball Statistics: ", 400, 560);

        g.drawLine(375, 510, 900, 510);


        for (int i = 0; i < xCoords.size(); i++) {
            g.setColor(Color.white);
            g.fillOval(((DynamicsObject) (xCoords.get(i))).getxCoordinate(), 1000, 10, 10);
        }

        if (launch == true) {
            g.setColor(Color.black);
            g.fillRect(10, 150, 300, 750);
        }
        g.setColor(Color.white);
        g.fillOval(dynamics.getxCoordinate(), 900, 50, 50);

        repaint();

    }
}
                

    

