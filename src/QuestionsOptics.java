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
public class QuestionsOptics extends JPanel {

    //variables
    private Mechanics mechanics = new Mechanics();

    //fonts
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 100);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 30);
    private Font smallFieldFont = new Font("TimesRoman", Font.BOLD, 20);

    //colours
    private Color grey = new Color(50, 50, 50);

    //Labels
    private JLabel questionLabel = new JLabel("");
    private JLabel numberLabel = new JLabel("");
    private JLabel correctLabel = new JLabel("Correct!");
    private JLabel incorrectLabel = new JLabel("");
    private JLabel answerLabel = new JLabel("Click An Answer:");
    private JLabel completedLabel = new JLabel("YOU HAVE COMPLETED ALL OPTICS QUESTIONS!");
    private JLabel scoreLabel;
    private String searchText = "";
    private int scoreCount = 0;
    private Formatter formatter;

    //booleans
    private boolean qRemoved = true;
    private boolean qbackground = false;

    //questions
    private Question q1 = new Question("A light beam with a speed of 2.75x₁₀⁸ m/s moves from one medium into " +
            "another at an angle of 45 degrees from the normal. After it moves inside the other medium, it moves at an " +
            "angle of 30 degrees to the normal. What is its speed in the second medium?", "1.94x₁₀⁸ m/s", "1.98x₁₀⁸ m/s", "2.94x₁₀⁸ m/s", "1.84x₁₀⁸ m/s");
    private Question q2 = new Question("A survivor from a shipwreck sees an image of a fish in the water. Where " +
            "must she aim with her spear to catch the fish?", "below", "above", "anywhere you want", "She doesn't like fish");
    private Question q3 = new Question("A light ray has an angle of incidence of 34º. The reflected ray will " +
            "make what angle with the reflecting surface?", "34", "25", "63", "61");
    private Question q4 = new Question("What is the index of refraction for an object in which light travels " +
            "at 1.97 x ₁₀⁸ m/s.", "7.4", "2.51", "1.52", "6.18");
    private Question q5 = new Question("The focal length of a converging lens is 15 cm. An object is placed 45 " +
            "cm away from the lens. How will the image appear?", "7cm", "22.5cm", "25cm", "73cm");
    private Question q6 = new Question("An object is 14 cm in front of a convex mirror. The image is 5.8 cm behind" +
            " the mirror. What is the focal length of the mirror?", "-4.1 cm", "-5.0 cm", "2.3 cm", "1.4 cm");
    private Question q7 = new Question("A laser beam strikes a plane’s reflecting surface with an angle of " +
            "incidence of 37°. What is the angle between the incident ray and the reflected ray?", "74°", "81°", "85°", "72°");
    private Question q8 = new Question("Is it possible to see a virtual image?", "Yes", "No", "Possibly?", "Believe and it shall be.");

    //lists
    private ArrayList<Question> qList;
    private ArrayList<String> answerList = new ArrayList<>();
    private int selection = -1;

    //constructor
    public QuestionsOptics() {
        setLayout(null);

        add(new ExitButton().getExitButton());
        add(addReturnButton());
        add(addBeginButton());

        add(addNumberLabel(numberLabel));

    }

    //add the return button method
    public CustomButton addReturnButton() {
        CustomButton returnButton = new CustomButton("GO BACK");
        returnButton.setFont(fieldFont);
        returnButton.setBounds(20, 80, 200, 40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Mechanics.frame.remove(Mechanics.questionsOptics);
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

    //add the next button between questions
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

    //add the finish button
    public CustomButton addFinishButton() {
        CustomButton finishButton = new CustomButton("FINISH");
        finishButton.setFont(fieldFont);
        finishButton.setBounds(300, 750, 300, 100);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                Mechanics.scoreOptics = scoreCount;

                //save score count to a text file
                IntroPanel.accounts.get(IntroPanel.accountNum - 1).setOpticsScore(scoreCount);

                try {
                    formatter = new Formatter("filename.txt");
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
                for (int i = 0; i < IntroPanel.accounts.size(); i++) {
                    formatter.format("%s%s%s%s%s%s", IntroPanel.accounts.get(i).getUsername() + "~", IntroPanel.accounts.get(i).getPassword() + "`", IntroPanel.accounts.get(i).getKinematicsScore() + "{", IntroPanel.accounts.get(i).getDynamicsScore() + "}", IntroPanel.accounts.get(i).getOpticsScore() + "[", IntroPanel.accounts.get(i).getElectricityScore() + "]");
                    if (i != IntroPanel.accounts.size() - 1) {
                        formatter.format(System.getProperty("line.separator"));
                    }
                }
                formatter.close();

                //score label
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

    //add final buttons
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
                Mechanics.frame.remove(Mechanics.questionsOptics);
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

        //add action listeners
        questionButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selection = 0;
                removeButtons(questionButton1, questionButton2, questionButton3, questionButton4);
            }
        });

        questionButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selection = 1;
                removeButtons(questionButton1, questionButton2, questionButton3, questionButton4);
            }
        });

        questionButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selection = 2;
                removeButtons(questionButton1, questionButton2, questionButton3, questionButton4);
            }
        });

        questionButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selection = 3;
                removeButtons(questionButton1, questionButton2, questionButton3, questionButton4);
            }
        });
    }

    //remove labels method
    public void removeLabels() {
        remove(correctLabel);
        remove(incorrectLabel);
    }

    //remove buttons method
    public void removeButtons(CustomButton questionButton1, CustomButton questionButton2, CustomButton questionButton3, CustomButton questionButton4) {
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
                System.out.println("hello");
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

    //shuffle answers
    public void shuffleAnswers(ArrayList<String> list) {
        list.clear();
        list.add(qList.get(0).getAnswer());
        list.add(qList.get(0).getWrongSelection1());
        list.add(qList.get(0).getWrongSelection2());
        list.add(qList.get(0).getWrongSelection3());
        Collections.shuffle(list);

    }

    //paint component method
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1280);

        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("OPTICS QUESTIONS", (mechanics.getScreenWidth() / 4), 150);

        if (!qRemoved) {
            g.setColor(grey);
            g.fillRect(280, 610, 340, 340);
        }

        if (qbackground) {
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