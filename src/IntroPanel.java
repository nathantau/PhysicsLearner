import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aun4606 on 12/5/2017.
 */
public class IntroPanel extends JPanel{

    //Variables
    private int rightDisplacement = 1;
    private int leftDisplacement = -1;
    private Mechanics mechanics = new Mechanics();

    //Colors and fonts
    private Font titleFont = new Font("SansSerif", Font.PLAIN, 130);
    private Font fieldFont = new Font("SansSerif", Font.BOLD, 50);
    private Font fieldFont2 = new Font("SansSerif", Font.BOLD, 40);
    private Font smallFont = new Font("SansSerif", Font.BOLD, 25);
    private Color white = new Color(255, 255, 255);
    private Color red = new Color(255, 0, 0);
    private Color blue = new Color(0, 0, 255);
    private Color green = new Color(0, 255, 0);
    private Formatter formatter;
    public static ArrayList<Account> accounts = new ArrayList<>();

    //Text fields
    private CustomField userName = new CustomField("USERNAME");
    private CustomField password = new CustomField("PASSWORD");
    private CustomField userLogin = new CustomField("");
    private CustomField passLogin = new CustomField("");

    //Boleans
    private boolean loginFailed = false;
    private boolean registered = false;
    private boolean registeredFail = false;
    public static int accountNum = 0;

