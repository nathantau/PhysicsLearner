import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.*;

public class SnellsPanel extends JPanel{

    //variables
    private Mechanics mechanics = new Mechanics();

    ///fonts
    private Font titleFont = new Font("SansSerif", Font.PLAIN, 100);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Font timeFont = new Font("TimesRoman", Font.BOLD, 60);

    //colours
    private Color white = new Color(255, 255, 255);
    private Color red = new Color(255, 0, 0);
    private Color blue = new Color(0, 0, 255);
    private Color grey = new Color(50,50,50);

    //lightray
    private LightRay lightRay = new LightRay(0,1,1.5,300);
    private static DecimalFormat decimalFormat = new DecimalFormat(".#");

    //boolean and numbers
    private boolean swapped = false;
    private double oldAngle = 0;
    private boolean isCriticalAngle = false;
    private int descriptionX = 100;
    private int descriptiony = 300;
    private int firstButtonY = 650;
    private int firstButtonX = 150;

    //constructor
    public SnellsPanel(){
        setLayout(null);
        add(new ExitButton().getExitButton());
        add(addPlusButton());
        add(addMinusButton());
        add(addResetButton());
        add(addSwapButton());
        add(addReturnButton());
        addInstructionsButton();
    }

    //reset method
    public void reset(){
        lightRay.setAngleIn(0);
    }

    //return button
    public CustomButton addReturnButton() {
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                Mechanics.frame.remove(Mechanics.snellsPanel);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.tutorialMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });
        return returnButton;
    }

    //add instructions button
    public void addInstructionsButton(){
        CustomButton instructionsButton = new CustomButton("?");
        instructionsButton.setFont(timeFont);
        instructionsButton.setBounds(250, 25, 100, 100);
        instructionsButton.setBackground(Color.gray);
        instructionsButton.setNormalColor(Color.gray);
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InstructionsFrame(5);
            }
        });
        add(instructionsButton);
    }

    //add swap button for swapping mediums
    public CustomButton addSwapButton(){
        CustomButton button = new CustomButton("SWAP");
        button.setFont(fieldFont);
        button.setBounds(firstButtonX,firstButtonY+60,200,40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                double i1 = lightRay.getIndex1();
                double i2 = lightRay.getIndex2();
                lightRay.setIndex1(i2);
                lightRay.setIndex2(i1);
                if(swapped){
                    swapped = false;
                }else{
                    swapped = true;
                }
                repaint();
            }
        });
        return button;
    }

    //add reset button to reset the variables
    public CustomButton addResetButton(){
        CustomButton minusButton = new CustomButton("RESET");
        minusButton.setFont(fieldFont);
        minusButton.setBounds(firstButtonX,firstButtonY+120,200,40);
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                repaint();
            }
        });
        return minusButton;
    }

    //plus button for increasing angle
    public CustomButton addPlusButton(){
        CustomButton plusButton = new CustomButton("PLUS");
        plusButton.setFont(fieldFont);
        plusButton.setBounds(firstButtonX,firstButtonY+180,200,40);
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(0<=lightRay.getAngleIn() && lightRay.getAngleIn()<90){

                    if(lightRay.getAngleIn()==lightRay.getCriticalAngle()){
                          lightRay.setAngleOut(90);
                    }else if(lightRay.getAngleIn()<lightRay.getCriticalAngle()&&lightRay.getAngleIn()>lightRay.getCriticalAngle()-5){
                        oldAngle = lightRay.getAngleIn();
                        lightRay.setAngleIn(lightRay.getCriticalAngle());
                        isCriticalAngle = true;
                    }else{
                        lightRay.setAngleIn(lightRay.getAngleIn()+5);
                    }
                    repaint();
                }
            }
        });
        return plusButton;
    }

    //add minus button to decrease angle
    public CustomButton addMinusButton(){
        CustomButton minusButton = new CustomButton("MINUS");
        minusButton.setFont(fieldFont);
        minusButton.setBounds(firstButtonX,firstButtonY+240,200,40);
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(0<lightRay.getAngleIn() && lightRay.getAngleIn()<=90){
                    if(isCriticalAngle==true){
                        lightRay.setAngleIn(oldAngle);
                        isCriticalAngle = false;
                    }else{
                        lightRay.setAngleIn(lightRay.getAngleIn()-5);
                    }

                    repaint();
                }
            }
        });
        return minusButton;
    }

    //paint method
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D)g;
        g.fillRect(0, 0, 1920, 1280);
        g.setColor(grey);
        g.fillRect(600,200,1200,800);
        //BlueLine
        g.setColor(blue);
        g.drawLine(600,600,1800,600);
        if(swapped){
            g.fillRect(600,200,1200,400);
        }else{
            g.fillRect(600,600,1200,400);
        }

        g.setColor(Color.white);
        g.setColor(white);
        //Horizontal LInes
        g.drawLine(600,1000,1800,1000);
        g.drawLine(600,200,1800,200);

        //Vertical LInes
        g.drawLine(600,1000,600,200);
        g.drawLine(1800,1000,1800,200);


        g.setFont(titleFont);
        g.drawString("REFRACTION", (int)(mechanics.getScreenWidth()/3.5), 120);
        g.setColor(Color.white);

        g.fillRect(firstButtonX-10,firstButtonY+50,220,240);
        g.setColor(grey);
        g.fillRect(descriptionX-50,descriptiony-60,500,250);
        if(lightRay.getAngleIn()!=0){
            g.setColor(red);
            g2.setStroke(new BasicStroke(2));
            g.drawLine(1200,300,1200,900);
            g2.setStroke(new BasicStroke(1));
        }
        g.setColor(white);
        //horizontal
        g.drawLine(descriptionX-50,descriptiony-60,descriptionX+450,descriptiony-60);
        g.drawLine(descriptionX-50,descriptiony+190,descriptionX+450,descriptiony+190);
        //vertical
        g.drawLine(descriptionX-50,descriptiony-60,descriptionX-50,descriptiony+190);
        g.drawLine(descriptionX+450,descriptiony-60,descriptionX+450,descriptiony+190);
        g.setColor(Color.white);

        g.setFont(fieldFont);

        //draw descriptions
        if(lightRay.getAngleIn()!=0){
            g.drawString("Incident Angle: "+decimalFormat.format(lightRay.getAngleIn())+" Degrees",descriptionX-30,300);
        }else{
            g.drawString("Incident Angle: "+0.0+" Degrees",descriptionX-30,300);
        }

        if(lightRay.getRefractionAngle()!=0){
            g.drawString("Refraction Angle: "+decimalFormat.format(lightRay.getRefractionAngle())+" Degrees",descriptionX-30,350);
        }else{
            g.drawString("Refraction Angle: "+0.0+" Degrees",descriptionX-30,350);
        }
        g.drawString("Incident Index: "+lightRay.getIndex1(),descriptionX-30,400);
        g.drawString("Refraction Index: "+lightRay.getIndex2(),descriptionX-30,450);

        g2.setStroke(new BasicStroke(5));
        g.drawLine((int)lightRay.getxStart(),(int)lightRay.getyStart(),(int)lightRay.getxMiddle(),(int)lightRay.getyMiddle());
        g.drawLine((int)lightRay.getxMiddle(),(int)lightRay.getyMiddle(),(int)lightRay.getxEnd(),(int)lightRay.getyEnd());

        g.drawString("Incident Index: "+lightRay.getIndex1(),620,240);
        g.drawString("Refraction Index: "+lightRay.getIndex2(),620,640);


    }


}