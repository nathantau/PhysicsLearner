public abstract class Circuit { //this is an abstract class because there are parallel and series circuits

    private Battery battery; // must have one battery
    private Resistor r1, r2; // the 2 resistors of the circuit

    //A circuit has one battery and 2 resistors
    public Circuit(double batteryVoltage, double resistance1, double resistance2) {
        this.battery = new Battery(batteryVoltage);
        this.r1 = new Resistor(resistance1);
        this.r2 = new Resistor(resistance2);
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public double getBatteryVoltage() {
        return battery.getVoltage();
    }

    public Battery getBattery() {
        return battery;
    }

    public Resistor getResistor1() {
        return r1;
    }

    public Resistor getResistor2() {
        return r2;
    }

    abstract double getVoltage1();

    abstract double getVoltage2();

    abstract double getCurrent1();

    abstract double getCurrent2();

    abstract double getTotalCurrent();

    abstract double getTotalResistance();

}