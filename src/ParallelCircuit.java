public class ParallelCircuit extends Circuit {

    //A ParallelCircuit extends the Circuit class
    public ParallelCircuit(double batteryVoltage, double resistance1, double resistance2) { //you have to enter 2 resistances to make 2 resistors when you make a parallel circuit
        super(batteryVoltage, resistance1, resistance2);
    }

    //These 2 methods uses Ohm's Law and circuit rules to return the current values passing through the respective resistors
    @Override
    double getCurrent1() {
        return getBatteryVoltage() / getResistor1().getResistance();
    }

    @Override
    double getCurrent2() {
        return getBatteryVoltage() / getResistor2().getResistance();

    }

    //The voltage across resistors in parallel are the same to each other
    @Override
    public double getVoltage1() {
        return getBatteryVoltage();
    }

    @Override
    public double getVoltage2() {
        return getBatteryVoltage();
    }

    //This uses's Ohm's Law to find the total current
    @Override
    double getTotalCurrent() {
        return getBatteryVoltage() / getTotalResistance();
    }

    @Override
    double getTotalResistance() { // this specifically calculates total resistance for parallel circuits
        return 1 / (1 / getResistor1().getResistance() + 1 / getResistor2().getResistance());
    }

}