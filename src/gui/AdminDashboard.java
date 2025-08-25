package gui;

import javax.swing.*;
import java.awt.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDashboard extends JFrame{
    public AdminDashboard(){
        setTitle("Admin Dashboard");
        setSize(400,200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        JLabel sportname = new JLabel("Sport Name");
        JLabel maxnum = new JLabel("Max Number Of Players");
        JLabel fees = new JLabel("Registration Fees");
        JLabel first = new JLabel("First Prize");
        JLabel second = new JLabel("Second Prize");

        JTextArea t1 = new JTextArea(); JTextArea t2 = new JTextArea(); JTextArea t3 = new JTextArea();
        JTextArea t4 = new JTextArea(); JTextArea t5 = new JTextArea();

        panel.add(sportname); panel.add(t1);
        panel.add(maxnum); panel.add(t2); 
        panel.add(fees); panel.add(t3);
        panel.add(first); panel.add(t4);
        panel.add(second); panel.add(t5);
        

        JButton addbtn = new JButton("Add Sport");
        addbtn.addActionListener(e->{
            int i = JOptionPane.showConfirmDialog(null,panel,"Add sport",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(i == JOptionPane.OK_OPTION){
            
                String sportname1 = t1.getText();
                String maxnum1 = t2.getText();
                String fees1 = t3.getText();
                String first1 = t4.getText();
                String second1 = t5.getText();
                if (sportname1.isEmpty() || maxnum1.isEmpty() || fees1.isEmpty() || first1.isEmpty() || second1.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
        Connection conn = util.Db.getConnection();
        if (conn != null) {
            String sql = "INSERT INTO sports (sports_name,max_players ,registration_fees, first_prize,second_prize) VALUES (?, ?, ?, ?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sportname1);
            pst.setString(2, maxnum1);
            pst.setString(3, fees1);
            pst.setString(4, first1);
            pst.setString(5, second1);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Sport added successfully!");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Addition Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    }
                    
            
        });

        JButton removebtn = new JButton("Remove Sport");
        removebtn.addActionListener(e->{
            String sportname3 = JOptionPane.showInputDialog(null,"Sport Name","Remove Sport",JOptionPane.PLAIN_MESSAGE);

            if(sportname3 != null){
                try {
        Connection conn = util.Db.getConnection();
        if (conn != null) {
            String sql = "DELETE FROM sports WHERE sports_name = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sportname3);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Sport Removed Successfully!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Deletion Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    }
                    

            
        });

        JButton viewbtn = new JButton("View Sports List");
        viewbtn.addActionListener(e->{
            try (Connection con = util.Db.getConnection()) {
                String query = "SELECT * FROM sports";
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
    
                StringBuilder sports = new StringBuilder();
                while (rs.next()) {
                    sports.append("Sport: ").append(rs.getString("sports_name"))
                            .append(", Max Players: ").append(rs.getString("max_players"))
                            .append(" ,Registration Fees: ").append(rs.getString("registration_fees"))
                            .append(", First Prize: ").append(rs.getString("first_prize"))
                            .append(", Second Prize: ").append(rs.getString("second_prize"))
                            .append("\n");
                }
    
                if (sports.length() == 0) {
                    JOptionPane.showMessageDialog(this, "No sports data found.");
                } else {
                    JTextArea sportsArea = new JTextArea(sports.toString());
                    sportsArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(sportsArea);
                    scrollPane.setPreferredSize(new Dimension(400, 200));
                    JOptionPane.showMessageDialog(this, scrollPane, "Sports", JOptionPane.INFORMATION_MESSAGE);
                }
    
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton exitbtn = new JButton("Exit");
        exitbtn.addActionListener(e->{dispose();});

        add(addbtn); add(removebtn); add(viewbtn); add(exitbtn);

        setLayout(new FlowLayout());
        setVisible(true);
    }
}
