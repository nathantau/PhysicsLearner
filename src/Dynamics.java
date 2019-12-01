import java.text.DecimalFormat;

public class Dynamics {

    // Objects will intrinsically have velocities in the x and y directions and accelerations in the x and y directions, they can also possess a launch angle if it's projectile motion
    private double xVelocity, xAcceleration, time;
    private double netForce;
    private int force, mass;
    private int xCoordinate = 100; // Initial coordinate of the moving object
    private double coefficient;
    
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.##");
    
    // constructor
    public Dynamics() { // this constructor is used for non-projectile motion
    }

    //  get net force
    public void setNetForce() {
        this.netForce = this.force - this.coefficient * this.mass * 9.81;
    }
    
    // get acceleration method
    public String getxAcceleration() {
        return DECIMAL_FORMAT.format(this.xAcceleration);
    }

    // get veloctiy (string) method
    public String getvXInstant() {
        return DECIMAL_FORMAT.format (this.xVelocity);
    }

    // get velocity method
    public double getXVelocity() {
    return this.xVelocity;
}
    // get x-coordinate method
    public int getxCoordinate() {
        return this.xCoordinate;
    }

    // get force applied method
    public int getForce() {
        return this.force;
    }

    // get mass method
    public int getMass() {
        return this.mass;
    }
    
    // get net force method
    public String getNetForce() {
        return DECIMAL_FORMAT.format(netForce);
    }

    // get coefficient of friction method 
    public double getCoefficient() {
        return this.coefficient;
    }

    // set x-coordinate method
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    // set velocity method
    public void setvX(double vX, double time) {
        this.xVelocity = xAcceleration * time;
    }

    // set velocity (at start) method
    public void setvX () {
        this.xVelocity = 0;
    }
           
    // set mass method
    public void setMass(int mass) {
        this.mass = mass;
    }

    // set force applied method
    public void setForce(int force) {
        this.force = force;
    }

    // set acceleration method
    public void setaX() {
        this.xAcceleration = this.netForce / this.mass;
    }

    // set acceleration (at start) method
    public void setaXReset() {
        this.xAcceleration = 0;
    }
    
    // set coefficient of friction method
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    // get instantaneous x-coordinate method 
    public double getHorizontalDisplacement(final double time) { // this returns the instant displacement in the x direction
        return this.xVelocity * time + 0.5 * this.xAcceleration * time * time;
    }

    // set time method
    public void setTime(double time) {
        this.time = time;
    }
    
    // get time method
    public String getTime() {
        return DECIMAL_FORMAT.format(time);
    }
}