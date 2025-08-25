package gui;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class MgtDashboard extends JFrame{
    public MgtDashboard(){
        setTitle("Management Dashboard");
        setSize(400,200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        JLabel sportname = new JLabel("Sport Name");
        JLabel team1 = new JLabel("Team 1 Name");
        JLabel team2 = new JLabel("Team 2 Name");
        JLabel date = new JLabel("Date");
        JLabel time = new JLabel("Time");

        JTextArea t1 = new JTextArea(); JTextArea t2 = new JTextArea(); JTextArea t3 = new JTextArea();
        JTextArea t4 = new JTextArea(); JTextArea t5 = new JTextArea();

        JPanel panel1=new JPanel(new GridLayout(5,5,10,10));
        panel1.add(sportname); panel1.add(t1);
        panel1.add(team1); panel1.add(t2); 
        panel1.add(team2); panel1.add(t3);
        panel1.add(date); panel1.add(t4);
        panel1.add(time); panel1.add(t5);
        

        // JButton schedulebtn = new JButton("Schedule A Match");
        // // panel1.add(schedulebtn);
        // schedulebtn.addActionListener(e->{
            
        // });

        // JButton viewbtn = new JButton("View Fixture");
        // panel1.add(viewbtn);
        
        // JButton removebtn = new JButton("Remove Fixture");
       

        JButton exitbtn = new JButton("Exit");
        // panel1.add(exitbtn);
        exitbtn.addActionListener(e->{dispose();});

        // add(schedulebtn); add(viewbtn); add(removebtn); add(exitbtn);
        //add separate buttons for schedue view remove and makediff panels

        JButton schedule = new JButton("Schedule A Match");
        add(schedule);
        schedule.addActionListener(e->{
            int result = JOptionPane.showConfirmDialog(null,panel1,"Schedule",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(result == JOptionPane.OK_OPTION){
                String sportName = t1.getText().trim();
            String team3 = t2.getText();
            String team4 = t3.getText();
            String date1 = t4.getText();
            String time1 = t5.getText();

        if (sportName.isEmpty() || team3.isEmpty() || team4.isEmpty() || date1.isEmpty() || time1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection con = util.Db.getConnection()) {
            // Check if sport exists
            String checkSport = "SELECT * FROM sports WHERE sports_name=?";
            PreparedStatement pst = con.prepareStatement(checkSport);
            pst.setString(1, sportName);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Sport not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insert match
            String insertMatch = "INSERT INTO fixtures (sports_name, team1, team2, match_date, match_time) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst2 = con.prepareStatement(insertMatch);
            pst2.setString(1, sportName);
            pst2.setString(2, team3);
            pst2.setString(3, team4);
            pst2.setString(4, date1);
            pst2.setString(5, time1);

            int inserted = pst2.executeUpdate();
            if (inserted > 0) {
                JOptionPane.showMessageDialog(this, "Match scheduled successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to schedule match.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
            }
        });
        JButton view = new JButton("View Fixture");
        add(view);
        view.addActionListener(e -> {
            try (Connection con = util.Db.getConnection()) {
                String query = "SELECT * FROM fixtures";
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
    
                StringBuilder fixtures = new StringBuilder();
                while (rs.next()) {
                    fixtures.append("Sport: ").append(rs.getString("sports_name"))
                            .append(", Teams: ").append(rs.getString("team1"))
                            .append(" vs ").append(rs.getString("team2"))
                            .append(", Date: ").append(rs.getString("match_date"))
                            .append(", Time: ").append(rs.getString("match_time"))
                            .append("\n");
                }
    
                if (fixtures.length() == 0) {
                    JOptionPane.showMessageDialog(this, "No fixtures found.");
                } else {
                    JTextArea fixtureArea = new JTextArea(fixtures.toString());
                    fixtureArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(fixtureArea);
                    scrollPane.setPreferredSize(new Dimension(400, 200));
                    JOptionPane.showMessageDialog(this, scrollPane, "Fixtures", JOptionPane.INFORMATION_MESSAGE);
                }
    
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton remove = new JButton("Remove Fixture");
        add(remove);
        remove.addActionListener(e->{
            String sportName = JOptionPane.showInputDialog(this, "Enter the Sport Name of the fixture to remove:");
            if (sportName == null || sportName.trim().isEmpty()) {
                return;
        }

        try (Connection con = util.Db.getConnection()) {
            String deleteQuery = "DELETE FROM fixtures WHERE sports_name=?";
            PreparedStatement pst = con.prepareStatement(deleteQuery);
            pst.setString(1, sportName.trim());

            int deleted = pst.executeUpdate();
            if (deleted > 0) {
                JOptionPane.showMessageDialog(this, "Fixture(s) removed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No fixture found with the given Sport Name.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        });
        add(exitbtn);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    private void clearFields() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}