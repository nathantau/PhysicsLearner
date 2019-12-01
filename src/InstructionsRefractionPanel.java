import javax.swing.*;
import java.awt.*;

public class InstructionsRefractionPanel extends JPanel {

    //Instructions
    private Color grey = new Color(50, 50, 50);
    private Font titleFont = new Font("TimesRoman", Font.BOLD, 60);
    private Font equationFont = new Font("TimesRoman", Font.ITALIC, 35);
    private Font fieldFont = new Font("TimesRoman", Font.BOLD, 20);

    //Constructor
    public InstructionsRefractionPanel(){
        setBackground(Color.black);
    }

    //Paint method
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("Refraction", 50, 100);

        //Draw background
        g.setColor(grey);
        g.fillRect(30, 290, 1400, 200);
        g.fillRect(30, 510, 1400, 100);
        g.setColor(Color.white);
        g.drawRect(30, 290, 1400, 200);
        g.drawRect(30, 510, 1400, 100);

        //Draw text
        g.setColor(Color.white);
        g.setFont(fieldFont);
        g.drawString("When light travels from one medium to another, it is bent. This phenomenon is called refraction.",50,150);
        g.drawString("The white line in the middle of the screen is the normal (perpendicular line) to the interface of the two mediums.",50,180);
        g.drawString("The angle of incidence is the angle between the normal and the incident ray.",50,210);
        g.drawString("The angle of refraction is the angle between the normal and the refracted ray.",50,240);
        g.setFont(equationFont);
        g.drawString("Instructions:", 50, 330);
        g.drawString("Swap the two mediums and see what happens.",50,370);
        g.setFont(fieldFont);
        g.drawString("Notice that light bends towards the normal when traveling from a medium of lower index of refraction to a medium with a higher one.",50,410);
        g.drawString("Light bends away from the normal when traveling from a medium of higher index of refraction to a medium with a lower one.",50,440);
        g.drawString("The relationship between the angles incidence, angle of refraction, and the refractive indices of the two mediums is modeled by:",50,470);
        g.setFont(equationFont);
        g.drawString("Snell's Law: n1*sin(ai) = n2*sin(aR)",50,550);
        g.setFont(fieldFont);
        g.drawString("where n1 and n2 are the indices of refraction of the two respective mediums, ai is the angle of incidence, and aR is the angle of refraction.",50,580);

        drawImages(g);
    }

    //Draw images method
    public void drawImages(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(200, 700, 300, 100);

        g.setColor(grey);
        g.fillRect(200, 800, 300, 100);

        g.setColor(Color.white);
        g.drawLine(350, 700, 350, 900);

        g.setColor(Color.blue);
        g.fillRect(600, 700, 300, 100);

        g.setColor(grey);
        g.fillRect(600, 800, 300, 100);

        g.setColor(Color.red);
        g.drawLine(750, 700, 750, 900);

        g.setColor(Color.white);
        g.drawLine(690, 700, 750, 800);
        g.drawLine(750, 800, 850, 900);
    }
}
