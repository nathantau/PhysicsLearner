
import javax.swing.*;
import java.awt.*;

/**
 * Created by aun4606 on 12/5/2017.
 */
class Mechanics {

    // Global variables
    static int scoreKinematics;
    static int scoreOptics;
    static int scoreElectricity;
    static int scoreDynamics;

    // Main Panels
    static JFrame frame = new JFrame();
    static IntroPanel introPanel = new IntroPanel();
    static MenuPanel menuPanel = new MenuPanel();

    // Menu Panel
    static ProgressMenu progressMenu = new ProgressMenu();
    static TutorialMenu tutorialMenu = new TutorialMenu();
    static QuestionsMenu questionsMenu = new QuestionsMenu();

    // Kinematics Menus
    static OneDMotionPanel oneDMotionPanel = new OneDMotionPanel();
    static TwoDMotionPanel twoDMotionPanel = new TwoDMotionPanel();
    static ProjectileMotionPanel projectileMotionPanel = new ProjectileMotionPanel();

    // Dynamics Menu
    static DynamicsMenu dynamicsMenu = new DynamicsMenu();

    // Electricity Menus
    static CircuitsPanel circuitsPanel = new CircuitsPanel();

    // Question Menus
    static QuestionsKinematics questionsKinematics = new QuestionsKinematics();
    static QuestionsDynamics questionsDynamics = new QuestionsDynamics();
    static QuestionsElectricity questionsElectricity = new QuestionsElectricity();
    static QuestionsOptics questionsOptics = new QuestionsOptics();

    // Optics Menus
    static SnellsPanel snellsPanel = new SnellsPanel();

    // Panel Dimensions
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenHeight = (int) screenSize.getHeight();
    private int screenWidth = (int) screenSize.getWidth();

    static final int WIDTH = 1920;
    static final int HEIGHT = 1280;

    // Get Methods
    int getScreenHeight() {
        return screenHeight;
    }

    int getScreenWidth() {
        return screenWidth;
    }

    // Main Method
    static void main(String[] args) {
        addJFrame();
    }

    // Method to add the frame
    static void addJFrame() {
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.add(introPanel);
    }

}