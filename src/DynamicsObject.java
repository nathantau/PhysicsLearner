/**
 *
 * @author ZhaoA0388
 */

// dynamics object (used to display exact velocity and time for periodical dots)
public class DynamicsObject {
    
    private int xCoordinate;
    private String time;
    private String vX;

    // constructor
    public DynamicsObject(int xCoordinate, String time, String vX) {
        this.xCoordinate = xCoordinate;
        this.time = time;
        this.vX = vX;
    }

    // contructor (empty)
    public DynamicsObject() {
    }

    // get velocity
    public String getvX() {
        return vX;
    }

    // get time
    public String getTime() {
        return time;
    }

    // get x coordinate
    public int getxCoordinate() {
        return xCoordinate;
    }

    // set x coordinate 
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    // set time
    public void setTime(String time) {
        this.time = time;
    }

    // set velocity
    public void setvX(String vX) {
        this.vX = vX;
    }
}
