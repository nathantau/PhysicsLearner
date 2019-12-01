public abstract class Kinematics {

    //objects will intrinsically have velocities in the x and y directions and accelerations in the x and y directions, they can also possess a launch angle if it's projectile motion
    private double vX, aX, vY, aY;
    private static final double g = 15 ; //acceleration due to gravity set as 15 for the purpose of this program. It is displayed as 9.8 for the user.
    private double angle; // this is the angle from the horizontal axis
    private double xCoordinate, yCoordinate; //the initial coordinate of the moving object 

    //constructor 1
    public Kinematics(double vX, double vY, double aX, double aY, double xCoordinate, double yCoordinate) { //this constructor is used for non-projectile motion
        this.vX = vX;
        this.vY = vY;
        this.aX = aX;
        this.aY = aY;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    //constructor 2
    public Kinematics(double velocity, double angle, double xCoordinate, double yCoordinate) { //this constructor is used for projectile motion 
        setComponents(velocity, angle);
        this.angle = angle;
        this.aY = -g;
        this.aX = 0;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    //set method
    public void setComponents(double velocity, double angle) { //calculates the components of the velocities for projectile motion
        vX = velocity * Math.cos(Math.toRadians(angle));
        vY = velocity * Math.sin(Math.toRadians(angle));
    }

    //get method for horizontal acceleration
    public double getaX() {
        return aX;
    }

    //get method for vertical acceleration
    public double getaY() {
        return aY;
    }

    //get method for horizontal velocity
    public double getvX() {
        return vX;
    }

    //get method for vertical velocity
    public double getvY() {
        return vY;
    }

    //get method for x coordinate of moving object
    public double getxCoordinate() {
        return xCoordinate;
    }

    //get method for y coordinate of moving object
    public double getyCoordinate() {
        return yCoordinate;
    }

    //set method for the launch angle of projectile
    public void setAngle(double angle) {
        this.angle = angle;
    }

    //set method for the x coordinate
    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    //set method for the y coordinate
    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    // returns the acceleration due to gravity
    public static double getG() {
        return g;
    }

    public double getAngle() {
        return angle;
    }

    //set method for the vertical velocity
    public void setvY(double vY) {
        this.vY = vY;
    }

    //set method for the horizontal velocity
    public void setvX(double vX) {
        this.vX = vX;
    }

    //set method for the vertical acceleration
    public void setaY(double aY) {
        this.aY = aY;
    }

    //set method for the horizontal acceleration
    public void setaX(double aX) {
        this.aX = aX;
    }

    //get method for the instanteous displacement of the object in the horizontal direction
    public double getInstantHorizontalDisplacement(double time) { //this returns the instant displacement in the x direction 
        return vX * time + 1 / 2 * aX * time * time;
    }

    //get method for the instanteous displacement of the object in vertical direction
    public double getInstantVerticalDisplacement(double time) { //this returns the instant displacement in the y direction 
        return vY * time + 1 / 2 * aY * time * time;
    }

    //get method
    public double getFinalNetDisplacement(double time) { //this returns the net displacement of an object after a certain amount of time 
        double dX, dY;
        dX = vX * time + 1 / 2 * aX * time * time;
        dY = vY * time + 1 / 2 * aY * time * time;
        return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
    }

    //get method
    public double getFinalProjectileDisplacement() { // THIS GETS THE FINAL HORIZONTAL DISPLACEMENT OF A PROJECTILE
        double time;
        time = -2 * vY / aY;
        return vX * time;
    }

    //get method for the max height of a projectile
    public double getMaxHeight() { // THIS GETS THE MAX HEIGHT OF THE PROJECTILE AFTER YOU MAKE A PROJECTILE OBJECT 
        return -(Math.pow(vY, 2)) / (2 * aY);
    }

    //get method for the instantaneous hight of a projectile
    public double getInstantProjectileHeight(double time) { // This get's the instantaneous height at a moment in time USE FOR PROJECTILE
        return vY * time + (1 / 2) * aY * time * time;
    }

    //get method for the instantaneous horizontal displacement of a projectile
    public double getInstantProjectileDisplacement(double time) { //This get's the instantaneous displacement at a moment in time USE FOR PROJECTILE
        return vX * time;
    }

}