package sports;

public class Sport {

    private String sportName;
    private int maxNumOfPlayers;
    private double registrationFees;
    private double firstPrize;
    private double secondPrize;

    public Sport(){
        sportName = "Unknown";
        maxNumOfPlayers = 0;
        registrationFees = 0.0;

        firstPrize = 0.0;
        secondPrize = 0.0;
    }

    public Sport(String sportName ,int maxNumOfPlayers ,double registrationFees ,double firstPrize ,double secondPrize ){
        this.sportName = sportName;
        this.maxNumOfPlayers = maxNumOfPlayers;
        this.registrationFees = registrationFees;
        this.firstPrize = firstPrize;
        this.secondPrize = secondPrize;
    }
    public String getSportName(){
        return sportName;
    }
    public int getmaxPlayers(){
        return maxNumOfPlayers;
    }
    public double getFees(){
        return registrationFees;
    }
    public double getFirstPrize(){
        return firstPrize;
    }
    public double getSecondPrize(){
        return secondPrize;
    }
    public void viewSportDetails(){
        System.out.println("Sport Name: " + sportName);
        System.out.println("Max Number of Players: " + maxNumOfPlayers);
        System.out.println("Registration Fees: " + registrationFees);
        System.out.println("First Prize: " + firstPrize);
        System.out.println("Second Prize: " + secondPrize);
    } 
}
