

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
public class QuestionsElectricity extends JPanel {

    //variables
    private Mechanics mechanics = new Mechanics();

    private Font titleFont = new Font("TimesRoman", Font.BOLD, 100);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Font smallFieldFont = new Font("TimesRoman", Font.BOLD, 20);

    //Color
    private Color grey = new Color(50, 50, 50);

    //Labels
    private JLabel questionLabel = new JLabel("");
    private JLabel numberLabel = new JLabel("");
    private JLabel correctLabel = new JLabel("Correct!");
    private JLabel incorrectLabel;
    private JLabel answerLabel = new JLabel("Click An Answer:");
    private JLabel completedLabel = new JLabel("YOU HAVE COMPLETED ALL ELECTRICITY QUESTIONS!");
    private JLabel scoreLabel;
    private String searchText = "";
    private int scoreCount = 0;
    private Formatter formatter;

    //Booleans
    private boolean qRemoved = true;
    private boolean qbackground = false;



    //Questions
    private Question q1 = new Question("Two charged objects have a repulsive force of 0.08 N. The distance " +
            "separating the two objects is tripled. Determine the new repulsive force.", "0.00889 N",
            "0.00112 N","12 N","0.50 N");
    private Question q2 = new Question("Two point charges are separated by a distance r. Determine the factor " +
            "by which the electric force between them changes when the separation is reduced by a factor of 1.50.",
            "Increase by 2.25 times","Increase by 2","Decrease by 10","Increase by 2.52");
    private Question q3 = new Question("Two charged objects have an attractive force of 0.08 N. Suppose that " +
            "the charge of one of the objects is tripled and the distance separating the objects is tripled. Calculate " +
            "the new attractive force.", "0.0267 N","200N","0.00005N","0.1N");
    private Question q4 = new Question("Determine the magnitude of the electric force between two electrons " +
            "separated by a distance of 0.14 nm.", "1.175 N","2N","1.179N","1.2N");
    private Question q5 = new Question("Particles of charge q and 3q are placed on the x-axis at x = -40 and " +
            "x = 50, respectively. A third particle of charge q is placed on the x-axis, and the total electric force " +
            "on this particle is zero. Determine the position of the particle.","0","-17.5",
            "-5","50");
    private Question q6 = new Question("In a circuit, a resistor is attached in series to a battery. If the " +
            "resistance is 5 ohms and the voltage is 10 volts, what is the current.", "2 Amperes", "1 Ampere",
            "3 Amperes", "4 Amperes");
    private Question q7 = new Question("In a parallel circuit, two resistors are attached in parallel to a battery. " +
            "What is the voltage if the resistance of each resistor is 6 ohms, what is the total resistance?", "⅓ ohms",
            "2 Ohms", "1 Ohm", "0.5 Ohms");
    private Question q8 = new Question("In a series circuit, two light bulbs are connected to a battery. The " +
            "first lightbulb has a voltage of 12 volts, while the second one has 15 volts. What is the total voltage?",
            "27 Volts", "30 Volts", "32 Volts", "24 Volts");

    //lists
    private ArrayList<Question> qList;
    private ArrayList<String> answerList = new ArrayList<>();
    private int selection = -1;

    //add questions
    public QuestionsElectricity() {
        setLayout(null);
        add(new ExitButton().getExitButton());
        add(addReturnButton());
        add(addBeginButton());

        add(addNumberLabel(numberLabel));
    }

    //add return button to previous section
    public CustomButton addReturnButton(){
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                Mechanics.frame.remove(Mechanics.questionsElectricity);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.questionsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });

        return returnButton;
    }
    
    //add button to begin
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

    //add net button
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

    //add button to finish questions
    public CustomButton addFinishButton() {
        CustomButton finishButton = new CustomButton("FINISH");
        finishButton.setFont(fieldFont);
        finishButton.setBounds(300, 750, 300, 100);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                Mechanics.scoreElectricity = scoreCount;
                IntroPanel.accounts.get(IntroPanel.accountNum-1).setElectricityScore(scoreCount);

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

                scoreLabel = new JLabel("Your score is: " + scoreCount + " out of 8");

                scoreCount = 0;
                qRemoved = true;
                qbackground = false;
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

    //add the buttons to try again and to check for progress
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
                Mechanics.frame.remove(Mechanics.questionsElectricity);
                Mechanics.frame.add(Mechanics.progressMenu);
                setVisible(true);
            }
        });

        add(againButton);
        add(progressButton);

    }

    //add question buttons
    public void addQuestionButtons() {

        //Custom Button
        CustomButton questionButton1 = new CustomButton(answerList.get(0));
        CustomButton questionButton2 = new CustomButton(answerList.get(1));
        CustomButton questionButton3 = new CustomButton(answerList.get(2));
        CustomButton questionButton4 = new CustomButton(answerList.get(3));

        //set question buttons
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

        //question button action listeners
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

    //remove labels method
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

    //check method
    public boolean check() {
        if (selection != -1) {
            if (answerList.get(selection).equals(qList.get(0).getAnswer())) {
                scoreCount += 1;
                add(addSelectionLabel(correctLabel));
                repaint();
                return true;
            } else {
                incorrectLabel = new JLabel("Incorrect. The correct answer is " + qList.get(0).getAnswer());
                add(addSelectionLabel(incorrectLabel));
                repaint();
            }
        }
        return false;
    }

    //shuffle the answers method
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
        g.drawString("ELECTRICITY QUESTIONS", (mechanics.getScreenWidth()/5), 150);

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

    //question label
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

    //add answer label
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