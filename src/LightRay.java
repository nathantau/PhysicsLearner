//This is for Snell's law refraction going from one medium to another
public class LightRay {

    private double angleIn, angleOut; //angle of incidence and refraction
    private double index1, index2; //indices of refraction for the 2 mediums
    private double length; // length of the light ray
    private final double xMiddle = 1200, yMiddle = 600; //This is the point in the middle on the medium line where the incident ray ends and where the refracted ray starts
    private double xStart, yStart, xEnd, yEnd; //These are the coordinate values for the points that locate the line segment representing the light rays

    public LightRay(double angleIn, double index1, double index2, double length) {
        this.angleIn = angleIn;
        this.index1 = index1;
        this.index2 = index2;
        this.length = length;
        setCoordinates();
    }

    //This is intended to set some necessary coordinates of the ray, there are 2 points that are taken care of
    public void setCoordinates() {
        xStart = xMiddle - length * Math.sin(Math.toRadians(angleIn));
        yStart = yMiddle - length * Math.cos(Math.toRadians(angleIn));
        xEnd = xMiddle + length * Math.sin(Math.toRadians(getRefractionAngle()));
        yEnd = yMiddle + length * Math.cos(Math.toRadians(getRefractionAngle()));

    }

    //This is the method that sets coordinates based on an input of angleOut
    public void setCoordinates2(double angleOut) {
        xStart = xMiddle - length * Math.sin(Math.toRadians(getRefractionAngle2(angleOut)));
        yStart = yMiddle - length * Math.cos(Math.toRadians(getRefractionAngle2(angleOut)));
        xEnd = xMiddle + length * Math.sin(Math.toRadians(angleOut));
        yEnd = yMiddle + length * Math.cos(Math.toRadians(angleOut));
    }

    //Checks whether total internal reflection occurs
    public boolean checkTotalInternalReflection() {
        double criticalAngle;
        if (index1 > index2) {
            criticalAngle = Math.toDegrees(Math.asin(index2 / index1));
            if (angleIn >= criticalAngle) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //Returns the value of the critical angle based on Snell's Law
    public double getCriticalAngle(){
            return Math.toDegrees(Math.asin(index2/index1));
    }

    //Calculates the angle of refraction
    public double getRefractionAngle() {
        return Math.toDegrees(Math.asin(index1 / index2 * Math.sin(Math.toRadians(angleIn))));
    }

    //This method is to get the angleIn based on angleOut, understand that "2" always corresponds to OUT whereas "1" always corresponds to IN
    public double getRefractionAngle2(double angleOut) {
        return Math.toDegrees(Math.asin(index2 / index1 * Math.sin(Math.toRadians(angleOut))));
    }

    //This sets the refraction angle and automatically adjusts the line coordinates based on it
    public void setAngleOut(double angleOut) {
        this.angleOut = angleOut;
        setCoordinates2(angleOut);
    }

    //This sets the angle of incidence and automatically adjusts the line coordinates based on it
    public void setAngleIn(double angleIn) {
        this.angleIn = angleIn;
        setCoordinates();
    }

    //These are the get and set methods for all the states of the LightRay class
    public void setIndex1(double index1) {
        this.index1 = index1;
    }

    public void setIndex2(double index2) {
        this.index2 = index2;
    }

    public double getAngleIn() {
        return angleIn;
    }

    public double getIndex1() {
        return index1;
    }

    public double getIndex2() {
        return index2;
    }

    public double getLength() {
        return length;
    }

    public double getxMiddle() {
        return xMiddle;
    }

    public double getyMiddle() {
        return yMiddle;
    }

    public double getxStart() {
        return xStart;
    }

    public double getyStart() {
        return yStart;
    }

    public double getxEnd() {
        return xEnd;
    }

    public double getyEnd() {
        return yEnd;
    }

}