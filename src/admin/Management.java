package admin;
import management.*;
import java.util.Scanner;


public class Management extends Admin {
    static Scanner sc = new Scanner(System.in);
    public Management(String name,String username, String password){
        super(name,"Management Admin", username, password);
    }

    public void scheduleMatchInFixture(){
        
        System.out.println("Enter the sport name");
        String sport = sc.nextLine();
        System.out.println("Enter the team 1 name");
        String team1 = sc.nextLine();
        System.out.println("Enter the team 2 name");
        String team2 = sc.nextLine();
        System.out.println("Enter the match date");
        String date = sc.nextLine();
        System.out.println("Enter the match time");
        String time = sc.nextLine();

        Fixture fixture = new Fixture(sport, team1, team2, date, time);
        fixture.saveFixture();
    }

    public static void viewFixture(){
        System.out.println("Enter the sport name");
        String sport = sc.nextLine();

        Fixture f1 = new Fixture(sport);
        f1.viewFixture();
    }

    public void deleteFixture(){
        System.out.println("Enter the sport name");
        String sport = sc.nextLine();

        Fixture f1 = new Fixture(sport);
        f1.deleteFixture();
    }
}