package admin;
import sports.*;
import java.io.*;
import java.util.Scanner;

public class Core extends Admin {

    public Core(String name, String username, String password){
        super(name, "Core Admin", username, password);
    }

    private static String fileName = "data/sports.txt";
    Scanner scanner = new Scanner(System.in);
    public void addSport(){

        System.out.print("Enter sport name: ");
        String name = scanner.nextLine();
        System.out.print("Enter max number of players: ");
        int maxPlayers = scanner.nextInt();
        System.out.print("Enter registration fees: ");
        double fees = scanner.nextDouble();
        System.out.print("Enter first prize amount: ");
        double firstPrize = scanner.nextDouble();
        System.out.print("Enter second prize amount: ");
        double secondPrize = scanner.nextDouble();
        scanner.nextLine();
        // scanner.close();

        Sport sport = new Sport(name, maxPlayers, fees, firstPrize, secondPrize);
        try(FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw)){
                bw.write(sport.getSportName() + ","+ sport.getmaxPlayers() + ","+ sport.getFees()  + ","+  sport.getFirstPrize() + ","+ sport.getSecondPrize() );
                bw.newLine();
                System.out.println("Sport saved successfully");
        }catch(IOException e){
            System.out.println("Error while savinf sport : " + e.getMessage());
        }
    }
    public void removeSport(){

        System.out.print("Enter sport name: ");
        String name = scanner.nextLine();
        // sc.close();
        try{
            File file = new File(fileName);
            File temp = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if(parts[0].equalsIgnoreCase(name)){
                    found = true;
                    continue;
                }
                bw.write(line);
                bw.newLine();
            }
            br.close();
            bw.close();

            if(found){
                file.delete();
                temp.renameTo(file);
                System.out.println("Sport removed successfully");
            }else{
                temp.delete();
                System.out.println("Sport not found");
            }
        }catch(IOException e){
            System.out.println("Error while removing sport : " + e.getMessage());
        }
    }

    public static void viewSportList(){
        try(FileReader fr = new FileReader("data/sports.txt");
            BufferedReader br = new BufferedReader(fr)    
        ){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);    
            }
        }catch(IOException e){
            System.out.println("Failed to view sports list : " + e.getMessage());
        }
    }
}