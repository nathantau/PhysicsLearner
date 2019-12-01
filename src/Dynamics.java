import java.text.DecimalFormat;

public class Dynamics {

    //objects will intrinsically have velocities in the x and y directions and accelerations in the x and y directions, they can also possess a launch angle if it's projectile motion
    private double vX, aX, time;
    private double netForce;
    private int force, mass;
    private int xCoordinate = 100; //the initial coordinate of the moving object 
    private double coefficient;
    DecimalFormat f = new DecimalFormat("##.##");
    
    //constructor
    public Dynamics() { //this constructor is used for non-projectile motion
    }

    //get net force
    public void setNetForce() {
        this.netForce = force - coefficient * mass * 9.81;
    }
    
    //get acceleration method
    public String getaX() {
        return f.format(aX);
    }

    //get veloctiy (string) method
    public String getvXInstant() {
        return f.format (vX);
    }

    //get velocity method
    public double getvX () {
    return vX;
}
    //get x-coordinate method
    public int getxCoordinate() {
        return xCoordinate;
    }

    //get force applied method
    public int getForce() {
        return force;
    }

    //get mass method
    public int getMass() {
        return mass;
    }
    
    //get net force method
    public String getNetForce() {
        return f.format(netForce);
    }

    //get coefficient of friction method 
    public double getCoefficient() {
        return coefficient;
    }

    //set x-coordinate method
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    //set velocity method
    public void setvX(double vX, double time) {
        this.vX = aX * time;
    }

    //set velocity (at start) method
    public void setvX () {
        this.vX = 0;
    }
           
    //set mass method
    public void setMass(int mass) {
        this.mass = mass;
    }

    //set force applied method
    public void setForce(int force) {
        this.force = force;
    }

    //set acceleration method
    public void setaX() {
        this.aX = netForce / mass;
    }

    //set acceleration (at start) method
    public void setaXReset() {
        this.aX = 0;
    }
    
    //set coefficient of friction method
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    //get instantaneous x-coordinate method 
    public double getHorizontalDisplacement(double time) { //this returns the instant displacement in the x direction
        return  vX * time + 1/2 * aX * time * time;
    }

    //set time method
    public void setTime(double time) {
        this.time = time;
    }
    
    //get time method
    public String getTime() {
        return f.format(time);
    }
}