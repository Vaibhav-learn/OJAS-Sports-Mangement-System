package main;
import gui.*;
import java.io.*;
class Ojas{
    
    static void saveNewUser(String name, String username , String password , String role){
        try(FileWriter fw = new FileWriter("data/users.txt",true);
            BufferedWriter bw = new BufferedWriter(fw)
        ){
            bw.write(name + "," + username + "," + password + ',' +  role);
            bw.newLine();
        }catch(IOException e){
            System.out.println("Failed to save info : "+ e.getMessage());
        }
    }

    static boolean checkLogin(String username , String password){
        boolean oldUser = false;
        try(FileReader fr = new FileReader("data/users.txt");
            BufferedReader br = new BufferedReader(fr)
        ){
            String line;
            while((line = br.readLine()) != null){
                String users[] = line.split(",");
                if((users[1].equals(username) && users[2].equals(password))){
                    oldUser = true;
                    break;
                }
            }

        }catch(IOException e){
            System.out.println("Failed to check info : "+ e.getMessage() );
        }
        if (oldUser) {
            return true;
        }
        return false;
    }

    static boolean alreadyUser(String username){
        boolean oldUser = false;
        try(FileReader fr = new FileReader("data/users.txt");
            BufferedReader br = new BufferedReader(fr)
        ){
            String line;
            while((line = br.readLine()) != null){
                String users[] = line.split(",");
                if(users[1].equals(username)){
                    oldUser = true;
                    break;
                }
            }

        }catch(IOException e){
            System.out.println("");
        }
        if (oldUser) {
            return true;
        }
        return false;
    }
    
