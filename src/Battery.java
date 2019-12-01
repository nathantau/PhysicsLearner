public class Battery {



    private double voltage; //battery only has voltage
    private boolean closed; //whether the battery is on or not, this decides if the resistor lights up

    public Battery(double voltage) {
        this.voltage = voltage;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    //Checks if the circuit is closed (running)
    public boolean isClosed() {
        return closed;
    }

    //Sets whether the circuit is closed
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

}