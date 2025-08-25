package gui;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class LoginFrame extends JFrame {
    public LoginFrame(){
        setTitle("Login");
        setSize(300,200);
        setLocationRelativeTo(null);

        JLabel username = new JLabel("     Username");
        JLabel password = new JLabel("     Password");

        JTextArea t2 = new JTextArea();
        t2.setBorder(new LineBorder(Color.BLACK,1));
        JPasswordField t3 = new JPasswordField();
        t3.setBorder(new LineBorder(Color.BLACK,1));

        JPanel panel1 = new JPanel(new GridLayout(3,2,10,10));
        add(panel1);
        panel1.add(username); panel1.add(t2); panel1.add(password); panel1.add(t3);

        JButton loginbtn = new JButton("Login");
        loginbtn.addActionListener(e->{
            String username1 = t2.getText();
            String password1 = new String(t3.getPassword());
            if(username1.isEmpty()|| password1.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all fields!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
        Connection conn = util.Db.getConnection();
        if (conn != null) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username1);
            pst.setString(2, password1);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role"); 

                JOptionPane.showMessageDialog(this, "Login Successful as " + role);

                if (role.equalsIgnoreCase("Core Admin")) {
                    new AdminDashboard();
                } else if (role.equalsIgnoreCase("Participant")) {
                    new PlayerDashboard();
                } else if(role.equalsIgnoreCase("Management Admin")){
                    new MgtDashboard();
                }

                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Login Failed!", "Error", JOptionPane.ERROR_MESSAGE);
    }
        });
        JPanel panel2 = new JPanel();
        add(panel2);
        panel2.add(loginbtn);

        setLayout(new GridLayout(2,1));
        setVisible(true);
    }
}
