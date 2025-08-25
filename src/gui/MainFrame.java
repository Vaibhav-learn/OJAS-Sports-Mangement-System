package gui;
import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame(){
        setTitle("OJAS Sports Management System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel("OJAS SPORTS MANAGEMENT SYSTEM");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        JButton LoginBtn, RegisterBtn, ExitBtn;
        add(heading);
    
        LoginBtn = new JButton("Login");
        RegisterBtn = new JButton("Register");
        ExitBtn = new JButton("Exit");
        add(LoginBtn); add(RegisterBtn); add(ExitBtn);

        LoginBtn.addActionListener(e ->{new LoginFrame();});
        RegisterBtn.addActionListener(e->{new RegisterFrame();});
        ExitBtn.addActionListener(e->{System.exit(ABORT);});
        setLayout(new FlowLayout());
        setVisible(true);
    }
}
