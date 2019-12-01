public class SeriesCircuit extends Circuit {

    //A SeriesCircuit extends the Circuit class
    public SeriesCircuit(double batteryVoltage, double resistance1, double resistance2) {
        super(batteryVoltage, resistance1, resistance2);
    }

    //These 2 methods uses Ohm's Law and circuit rules to return the voltage values across the respective resistors
    @Override
    public double getVoltage1() {
        return getResistor1().getResistance() * getBatteryVoltage() / (getResistor1().getResistance() + getResistor2().getResistance());
    }

    @Override
    public double getVoltage2() {
        return getResistor2().getResistance() * getBatteryVoltage() / (getResistor1().getResistance() + getResistor2().getResistance());
    }

    //The current in a series circuit is the same everywhere
    @Override
    public double getCurrent1() {
        return getTotalCurrent();
    }

    @Override
    public double getCurrent2() {
        return getTotalCurrent();
    }

    //This uses's Ohm's Law to find the total current
    @Override
    double getTotalCurrent() {
        return getBatteryVoltage() / getTotalResistance();
    }

    //The total resistance in a series circuit is equal to the sum of all the resistances
    @Override
    double getTotalResistance() {
        return getResistor1().getResistance() + getResistor2().getResistance();
    }

}