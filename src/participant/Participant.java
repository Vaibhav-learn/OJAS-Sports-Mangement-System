package participant;
import java.io.*;
import java.util.Scanner;
import user.User;
public class Participant extends User{
    String name;
    Scanner sc = new Scanner(System.in);
    public Participant(String name,String username,String password){
        super(username, password);
        this.name = name;
    }
    public void viewDetails(){
        System.out.println("Name : "+name+"\nUsername : "+username);
    }
    private void savePlayer(String sport, String name , long id , String course){
        try(FileWriter fw = new FileWriter("data/player.txt",true);
            BufferedWriter bw = new BufferedWriter(fw)
        ){
            bw.write(sport + ","+name+","+id+","+course);
            bw.newLine();
        }catch(IOException e){
            System.out.println("Failed to save details of  player : "+ e.getMessage());
        }
    }
    private int getNumOfPlayers(String sport){
        int num = -1;
        try(FileReader fr = new FileReader("data/sports.txt");
            BufferedReader br = new BufferedReader(fr)
        ){
            String line;
            while((line = br.readLine()) != null){
                String info[] = line.split(",");
                if(sport.equalsIgnoreCase(info[0])){
                    num = Integer.parseInt(info[1]);
                }
            }
        }catch(IOException e){
            System.out.println("Failed to get number of players : "+ e.getMessage());
        }
        return num;
    }
    public void viewEachSportsInfo(){
        admin.Core.viewSportList();
    }
    public void viewFixture(){
        admin.Management.viewFixture();
    }
    public void participate(){
        System.out.println("Enter the sport you wish to participate in : " );
        String sport = sc.nextLine();
        int maxNum = getNumOfPlayers(sport);
        System.out.println("You can enter the details of only "+maxNum+" players for this respective sport");
        System.out.println("How many players do you wish to enter? ");
        int actualNum = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the details of each player : ");
        for(int i = 1; i<=actualNum ; i++){
            System.out.println("Enter name of player "+i+" :");
            String name2 = sc.nextLine();
            System.out.println("Enter collegeId of player "+i+" :");
            long id = sc.nextLong();
            sc.nextLine();
            System.out.println("Enter course of player "+i+" :");
            String course = sc.nextLine();
            Player p1 = new Player(sport,name2, id, course);
            savePlayer(sport, name2, id, course);
            System.out.println("Player "+i+" added successfully");
        }
    }
}