    static String getRole(String username){
        String role3 = null;
        try(FileReader fr = new FileReader("data/users.txt");
            BufferedReader br = new BufferedReader(fr)
        ) {
            String line;
            while((line = br.readLine()) != null){
                String[] info = line.split(",");
                if(info[1].equals(username)){
                    role3 = info[3];
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to get role from file :  " + e.getMessage());
        }
        return role3;
    }
    public static void main(String args[]){
        // Connection con = util.Db.getConnection();
        // if (con != null) {
        //     System.out.println("Connected successfully!");
        // } else {
        //     System.out.println("Connection failed.");
        // }
        new MainFrame();

    }}
    //     String url = "jdbc:mysql://localhost:3306/OJAS"; 
    //     String username7 = "root"; 
    //     String password7 = "scieneer"; 

    //     try {
    //         Connection conn = DriverManager.getConnection(url, username7, password7);
    //         System.out.println("Connection successful!");
    //         conn.close();
    //     }
       
    //     catch (SQLException e) {
    //         System.out.println("Connection failed: " + e.getMessage());
    //     }

    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("********** OJAS SPORTS FEST ************");
        
    //     while(true){
    //         System.out.println("Enter one of the following(1-3) : ");
    //         System.out.println("1. Login\n2. Register \n3. Exit");
    //         int choice1 = sc.nextInt();
    //         switch (choice1) {
    //             case 1 :
    //                 sc.nextLine();
    //                 System.out.println("Name : ");
    //                 String name = sc.nextLine();
    //                 System.out.println("Username : ");
    //                 String username = sc.nextLine();
    //                 System.out.println("Password : ");
    //                 String password = sc.nextLine();
    //                 if(checkLogin(username, password)){
    //                     System.out.println("Logged in succesfully");
    //                     String role4 = getRole(username);
    //                     if(role4.equalsIgnoreCase("Core Admin")){
    //                         Core c1 = new Core(name,username, password);
    //                         while(true){
    //                             System.out.println("What do you wanna do? \n1. Add sport \n2. Remove Sport \n3. View Sport List \n4. Exit");
    //                             int option = sc.nextInt();
    //                             switch (option) {
    //                                 case 1:
    //                                     c1.addSport();
    //                                     break;
                                    
    //                                 case 2:
    //                                     c1.removeSport();
    //                                     break;

    //                                 case 3:
    //                                     admin.Core.viewSportList();
    //                                     break;

    //                                 case 4:
    //                                     break;
                        
    //                                 default:
    //                                     System.out.println("Enter valid option");
    //                             }
    //                             if(option == 4){
    //                                 break;
    //                             }
    //                         }
    //                     }else if(role4.equalsIgnoreCase("Management Admin")){
    //                         Management m1 = new Management(name, username, password);
    //                         while(true){
    //                             System.out.println("What do you wanna do? \n1. Schedule Match In Fixture \n2. View Fixture \n3. Remove Fixture \n4. Exit");
    //                             int option = sc.nextInt();
    //                             switch (option) {
    //                                 case 1:
    //                                     m1.scheduleMatchInFixture();
    //                                     break;
                                    
    //                                 case 2:
    //                                     admin.Management.viewFixture();
    //                                     break;

    //                                 case 3:
    //                                     m1.deleteFixture();
    //                                     break;

    //                                 case 4:
    //                                     break;
                        
    //                                 default:
    //                                     System.out.println("Enter valid option");
    //                             }
    //                             if(option == 4){
    //                                 break;
    //                             }
    //                         }
    //                     }else{
    //                         Participant p1 = new Participant(name,username,password);
    //                         while(true){
    //                             System.out.println("What do you wanna do? \n1. View Sports List \n2. Participate In A Sport \n3. View Fixture \n4. Exit");
    //                             int option = sc.nextInt();
    //                             switch (option) {
    //                                 case 1:
    //                                     p1.viewEachSportsInfo();
    //                                     break;
                                    
    //                                 case 2:
    //                                     p1.participate();
    //                                     break;

    //                                 case 3:
    //                                     p1.viewFixture();
    //                                     break;

    //                                 case 4:
    //                                     break;
                        
    //                                 default:
    //                                     System.out.println("Enter valid option");
    //                             }
    //                             if(option == 4){
    //                                 break;
    //                             }
    //                         }
    //                     }
    //                 }
    //                 else{
    //                     System.out.println("Enter correct username and password");
    //                 }
    //                 break;

    //             case 2 :
    //                 while(true){
    //                 System.out.println("Choose your role(1-3) : \n1. Admin \n2. Participant \n3. Exit");
    //                 int choice2 = sc.nextInt();

    //                 switch(choice2){
    //                     case 1 : 
    //                         sc.nextLine();
    //                         System.out.println("Name : ");
    //                         String name2 = sc.nextLine();
    //                         System.out.println("Username : ");
    //                         String username2 = sc.nextLine();
    //                         System.out.println("Password : ");
    //                         String password2 = sc.nextLine();
                            
    //                         if(alreadyUser(username2)){
    //                             System.out.println("This username is already taken");
    //                         }
    //                         else{ 
    //                             while(true){
    //                                 System.out.println("Enter your role - 1.Core Admin  2.Management Admin");
    //                                 int role2 = sc.nextInt();
    //                                 if(role2 == 1){
    //                                     Core coreAdmin = new Core(name2, username2, password2);
    //                                     saveNewUser(name2,username2,password2, "Core Admin");
    //                                     System.out.println("New core admin registered : ");
    //                                     coreAdmin.viewDetails();
    //                                     break;
    //                                 }
    //                                 else if(role2 == 2){
    //                                     Management mgtAdmin = new Management(name2, username2, password2);
    //                                     saveNewUser(name2,username2,password2, "Management Admin");
    //                                     System.out.println("New Management admin registered : ");
    //                                     mgtAdmin.viewDetails();
    //                                     break;
    //                                 }
    //                                 else{
    //                                     System.out.println("Enter valid number");
    //                                 }

    //                             }
    //                         }                      
    //                         break;

    //                     case 2 :
    //                         sc.nextLine();
    //                         System.out.println("Name : ");
    //                         String name3 = sc.nextLine();
    //                         System.out.println("Username : ");
    //                         String username3 = sc.nextLine();
    //                         System.out.println("Password : ");
    //                         String password3 = sc.nextLine();

    //                         if(alreadyUser(username3)){
    //                             System.out.println("This username is already taken");
    //                         }
    //                         else{
    //                             Participant participant = new Participant(name3,username3,password3);
    //                             saveNewUser(name3,username3,password3,"participant");  
    //                             System.out.println("New Participant registered : ");
    //                             participant.viewDetails();
    //                         }                      
    //                         break;           

    //                     case 3:
    //                         break;
                        
    //                     default:
    //                         System.out.println("Enter valid number");
    //                 }
    //                 if(choice2 == 3){
    //                     break;
    //                 }
    //             }
    //                 break;

    //             case 3 :
    //                 System.out.println("Exiting...");
    //                 break;

    //             default:
    //                 System.out.println("Enter a valid number");
    //         }
    //     if(choice1 == 3){
    //         break;
    //     }
    // }  
    //     System.out.println("***************** THANKYOU *******************");
    //     sc.close();
