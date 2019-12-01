
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by aun4606 on 12/5/2017.
 */
public class Mechanics  {

    //global variables
    public static int scoreKinematics;
    public static int scoreOptics;
    public static int scoreElectricity;
    public static int scoreDynamics;

    //Main Panels
    public static JFrame frame = new JFrame();
    public static IntroPanel introPanel = new IntroPanel();
    public static MenuPanel menuPanel = new MenuPanel();

    //Menu Panel
    public static ProgressMenu progressMenu = new ProgressMenu();
    public static TutorialMenu tutorialMenu = new TutorialMenu();
    public static QuestionsMenu questionsMenu = new QuestionsMenu();

    //Kinematics Menus
    public static OneDMotionPanel oneDMotionPanel = new OneDMotionPanel();
    public static TwoDMotionPanel twoDMotionPanel = new TwoDMotionPanel();
    public static ProjectileMotionPanel projectileMotionPanel = new ProjectileMotionPanel();

    //Dynamics Menu
    public static DynamicsMenu dynamicsMenu = new DynamicsMenu();

    //Electricity Menus
    public static CircuitsPanel circuitsPanel = new CircuitsPanel();

    //Question Menus
    public static QuestionsKinematics questionsKinematics = new QuestionsKinematics();
    public static QuestionsDynamics questionsDynamics = new QuestionsDynamics();
    public static QuestionsElectricity questionsElectricity = new QuestionsElectricity();
    public static QuestionsOptics questionsOptics = new QuestionsOptics();

    //Optics Menus
    public static SnellsPanel snellsPanel = new SnellsPanel();

    //Panel Dimensions
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenHeight = (int)screenSize.getHeight();
    private int screenWidth = (int)screenSize.getWidth();

    //Get Methods
    public int getScreenHeight(){
        return screenHeight;
    }
    public int getScreenWidth(){
        return screenWidth;
    }

    //Main Method
    public static void main(String[] args){

        addJFrame();

    }

    //Method to add the frame
    public static void addJFrame(){
        frame.setPreferredSize(new Dimension(1920, 1280));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.add(introPanel);
    }

}