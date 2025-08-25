package gui;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class RegisterFrame extends JFrame {
        public RegisterFrame(){
        setTitle("Register");
        setSize(300,200);
        setLocationRelativeTo(null);

        JLabel name = new JLabel("     Name");
        JLabel username = new JLabel("     Username");
        JLabel password = new JLabel("     Password");

        JTextArea t1 = new JTextArea();
        t1.setBorder(new LineBorder(Color.BLACK,1));
        JTextArea t2 = new JTextArea();
        t2.setBorder(new LineBorder(Color.BLACK,1));
        JPasswordField t3 = new JPasswordField();
        t3.setBorder(new LineBorder(Color.BLACK,1));

        JPanel panel1 = new JPanel(new GridLayout(3,2,10,10));
        panel1.add(name); panel1.add(t1); panel1.add(username); panel1.add(t2); panel1.add(password); panel1.add(t3);

        // JButton loginbtn = new JButton("Register");
        // JPanel panel2 = new JPanel();
        // panel2.add(loginbtn);

        JPanel panel3 = new JPanel(new GridLayout());
        panel3.add(panel1); 
        // panel3.add(panel2);

        JButton core = new JButton("Core Admin");
        add(core);
        core.addActionListener(e->{
            int i = JOptionPane.showConfirmDialog(null,panel3,"Details",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(i == JOptionPane.OK_OPTION){
               
                String name1 = t1.getText();
                String username1 = t2.getText();
                String password1 = new String(t3.getPassword());
                String role = "Core Admin";
                if (username1.isEmpty() || name1.isEmpty() || password1.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                 try {
        Connection conn = util.Db.getConnection();
        if (conn != null) {
            String sql = "INSERT INTO users (name,username, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name1);
            pst.setString(2, username1);
            pst.setString(3, password1);
            pst.setString(4, role);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Registration Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }}
        });

        JButton mgt = new JButton("Management Admin");
        add(mgt);
        mgt.addActionListener(e->{
            int i= JOptionPane.showConfirmDialog(null,panel3,"Details",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(i == JOptionPane.OK_OPTION){
               
                String name1 = t1.getText();
                String username1 = t2.getText();
                String password1 = new String(t3.getPassword());
                String role = "Management Admin";
                if (username1.isEmpty() || name1.isEmpty() || password1.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                 try {
        Connection conn = util.Db.getConnection();
        if (conn != null) {
            String sql = "INSERT INTO users (name,username, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name1);
            pst.setString(2, username1);
            pst.setString(3, password1);
            pst.setString(4, role);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                // optionally: clear fields
                t1.setText("");
                t2.setText("");
                t3.setText("");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Registration Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }}
        });
        JButton pgt = new JButton("Participant");
        add(pgt);
        pgt.addActionListener(e->{
            int i = JOptionPane.showConfirmDialog(null,panel3,"Details",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            if(i == JOptionPane.OK_OPTION){
               
                String name1 = t1.getText();
                String username1 = t2.getText();
                String password1 = new String(t3.getPassword());
                String role = "Participant";
                if (username1.isEmpty() || name1.isEmpty() || password1.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                 try {
        Connection conn = util.Db.getConnection();
        if (conn != null) {
            String sql = "INSERT INTO users (name,username, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name1);
            pst.setString(2, username1);
            pst.setString(3, password1);
            pst.setString(4, role);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Registration Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    }
                    
                });

        setLayout(new FlowLayout());
        setVisible(true);
    }
}