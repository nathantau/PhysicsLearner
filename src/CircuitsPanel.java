import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.TimerTask;

public class CircuitsPanel extends JPanel {

    //Variables
    private Mechanics mechanics = new Mechanics();

    //Fonts
    private Font titleFont = new Font("SansSerif", Font.PLAIN, 130);
    private Font fieldFont = new Font("SansSerif", Font.PLAIN, 30);
    private Font smallFont = new Font("SansSerif", Font.BOLD, 15);
    private Font fieldBoldFont = new Font("SansSerif", Font.BOLD, 30);
    private Font timeFont = new Font("TimesRoman", Font.BOLD, 60);

    //Colour
    private Color grey = new Color(50, 50, 50);

    //Buttons
    private CustomButton seriesButton = new CustomButton("Series");
    private CustomButton parallelButton = new CustomButton("Parallel");

    private CustomButton incBVSeries = new CustomButton("+");
    private CustomButton decBVSeries = new CustomButton("-");
    private CustomButton incRes1 = new CustomButton("+");
    private CustomButton decRes1 = new CustomButton("-");
    private CustomButton incRes2 = new CustomButton("+");
    private CustomButton decRes2 = new CustomButton("-");

    private CustomButton incBVParallel = new CustomButton("+");
    private CustomButton decBVParallel = new CustomButton("-");
    private CustomButton incRes1Parallel = new CustomButton("+");
    private CustomButton decRes1Parallel = new CustomButton("-");
    private CustomButton incRes2Parallel = new CustomButton("+");
    private CustomButton decRes2Parallel = new CustomButton("-");

    //Labels
    private JLabel chooseCircuit = new JLabel("Choose Circuit");
    private JLabel batteryVoltage = new JLabel("Battery Voltage");
    private JLabel resistor1 = new JLabel("Resistor 1 Resistance");
    private JLabel resistor2 = new JLabel("Resistor 2 Resistance");

    //Booleans
    private boolean seriesOn = false;
    private boolean parallelOn = false;
    private boolean removed = true;

    //Circuits
    private SeriesCircuit seriesCircuit = new SeriesCircuit(10, 10, 10);
    private ParallelCircuit parallelCircuit = new ParallelCircuit(10, 10, 10);

    //Kinematics Balls
    private KinematicsBall electron1 = new KinematicsBall(0, 30, 690, 440);
    private KinematicsBall electron2 = new KinematicsBall(0, 30, 690, 440);

    //Decimal Format
    private DecimalFormat df = new DecimalFormat("#.###");

