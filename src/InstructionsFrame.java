import javax.swing.*;
import java.awt.*;

public class InstructionsFrame extends JFrame {

    //Constructor which determines what frame to go to
    public InstructionsFrame(int x){
        setVariables();
        if(x == 0) {
            add(new InstructionsPanel());
        }
        else if(x == 1){
            add(new InstructionsOneDMotionPanel());
        }
        else if(x == 2){
            add(new InstructionsTwoDMotionPanel());
        }
        else if(x == 3){
            add(new InstructionsProjectilePanel());
        }
        else if(x == 4){
            add(new InstructionsCircuitsPanel());
        }
        else if(x == 5){
            add(new InstructionsRefractionPanel());
        } else if (x == 6) {
            add(new InstructionsDynamicsPanel());
        }
    }

    //Set the frame components
    public void setVariables(){
        setPreferredSize(new Dimension(1920, 1080));
        setBackground(Color.black);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }

}