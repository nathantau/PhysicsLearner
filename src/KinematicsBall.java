public class KinematicsBall extends Kinematics {

    //This models the ball object that is used to demonstrate 1-D, 2-D. and projectile motion.
    //It inherits from the kinematics class

    //constructor for projectile motion ball
    public KinematicsBall(double velocity, double angle, double xCoordinate, double yCoordinate) {
        super(velocity, angle, xCoordinate, yCoordinate);
    }

    //Constructor for 1-D and 2-D motion ball
    public KinematicsBall(double vX, double vY, double aX, double aY, double xCoordinate, double yCoordinate) {
        super(vX, vY, aX, aY, xCoordinate, yCoordinate);
    }

}