    //Constructor
    public CircuitsPanel() {
        //Create the panel
        setLayout(null);
        setFocusable(true);
        add(new ExitButton().getExitButton());
        add(addReturnButton());

        //Add buttons and labels
        addCircuitButtons();
        setParallelVarButtons();
        setSeriesVarButtons();
        addLabel(chooseCircuit, 100, 300);
        addInstructionsButton();

        //Add Timer
        java.util.Timer timer = new java.util.Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                //Set the electrons for both series and parallel
                if (seriesOn || parallelOn) {
                    if (electron1.getxCoordinate() == 690 && electron1.getyCoordinate() < 940) {
                        electron1.setyCoordinate(electron1.getyCoordinate() + 1);
                    } else if (electron1.getyCoordinate() == 940 && electron1.getxCoordinate() < 1190) {
                        electron1.setxCoordinate(electron1.getxCoordinate() + 1);
                    } else if (electron1.getxCoordinate() == 1190 && electron1.getyCoordinate() > 440) {
                        electron1.setyCoordinate(electron1.getyCoordinate() - 1);
                    } else {
                        electron1.setxCoordinate(electron1.getxCoordinate() - 1);
                    }
                }

                if (parallelOn) {
                    if (electron2.getxCoordinate() == 690 && electron2.getyCoordinate() < 940) {
                        electron2.setyCoordinate(electron2.getyCoordinate() +1);
                    }
                    else if(electron2.getyCoordinate() == 940 && electron2.getxCoordinate() < 940){
                        electron2.setxCoordinate(electron2.getxCoordinate()+1);
                    }
                    else if(electron2.getxCoordinate() == 940 && electron2.getyCoordinate() > 440){
                        electron2.setyCoordinate(electron2.getyCoordinate()-1);
                    }else{
                        electron2.setxCoordinate(electron2.getxCoordinate()-1);
                    }
                }

                repaint();
            }
        }, 0, 1);
    }

    //Add the Circuits instructions panel
    public void addInstructionsButton(){
        CustomButton instructionsButton = new CustomButton("?");
        instructionsButton.setFont(timeFont);
        instructionsButton.setBounds(250, 25, 100, 100);
        instructionsButton.setBackground(Color.gray);
        instructionsButton.setNormalColor(Color.gray);
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InstructionsFrame(4);
            }
        });
        add(instructionsButton);
    }

    //Add return button
    public CustomButton addReturnButton() {
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldBoldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Elements changed when clicked
                removed = true;
                seriesOn = false;
                parallelOn = false;

                add(seriesButton);
                add(parallelButton);
                add(chooseCircuit);
                removeElements();
                Mechanics.frame.remove(Mechanics.circuitsPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return returnButton;
    }

    //Add the circuit buttons
    public void addCircuitButtons() {

        CustomButton removeButton = new CustomButton("Remove Circuit");

        //Series button
        seriesButton.setFont(fieldBoldFont);
        seriesButton.setBounds(110, 400, 200, 50);
        seriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removed = false;
                seriesOn = true;
                addSeriesVarButtons();

                remove(chooseCircuit);
                remove(seriesButton);
                remove(parallelButton);
            }
        });

        //Parallel button
        parallelButton.setFont(fieldBoldFont);
        parallelButton.setBounds(110, 500, 200, 50);
        parallelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removed = false;
                parallelOn = true;
                addParallelVarButtons();

                remove(chooseCircuit);
                remove(seriesButton);
                remove(parallelButton);
            }
        });

        //Remove Circuit button
        removeButton.setFont(fieldBoldFont);
        removeButton.setBounds(50, 200, 300, 50);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removed = true;
                seriesOn = false;
                parallelOn = false;

                add(chooseCircuit);
                add(seriesButton);
                add(parallelButton);
                removeElements();
            }
        });
        add(seriesButton);
        add(parallelButton);
        add(removeButton);
    }

    //Remove elements method
    public void removeElements() {
        seriesCircuit.getBattery().setVoltage(10);
        seriesCircuit.getResistor1().setResistance(10);
        seriesCircuit.getResistor2().setResistance(10);

        parallelCircuit.getBattery().setVoltage(10);
        parallelCircuit.getResistor1().setResistance(10);
        parallelCircuit.getResistor2().setResistance(10);

        //Remove labels
        remove(incBVSeries);
        remove(decBVSeries);
        remove(incRes1);
        remove(decRes1);
        remove(incRes2);
        remove(decRes2);

        remove(incBVParallel);
        remove(decBVParallel);
        remove(incRes1Parallel);
        remove(decRes1Parallel);
        remove(incRes2Parallel);
        remove(decRes2Parallel);

        remove(batteryVoltage);
        remove(resistor1);
        remove(resistor2);

        repaint();
    }

    //Set parallel buttons for changing parallel variables
    public void setParallelVarButtons() {
        incBVParallel.setFont(fieldFont);
        incBVParallel.setBounds(100, 400, 60, 50);
        incBVParallel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parallelCircuit.getBattery().setVoltage(parallelCircuit.getBattery().getVoltage() + 5);
                repaint();
            }
        });

        decBVParallel.setFont(fieldFont);
        decBVParallel.setBounds(200, 400, 60, 50);
        decBVParallel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parallelCircuit.getBattery().getVoltage() > 0) {
                    parallelCircuit.getBattery().setVoltage(parallelCircuit.getBattery().getVoltage() - 5);
                    repaint();
                }
            }
        });

        incRes1Parallel.setFont(fieldFont);
        incRes1Parallel.setBounds(100, 600, 60, 50);
        incRes1Parallel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parallelCircuit.getResistor1().setResistance(parallelCircuit.getResistor1().getResistance() + 5);
                repaint();
            }
        });

        decRes1Parallel.setFont(fieldFont);
        decRes1Parallel.setBounds(200, 600, 60, 50);
        decRes1Parallel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parallelCircuit.getResistor1().getResistance() > 0) {
                    parallelCircuit.getResistor1().setResistance(parallelCircuit.getResistor1().getResistance() - 5);
                    repaint();
                }
            }
        });

        incRes2Parallel.setFont(fieldFont);
        incRes2Parallel.setBounds(100, 800, 60, 50);
        incRes2Parallel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parallelCircuit.getResistor2().setResistance(parallelCircuit.getResistor2().getResistance() + 5);
                repaint();
            }
        });

        decRes2Parallel.setFont(fieldFont);
        decRes2Parallel.setBounds(200, 800, 60, 50);
        decRes2Parallel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parallelCircuit.getResistor2().getResistance() > 0) {
                    parallelCircuit.getResistor2().setResistance(parallelCircuit.getResistor2().getResistance() - 5);
                    repaint();
                }
            }
        });
    }

    //Parallel Variable Buttons
    public void addParallelVarButtons(){
        //Elements added when parallel circuit added
        add(incBVParallel);
        add(decBVParallel);
        add(incRes1Parallel);
        add(decRes1Parallel);
        add(incRes2Parallel);
        add(decRes2Parallel);

        addLabel(batteryVoltage, 100, 300);
        addLabel(resistor1, 100, 500);
        addLabel(resistor2, 100, 700);

        repaint();
    }

    //Series Variable Buttons
    public void setSeriesVarButtons() {
        incBVSeries.setFont(fieldFont);
        incBVSeries.setBounds(100, 400, 60, 50);
        incBVSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seriesCircuit.getBattery().setVoltage(seriesCircuit.getBatteryVoltage() + 5);
                repaint();
            }
        });

        decBVSeries.setFont(fieldFont);
        decBVSeries.setBounds(200, 400, 60, 50);
        decBVSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seriesCircuit.getBattery().getVoltage() > 0) {
                    seriesCircuit.getBattery().setVoltage(seriesCircuit.getBatteryVoltage() - 5);
                    repaint();
                }
            }
        });

        incRes1.setFont(fieldFont);
        incRes1.setBounds(100, 600, 60, 50);
        incRes1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seriesCircuit.getResistor1().setResistance(seriesCircuit.getResistor1().getResistance() + 5);
                repaint();
            }
        });

        decRes1.setFont(fieldFont);
        decRes1.setBounds(200, 600, 60, 50);
        decRes1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seriesCircuit.getResistor1().getResistance() > 0) {
                    seriesCircuit.getResistor1().setResistance(seriesCircuit.getResistor1().getResistance() - 5);
                    repaint();
                }
            }
        });

        incRes2.setFont(fieldFont);
        incRes2.setBounds(100, 800, 60, 50);
        incRes2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seriesCircuit.getResistor2().setResistance(seriesCircuit.getResistor2().getResistance() + 5);
                repaint();
            }
        });

        decRes2.setFont(fieldFont);
        decRes2.setBounds(200, 800, 60, 50);
        decRes2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seriesCircuit.getResistor2().getResistance() > 0) {
                    seriesCircuit.getResistor2().setResistance(seriesCircuit.getResistor2().getResistance() - 5);
                    repaint();
                }
            }
        });
    }

    //Elements added when series circuit is added
    public void addSeriesVarButtons(){

        add(incBVSeries);
        add(decBVSeries);
        add(incRes1);
        add(decRes1);
        add(incRes2);
        add(decRes2);
        addLabel(batteryVoltage, 100, 300);
        addLabel(resistor1, 100, 500);
        addLabel(resistor2, 100, 700);

        repaint();
    }

    //Paint method
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1280);

        //Title
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("CIRCUITS", (int) (mechanics.getScreenWidth() / 3.0), 150);

        //Draw the Series circuit election
        if (seriesOn) {
            drawSeries(g);
            if(seriesCircuit.getBatteryVoltage()!=0) {
                g.setColor(Color.yellow);
                g.fillOval((int) electron1.getxCoordinate(), (int) electron1.getyCoordinate(), 20, 20);
            }
        }

        //Draw the parallel circuit electron
        if (parallelOn) {
            drawParallel(g);
            if(parallelCircuit.getBatteryVoltage()!=0) {
                g.setColor(Color.yellow);
                g.fillOval((int) electron1.getxCoordinate(), (int) electron1.getyCoordinate(), 20, 20);
                g.fillOval((int) electron2.getxCoordinate(), (int) electron2.getyCoordinate(), 20, 20);
            }
        }

        //Remove the circuits
        if (removed) {
            g.setColor(Color.black);
            g.fillRect(200, 200, 2000, 2000);
            g.setColor(grey);
            g.fillRect(80, 310, 260, 280);
            g.setColor(Color.white);
            g.drawRect(80,310, 260, 280);
        }
    }

    //Draw the Series circuit
    public void drawSeries(Graphics g) {
        g.setColor(grey);
        g.fillRect(50, 300, 550, 600);
        g.fillRect(1300, 300, 550, 600);

        g.setColor(Color.white);
        g.drawRect(50, 300, 550, 600);
        g.drawRect(1300, 300, 550, 600);
        g.drawLine(700, 450, 1200, 450);
        g.drawLine(700, 450, 700, 950);
        g.drawLine(700, 950, 1200, 950);
        g.drawLine(1200, 450, 1200, 950);
        g.drawLine(650, 700, 750, 700);
        g.drawLine(675, 710, 725, 710);

        g.setColor(Color.black);
        g.fillRect(690, 701, 20, 9);
        g.fillRect(900, 450, 100, 1);
        g.fillRect(900, 950, 100, 1);

        g.setColor(Color.white);
        g.drawRect(900, 440, 100, 20);
        g.drawRect(900, 940, 100, 20);

        g.setFont(smallFont);
        g.drawString("Resistor 1", 910, 455);
        g.drawString("Resistor 2", 910, 955);

        g.setFont(fieldFont);
        g.drawString("" + df.format(seriesCircuit.getBatteryVoltage()) + " Volts", 400, 450);
        g.drawString("" + df.format(seriesCircuit.getResistor1().getResistance()) + " Ohms", 400, 650);
        g.drawString("" + df.format(seriesCircuit.getResistor2().getResistance()) + " Ohms", 400, 850);

        g.drawString("Resistor 1: ", 1350, 350);
        g.drawString("Voltage: " + df.format(seriesCircuit.getVoltage1()) + " Volts", 1400, 400);
        g.drawString("Current: " + df.format(seriesCircuit.getCurrent1()) + " Amperes", 1400, 450);

        g.drawString("Resistor 2: ", 1350, 550);
        g.drawString("Voltage: " + df.format(seriesCircuit.getVoltage2()) + " Volts", 1400, 600);
        g.drawString("Current: " + df.format(seriesCircuit.getCurrent2()) + " Amperes", 1400, 650);

        g.drawString("Total Current: " + df.format(seriesCircuit.getTotalCurrent()) + " Amperes", 1350, 750);
        g.drawString("Total Resistance: " + df.format(seriesCircuit.getTotalResistance()) + " Ohms", 1350, 800);
        g.drawString("Total Voltage: " + df.format(seriesCircuit.getBatteryVoltage()) + " Volts", 1350, 850);
    }

    //Draw the Parallel circuit
    public void drawParallel(Graphics g) {
        g.setColor(grey);
        g.fillRect(50, 300, 550, 600);
        g.fillRect(1300, 300, 550, 600);

        g.setColor(Color.white);
        g.drawRect(50, 300, 550, 600);
        g.drawRect(1300, 300, 550, 600);
        g.drawLine(700, 450, 1200, 450);
        g.drawLine(700, 450, 700, 950);
        g.drawLine(700, 950, 1200, 950);
        g.drawLine(1200, 450, 1200, 950);
        g.drawLine(650, 700, 750, 700);
        g.drawLine(675, 710, 725, 710);
        g.drawLine(950, 450, 950, 950);

        g.setColor(Color.black);
        g.fillRect(690, 701, 20, 9);
        g.fillRect(950, 650, 1, 100);
        g.fillRect(1200, 650, 1, 100);

        g.setColor(Color.white);
        g.drawRect(940, 650, 20, 100);
        g.drawRect(1190, 650, 20, 100);

        g.setFont(smallFont);
        g.drawString("Resistor 1", 920, 640);
        g.drawString("Resistor 2", 1170, 640);

        g.setFont(fieldFont);
        g.drawString("" + df.format(parallelCircuit.getBatteryVoltage()) + " Volts", 400, 450);
        g.drawString("" + df.format(parallelCircuit.getResistor1().getResistance()) + " Ohms", 400, 650);
        g.drawString("" + df.format(parallelCircuit.getResistor2().getResistance()) + " Ohms", 400, 850);

        g.drawString("Resistor 1: ", 1350, 350);
        g.drawString("Voltage: " + df.format(parallelCircuit.getVoltage1()) + " Volts", 1400, 400);
        g.drawString("Current: " + df.format(parallelCircuit.getCurrent1()) + " Amperes", 1400, 450);

        g.drawString("Resistor 2: ", 1350, 550);
        g.drawString("Voltage: " + df.format(parallelCircuit.getVoltage2()) + " Volts", 1400, 600);
        g.drawString("Current: " + df.format(parallelCircuit.getCurrent2()) + " Amperes", 1400, 650);

        g.drawString("Total Current: " + df.format(parallelCircuit.getTotalCurrent()) + " Amperes", 1350, 750);
        g.drawString("Total Resistance: " + df.format(parallelCircuit.getTotalResistance()) + " Ohms", 1350, 800);
        g.drawString("Total Voltage: " + df.format(parallelCircuit.getBatteryVoltage()) + " Volts", 1350, 850);
    }

    //Add Label Method
    public void addLabel(JLabel label, int x, int y) {
        label.setFont(fieldBoldFont);
        label.setBounds(x, y, 400, 100);
        label.setForeground(Color.white);
        add(label);
        repaint();
    }
}
