public class Account {

    //Variables
    private String username, password;
    private int kinematicsScore, dynamicsScore, opticsScore, electricityScore;

    //Constructor
    public Account (String username, String password, int kinematicsScore, int dynamicsScore, int opticsScore, int electricityScore){
        this.username = username;
        this.password = password;
        this.kinematicsScore = kinematicsScore;
        this.dynamicsScore = dynamicsScore;
        this.opticsScore = opticsScore;
        this.electricityScore = electricityScore;
    }
    //set Methods
    public void setKinematicsScore(int kinematicsScore){
        this.kinematicsScore = kinematicsScore;
    }
    public void setDynamicsScore(int dynamicsScore){
        this.dynamicsScore = dynamicsScore;
    }
    public void setOpticsScore(int opticsScore){
        this.opticsScore = opticsScore;
    }
    public void setElectricityScore(int electricityScore){
        this.electricityScore = electricityScore;
    }



    //Get Methods
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getKinematicsScore(){
        return kinematicsScore;
    }
    public int getDynamicsScore(){
        return dynamicsScore;
    }
    public int getOpticsScore(){
        return opticsScore;
    }
    public int getElectricityScore(){
        return electricityScore;
    }

    
}