    //Constructor
    public IntroPanel() {
        //Create constructor methods
        populateArrayList();
        setFocusable(true);
        setLayout(null);

        //Timer for background
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
                rightDisplacement += 1;
                leftDisplacement -= 1;
            }
        }, 1, 10);

        add(new ExitButton().getExitButton());
        add(addTutorialButton());

        //username and password added to
        userName.setFont(smallFont);
        userName.setBounds((int)Math.round(mechanics.getScreenWidth()/5.5),575,500,50);
        add(userName);
        password.setFont(smallFont);
        password.setBounds((int)Math.round(mechanics.getScreenWidth()/5.5),700,500,50);
        add(password);
        add(createAccount());

        userLogin.setFont(smallFont);
        userLogin.setBounds((int)Math.round(mechanics.getScreenWidth()/1.9),575,500,50);
        add(userLogin);
        passLogin.setFont(smallFont);
        passLogin.setBounds((int)Math.round(mechanics.getScreenWidth()/1.9),700,500,50);
        add(passLogin);
    }

    @Override
    //create colour graphics
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1920, 1280);

        //Draw background
        for (int i = 0; i < 11; i++) {
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

        g.setColor(new Color(50,50,50));
        g.fillRect((int)Math.round(mechanics.getScreenWidth()/5.8),430,550,500);
        g.fillRect((int)Math.round(mechanics.getScreenWidth()/1.95),430,550,500);
        g.setColor(white);
        g.drawRect((int)Math.round(mechanics.getScreenWidth()/5.8),430,550,500);
        g.drawRect((int)Math.round(mechanics.getScreenWidth()/1.95),430,550,500);
        g.setFont(titleFont);
        g.drawString("PHYSICS LEARNER", mechanics.getScreenWidth()/6, 380);
        g.setFont(fieldFont);
        g.drawString("Register:",(int)Math.round(mechanics.getScreenWidth()/5.5),490);
        g.drawString("Login:",(int)Math.round(mechanics.getScreenWidth()/1.9),490);

        //Registered displays
        if(loginFailed){
            g.setColor(red);
            g.drawString("INVALID",(int)Math.round(mechanics.getScreenWidth()/1.625),490);
        }
        if(registered){
            g.setColor(green);
            g.drawString("SUCCESS",(int)Math.round(mechanics.getScreenWidth()/3.3),490);
        }
        if(registeredFail){
            g.setColor(red);
            g.drawString("INVALID",(int)Math.round(mechanics.getScreenWidth()/3.3),490);
        }

        //draw titles
        g.setColor(white);
        g.setFont(fieldFont2);
        g.drawString("Create Username:",(int)Math.round(mechanics.getScreenWidth()/5.5),550);
        g.drawString("Create Password:",(int)Math.round(mechanics.getScreenWidth()/5.5),675);
        g.drawString("Enter Username:",(int)Math.round(mechanics.getScreenWidth()/1.9),550);
        g.drawString("Enter Password:",(int)Math.round(mechanics.getScreenWidth()/1.9),675);

    }

    //add a button for tutorials
    public CustomButton addTutorialButton(){
        CustomButton tutorialButton = new CustomButton("LOGIN");
        tutorialButton.setFont(fieldFont);
        tutorialButton.setBounds((int)Math.round(mechanics.getScreenWidth()/1.8),790,400,100);
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isValidUser()){

                    Mechanics.frame.remove(Mechanics.introPanel);
                    Mechanics.frame.repaint();
                    Mechanics.frame.add(Mechanics.menuPanel);
                    Mechanics.frame.repaint();
                    Mechanics.frame.setVisible(true);
                    Mechanics.frame.setBackground(Color.black);
                    loginFailed = false;
                    new InstructionsFrame(0);
                }else{
                    loginFailed = true;
                }
            }
        });
        return tutorialButton;
    }


    //Create account button
    public CustomButton createAccount(){
        CustomButton tutorialButton = new CustomButton("JOIN");
        tutorialButton.setFont(fieldFont);
        tutorialButton.setBounds((int)Math.round(mechanics.getScreenWidth()/4.9),790,400,100);
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                BufferedReader reader = null;
                try {
                    File file = new File("filename.txt");
                    reader = new BufferedReader(new FileReader(file));
                    String line;
                    String enteredUsername = userName.getText();
                    int sum = 0;
                    while ((line = reader.readLine()) != null) {
                        int separation = line.indexOf("~");
                        String username = line.substring(0,separation);
                        if(!username.equals(enteredUsername)){
                            sum += 0;
                        }else{
                            sum++;
                        }
                    }
                    if(sum==0){
                        accounts.add(new Account(userName.getText(),password.getText(),0,0,0,0));
                        openFile();
                        addRecords();
                        closeFile();
                        registered = true;
                        userName.setText("");
                        password.setText("");
                        registeredFail = false;

                    }else{
                        registeredFail = true;
                        registered=false;
                    }

                    repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return tutorialButton;
    }

    //Open file method
    public void openFile(){
        try{
            formatter = new Formatter("filename.txt");
        }catch(Exception e){
            System.out.println("ERROR");
        }
    }

    //Add records method
    public void addRecords(){
        for(int i = 0; i<accounts.size();i++){
            formatter.format("%s%s%s%s%s%s",accounts.get(i).getUsername()+"~",accounts.get(i).getPassword()+"`",accounts.get(i).getKinematicsScore()+"{", accounts.get(i).getDynamicsScore()+"}",accounts.get(i).getOpticsScore()+"[",accounts.get(i).getElectricityScore()+"]");
            if(i!=accounts.size()-1){
                formatter.format(System.getProperty("line.separator"));
            }
        }
    }

    //Close file method
    public void closeFile(){
        formatter.close();
    }

    //Populate the array list method
    public void populateArrayList(){
        for(int i = 0; i<accounts.size();i++){
            accounts.remove(accounts.size()-1);
        }
        BufferedReader reader = null;
        try {
            File file = new File("filename.txt");
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {

                int separation = line.indexOf("~");
                int ending = line.indexOf("`");
                int open1 = line.indexOf("{");
                int close1 = line.indexOf("}");
                int squareOpen = line.indexOf("[");
                int squareClose = line.indexOf("]");

                accounts.add(new Account(line.substring(0,separation), line.substring(separation+1,ending),Integer.valueOf(line.substring(ending+1,open1)),Integer.valueOf(line.substring(open1+1,close1)),Integer.valueOf(line.substring(close1+1,squareOpen)),Integer.valueOf(line.substring(squareOpen+1,squareClose))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //check valid user method
    public boolean isValidUser(){
        BufferedReader reader = null;
        try {
            File file = new File("filename.txt");
            reader = new BufferedReader(new FileReader(file));
            String line;
            String enteredUsername = userLogin.getText();
            String enteredPassword = passLogin.getText();
            int lineNum = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;
                int separation = line.indexOf("~");
                int ending = line.indexOf("`");
                String username = line.substring(0,separation);
                String password = line.substring(separation+1,ending);
                if(username.equals(enteredUsername)&&password.equals(enteredPassword)){
                    accountNum = lineNum;
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}