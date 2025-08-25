package management;

import java.io.*;

public class Fixture {
    String sportname;
    String team1;
    String team2;
    String date;
    String time;
    String filename;

    public Fixture(String sportname ,String team1 , String team2 , String date , String time){
        this.sportname = sportname;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
        this.filename = "data/" + sportname.toLowerCase() + "_fixture.txt";
    }

    public Fixture(String sportname){
        this.sportname = sportname;
        this.filename = "data/" + sportname.toLowerCase() + "_fixture.txt";
    }

    public void saveFixture(){
        try(FileWriter fw = new FileWriter(filename, true); 
            BufferedWriter bw = new BufferedWriter(fw)){
                bw.write(team1 + " vs " + team2 + "  "+ date + "  "+ time );
                bw.newLine();
                System.out.println("Match scheduled");
        }catch(IOException e){
            System.out.println("Failed to save fixture : "+ e.getMessage());
        }
    }
    public void viewFixture(){
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No fixture found for " + sportname);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (IOException e) {
            System.out.println("Failed to view fixture : "+ e.getMessage());
        }
    }
    public void deleteFixture(){
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("No fixture found for " + sportname);
        }
        try{
            file.delete();
            System.out.println("Fixture deleted");
        }catch(IOError e){
            System.out.println("Failed to delete fixture : "+ e.getMessage());
        }
    }
}
