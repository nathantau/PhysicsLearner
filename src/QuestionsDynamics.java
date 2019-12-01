

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;

/**
 * Created by ngj9136 on 12/13/2017.
 */
public class QuestionsDynamics extends JPanel {

    //Variables
    private Mechanics mechanics = new Mechanics();

    //fonts
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 100);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Font smallFieldFont = new Font("TimesRoman", Font.BOLD, 20);

    //colour
    private Color grey = new Color(50, 50, 50);

    //Labels
    private JLabel questionLabel = new JLabel("");
    private JLabel numberLabel = new JLabel("");
    private JLabel correctLabel = new JLabel("Correct!");
    private JLabel incorrectLabel = new JLabel("");
    private JLabel answerLabel = new JLabel("Click An Answer:");
    private JLabel completedLabel = new JLabel("YOU HAVE COMPLETED ALL DYNAMICS QUESTIONS!");
    private JLabel scoreLabel;
    private String searchText = "";
    private int scoreCount = 0;
    private Formatter formatter;
    private boolean qRemoved = true;
    private boolean qbackground = false;


    //Questions
    private Question q1 = new Question("Bob, the builder, is pushing a 12 kg block up an incline of 30 degrees " +
            "above the horizontal. If the coefficient of kinetic friction is 0.20, what is the force needed to push the " +
            "block up the incline at a constant velocity?", "79.25N","56.78N","25.69N","103.63N");
    private Question q2 = new Question("If an ant that could withstand 5000 newtons of force was crawling under" +
            " a mass as it was dropped, would it be able to survive if the block has a mass of 750 kg?", "Yes","No","Maybe","So");
    private Question q3 = new Question("You are sitting on a bus moving at 50 km/h [E] when you toss a ball in " +
            "front of you and straight up into the air. The ball reaches a height close to your eyes. Will the ball hit " +
            "you in the face?", "Yes","No","I don't know","Who cares?!");
    private Question q4 = new Question("An athlete with a mass of 62 kg jumps and lands on the ground on his feet. " +
            "The ground exerts a total force of 1.1 x ₁₀³ N [back 55º up] on his feet. What is the acceleration of the " +
            "athlete?", "17.75 m/s² [back 55º up]","17.55 m/s² [back 55º up]","17.75 m/s² [forward 55º up]","17.75 m/s² [back 5º up]");
    private Question q5 = new Question("A car is parked on a slippery hill. The hill is at an angle of 15º to " +
            "the horizontal. To keep it from sliding down the hill, the owner attaches a cable at the back of the car " +
            "and to a post. The mass of the car is 1.41 x ₁₀³ kg. Assuming there is no friction between the road and the " +
            "tires, what is the tension in the cable?", "3402N","0N","13361N","545534N");
    private Question q6 = new Question("A car is moving with a speed of 20 m/s when the brakes are applied. " +
            "The wheels lock (stop spinning). After travelling 40 meters, the car stops. Determine the coefficient of " +
            "kinetic friction between the tires and the road.", "2","0.43","0.51","0.79");
    private Question q7 = new Question("A hockey puck slides with an initial speed of 50 m/s on a large frozen " +
            "lake. The coefficient of kinetic friction between the puck and the ice is 0.03. What is the speed of the " +
            "puck after 10 seconds?", "74 m/s","47m/s","41 m/s","57 m/s");
    private Question q8 = new Question("A crate is placed on an adjustable inclined board. The coefficient of " +
            "static friction between the crate and the board is 0.29. What is the angle at which the crate just begins " +
            "to slips?", "17","61","67","16");

    //lists
    private ArrayList<Question> qList;
    private ArrayList<String> answerList = new ArrayList<>();
    private int selection = -1;

    //add dynamics questions
    public QuestionsDynamics() {
        setLayout(null);
        add(new ExitButton().getExitButton());
        add(addReturnButton());
        add(addBeginButton());

        add(addNumberLabel(numberLabel));
    }

    //add button to return to previous section
    public CustomButton addReturnButton() {
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                Mechanics.frame.remove(Mechanics.questionsDynamics);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.questionsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return returnButton;
    }

    //add button to begin section
    public CustomButton addBeginButton() {
        CustomButton beginButton = new CustomButton("BEGIN");
        beginButton.setFont(fieldFont);
        beginButton.setBounds((int)(mechanics.getScreenWidth()/2.7), 400, 500, 200);
        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                qRemoved = false;
                qbackground = true;
                qList = new ArrayList<>(Arrays.asList(q1, q2, q3, q4, q5, q6, q7, q8));
                searchText = qList.get(0).getQuestion();
                questionLabel.setText("<html>" + searchText.replaceAll("\\n", "<br>") + "</html>");
                add(addQuestionLabel(questionLabel, 250));

                shuffleAnswers(answerList);
                addQuestionButtons();

                add(addAnswerLabel(answerLabel));

                remove(beginButton);
                setVisible(true);
            }
        });

        return beginButton;
    }

    //next button
    public CustomButton addNextButton() {
        CustomButton nextButton = new CustomButton("NEXT");
        nextButton.setFont(fieldFont);
        nextButton.setBounds(300, 700, 200, 40);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLabels();
                qList.remove(0);
                qRemoved = false;
                remove(correctLabel);
                setVisible(false);
                if (qList.size() == 0) {
                    add(addFinishButton());
                    remove(answerLabel);
                    remove(questionLabel);
                    remove(numberLabel);
                } else {
                    searchText = qList.get(0).getQuestion();
                    questionLabel.setText("<html>" + searchText.replaceAll("\\n", "<br>") + "</html>");
                    add(answerLabel);
                    shuffleAnswers(answerList);
                    addQuestionButtons();
                }

                remove(nextButton);
                setVisible(true);
            }
        });

        return nextButton;
    }

    //Finish button
    public CustomButton addFinishButton() {
        CustomButton finishButton = new CustomButton("FINISH");
        finishButton.setFont(fieldFont);
        finishButton.setBounds(300, 750, 300, 100);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                Mechanics.scoreDynamics = scoreCount;

                //Saves progress
                IntroPanel.accounts.get(IntroPanel.accountNum-1).setDynamicsScore(scoreCount);
                    try{
                        formatter = new Formatter("filename.txt");
                    }catch(Exception e){
                        System.out.println("ERROR");
                    }
                    for(int i = 0; i<IntroPanel.accounts.size();i++){
                        formatter.format("%s%s%s%s%s%s",IntroPanel.accounts.get(i).getUsername()+"~",IntroPanel.accounts.get(i).getPassword()+"`",IntroPanel.accounts.get(i).getKinematicsScore()+"{", IntroPanel.accounts.get(i).getDynamicsScore()+"}",IntroPanel.accounts.get(i).getOpticsScore()+"[",IntroPanel.accounts.get(i).getElectricityScore()+"]");
                        if(i!=IntroPanel.accounts.size()-1){
                            formatter.format(System.getProperty("line.separator"));
                        }
                    }
                    formatter.close();
                
                //Display the score
                scoreLabel = new JLabel("Your score is: " + scoreCount + " out of 8");
                qRemoved = true;
                qbackground = false;
                scoreCount = 0;
                qList.clear();
                remove(finishButton);
                add(addQuestionLabel(completedLabel, 250));
                add(addQuestionLabel(scoreLabel, 350));
                addFinalButtons();
                setVisible(true);
            }
        });

        return finishButton;
    }
    
    
    //Add buttons to try again or to go to progress menu
    public void addFinalButtons(){
        CustomButton againButton = new CustomButton("Click to Try Again");
        againButton.setFont(fieldFont);
        againButton.setBounds(300, 450, 500, 100);

        CustomButton progressButton = new CustomButton("Click to Check Progress");
        progressButton.setFont(fieldFont);
        progressButton.setBounds(900, 450, 500, 100);

        againButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                remove(completedLabel);
                remove(scoreLabel);
                remove(againButton);
                remove(progressButton);
                add(addBeginButton());
                setVisible(true);
            }
        });

        progressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Mechanics.frame.remove(Mechanics.questionsDynamics);
                Mechanics.frame.add(Mechanics.progressMenu);
                setVisible(true);
            }
        });

        add(againButton);
        add(progressButton);

    }

    //Add the buttons for the questions
    public void addQuestionButtons() {

        //Custom Button
        CustomButton questionButton1 = new CustomButton(answerList.get(0));
        CustomButton questionButton2 = new CustomButton(answerList.get(1));
        CustomButton questionButton3 = new CustomButton(answerList.get(2));
        CustomButton questionButton4 = new CustomButton(answerList.get(3));

        questionButton1.setFont(smallFieldFont);
        questionButton1.setBounds(300, 700, 300, 50);
        questionButton2.setFont(smallFieldFont);
        questionButton2.setBounds(300, 760, 300, 50);
        questionButton3.setFont(smallFieldFont);
        questionButton3.setBounds(300, 820, 300, 50);
        questionButton4.setFont(smallFieldFont);
        questionButton4.setBounds(300, 880, 300, 50);

        add(questionButton1);
        add(questionButton2);
        add(questionButton3);
        add(questionButton4);

        //Action listeners
        questionButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selection = 0;
                removeButtons(questionButton1,questionButton2,questionButton3,questionButton4);
            }
        });

        questionButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selection = 1;
                removeButtons(questionButton1,questionButton2,questionButton3,questionButton4);
            }
        });

        questionButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selection = 2;
                removeButtons(questionButton1,questionButton2,questionButton3,questionButton4);
            }
        });

        questionButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selection = 3;
                removeButtons(questionButton1,questionButton2,questionButton3,questionButton4);
            }
        });
    }

    //remove label method
    public void removeLabels() {
        remove(correctLabel);
        remove(incorrectLabel);
    }

    //remove button method
    public void removeButtons(CustomButton questionButton1, CustomButton questionButton2, CustomButton questionButton3, CustomButton questionButton4){
        remove(answerLabel);
        qRemoved = true;
        check();
        remove(questionButton1);
        remove(questionButton2);
        remove(questionButton3);
        remove(questionButton4);
        add(addNextButton());
    }

    //Check if question is correct method
    public boolean check() {
        if (selection != -1) {
            if (answerList.get(selection).equals(qList.get(0).getAnswer())) {
                scoreCount += 1;
                add(addSelectionLabel(correctLabel));
                repaint();
                return true;
            } else {
                incorrectLabel.setText("Incorrect. The correct answer is " + qList.get(0).getAnswer());
                add(addSelectionLabel(incorrectLabel));
                repaint();
            }
        }
        return false;
    }

    //shuffle answers method
    public void shuffleAnswers(ArrayList<String> list) {
        list.clear();
        list.add(qList.get(0).getAnswer());
        list.add(qList.get(0).getWrongSelection1());
        list.add(qList.get(0).getWrongSelection2());
        list.add(qList.get(0).getWrongSelection3());
        Collections.shuffle(list);
    }

    //paint component
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1280);

        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("DYNAMICS QUESTIONS", (mechanics.getScreenWidth()/5), 150);

        if(!qRemoved){
            g.setColor(grey);
            g.fillRect(280, 610, 340, 340);
        }

        if(qbackground){
            g.setColor(grey);
            g.fillRect(270, 220, 1240, 300);
            g.setColor(Color.white);
            g.drawRect(270, 220, 1240, 300);
        }
    }

    //Add question label
    public JLabel addQuestionLabel(JLabel label, int ypos) {
        label.setFont(fieldFont);
        label.setBounds(300, ypos, 1200, 800);
        label.setForeground(Color.white);
        label.setVerticalAlignment(SwingConstants.TOP);
        return label;
    }

    //add number label
    public JLabel addNumberLabel(JLabel label) {
        label.setFont(fieldFont);
        label.setBounds(300, 100, 400, 300);
        label.setForeground(Color.white);
        return label;
    }

    //Add answer label
    public JLabel addAnswerLabel(JLabel label) {
        label.setFont(fieldFont);
        label.setBounds(300, 600, 500, 100);
        label.setForeground(Color.white);
        return label;
    }
    //add selection label
    public JLabel addSelectionLabel(JLabel label) {
        label.setFont(fieldFont);
        label.setBounds(300, 620, 1000, 100);
        label.setForeground(Color.white);
        return label;
    }
}