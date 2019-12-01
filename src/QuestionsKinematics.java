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
public class QuestionsKinematics extends JPanel {

    //variables
    private Mechanics mechanics = new Mechanics();
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 100);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Font smallFieldFont = new Font("TimesRoman", Font.BOLD, 20);
    private Formatter formatter;

    //colour
    private Color grey = new Color(50, 50, 50);

    //Labels
    private JLabel questionLabel = new JLabel("");
    private JLabel numberLabel = new JLabel("");
    private JLabel correctLabel = new JLabel("Correct!");
    private JLabel incorrectLabel = new JLabel("");
    private JLabel answerLabel = new JLabel("Click an Answer:");
    private JLabel completedLabel = new JLabel("YOU HAVE COMPLETED ALL KINEMATICS QUESTIONS!");
    private JLabel scoreLabel;
    private String searchText = "";
    private int scoreCount = 0;

    //boolean
    private boolean qRemoved = true;
    private boolean qbackground = false;


    //Questions
    private Question q1 = new Question("Officer Rick is waiting at a red light on a neighbourhood road when a Mustang " +
            "passes him by at a constant speed of 144 km/h. The instant that the offending car passes him, Officer Rick " +
            "starts drag racing after it. Assuming that his acceleration is constant at 8 m/s², how long will it take for " +
            "the police car to catch up to the Mustang? Assuming that the top speed of the police car is 240km/h, will Officer" +
            " Rick ever catch the Mustang, assuming that it never slows down?", "36s, no", "45s, yes", "1s, yes", "136s, no");
    private Question q2 = new Question("Jeffrey decides to do a stunt for Youtube by jumping across the Wakamakala " +
            "Valley in his motorcycle. He builds a ramp that is 30 degrees above the horizontal, and he flies off at a " +
            "speed of 60 m/s. If the other side of the rocky valley is 400 m across and 100 m below this side, will " +
            "Jeffrey live to tell the tale?", "no", "yes", "Who knows?", "Doesn't matter.");
    private Question q3 = new Question("A baseball pitcher throws a ball horizontally. The ball falls 83 cm while " +
            "travelling 18.4 meters to home plate. What is the initial horizontal speed of the baseball? Neglect air " +
            "resistance.", "44.7 m/s", "1 m/s", "74 m/s", "7 m/s");
    private Question q4 = new Question("A friend tosses a baseball out of his second-floor window with an initial " +
            "velocity of 4.3 m/s [42º below the horizontal]. The ball starts from a height of 3.9 meters, and you " +
            "catch the ball 1.4 meters above the ground. How long was the ball in the air?", "1.07s", "5s", "2s", "1.70s");
    private Question q5 = new Question("A car starts from a parking lot and travels 1.2 km south and then 3.1 km in" +
            " a direction 53º north of east. Relative to the parking lot, what is the car’s total displacement?", "2.26 km [south 21º east]", "2.29 km [south 21º east]", "2.26 km [south 28º east]", "1.26 km [south 21º east]");
    private Question q6 = new Question("A boater travels across a river from one point on the western shore to a " +
            "point 95 meters south on the eastern shore. The river is 77 meters wide as measured directly from west " +
            "to east. Calculate the boater’s total displacement.", "122 km [east 51º south]", "112 km [east 51º south]", "129 km [east 51º south]", "222 km [east 51º south]");
    private Question q7 = new Question("A student goes for a jog at an average speed of 3.5 m/s. Starting from home," +
            " he first runs 1.8 km [E] and then runs 2.6 km [N 35º E]. He then heads directly home. How long will " +
            "the entire trip take?", "41min", "45min", "14min", "75min");
    private Question q8 = new Question("A helicopter travelling horizontally at 50 m/s [W] turns steadily, so that " +
            "after 45 seconds, its velocity is 35 m/s [S]. What is the average acceleration of the helicopter?", "1.39 m/s² [west 35º south]", "1.76 m/s² [west 35º south]", "1.36 m/s² [west 35º south]", "2.36 m/s² [west 35º south]");

    //lists
    private ArrayList<Question> qList;
    private ArrayList<String> answerList = new ArrayList<>();
    private int selection = -1;

    //constructor
    public QuestionsKinematics() {

        setLayout(null);

        add(new ExitButton().getExitButton());
        add(addReturnButton());
        add(addBeginButton());

        add(addNumberLabel(numberLabel));

    }

    //add return button
    public CustomButton addReturnButton() {
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Mechanics.frame.remove(Mechanics.questionsKinematics);
                Mechanics.frame.repaint();
                Mechanics.frame.add(Mechanics.questionsMenu);
                Mechanics.frame.repaint();
                Mechanics.frame.setVisible(true);
            }
        });
        return returnButton;
    }

    //add begin button
    public CustomButton addBeginButton() {
        CustomButton beginButton = new CustomButton("BEGIN");
        beginButton.setFont(fieldFont);
        beginButton.setBounds((int) (mechanics.getScreenWidth() / 2.7), 400, 500, 200);
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

    //add next button
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

    //add finish button
    public CustomButton addFinishButton() {
        CustomButton finishButton = new CustomButton("FINISH");
        finishButton.setFont(fieldFont);
        finishButton.setBounds(300, 750, 300, 100);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                Mechanics.scoreKinematics = scoreCount;
                IntroPanel.accounts.get(IntroPanel.accountNum-1).setKinematicsScore(scoreCount);

                //add to the text document
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

                //scores
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

    //final buttons for trying again and go to the progress menu
    public void addFinalButtons() {
        CustomButton againButton = new CustomButton("Click to Try Again");
        againButton.setFont(fieldFont);
        againButton.setBounds(300, 450, 500, 100);

        CustomButton progressButton = new CustomButton("Click to Check Progress");
        progressButton.setFont(fieldFont);
        progressButton.setBounds(900, 450, 500, 100);

        //action listeners
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
                Mechanics.frame.remove(Mechanics.questionsKinematics);
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

        //set text and position
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

        //action listeners
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

    //remove buttons method
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

    //check question choice method
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
        g.drawString("KINEMATICS QUESTIONS", (mechanics.getScreenWidth() / 5), 150);

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

    //add question label
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