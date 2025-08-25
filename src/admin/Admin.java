package admin;
import user.*;

public class Admin extends User {
    protected String name;
    protected String role;
    protected int AdminId;
    private static int idCounter = 100;

    public Admin(String name, String role, String username, String password){
        super(username, password);
        this.AdminId = ++idCounter;
        this.name = name;
        this.role = role;
    }

    public void viewDetails(){
        System.out.println("Admin ID: " + AdminId);
        System.out.println("Name: " + name);
        System.out.println("Username: "+ username);
        System.out.println("Role: "+ role);
    }
}
