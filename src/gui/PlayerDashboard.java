package gui;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class PlayerDashboard extends JFrame {
    public PlayerDashboard(){
        setTitle("Player Dashboard");
        setSize(400,200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2,2,10,10));
        JLabel sportname = new JLabel("Sport Name");
        JLabel maxnum = new JLabel("Number Of Players");
        JLabel name = new JLabel("Name");
        JLabel collegeid = new JLabel("College Id");
        JLabel course = new JLabel("Course");

        JTextArea t1 = new JTextArea(); JTextArea t2 = new JTextArea(); JTextArea t3 = new JTextArea();
        JTextArea t4 = new JTextArea(); JTextArea t5 = new JTextArea();

        panel.add(sportname); panel.add(t1);
        panel.add(maxnum); panel.add(t2); 

    
        

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

        JButton participatebtn = new JButton("Participate");
        participatebtn.addActionListener(e->{
            int result = JOptionPane.showConfirmDialog(null,panel,"Participate In A Sport",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            if(result == JOptionPane.OK_OPTION){
                for(int i = 1; i<=Integer.parseInt(t2.getText());i++){
                    JPanel panel2 = new JPanel(new GridLayout(3,2,10,10));
                    panel2.add(name); panel2.add(t3);
                    panel2.add(collegeid); panel2.add(t4);
                    panel2.add(course); panel2.add(t5);
                    int j = JOptionPane.showConfirmDialog(null,panel2,"Player" + i + "Detail",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

                    if(j == JOptionPane.OK_OPTION){
                        String name1 = t3.getText();
                        int collegeId1 = Integer.parseInt(t4.getText());
                        String course1 = t5.getText();
                        if(name1.isEmpty() || course1.isEmpty()){
                            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        try {
                            Connection conn = util.Db.getConnection();
                            if (conn != null) {
                                String sql = "INSERT INTO players (player_name,college_id ,course) VALUES (?, ?, ?)";
                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.setString(1,name1);
                                pst.setInt(2, collegeId1);
                                pst.setString(3, course1);
                    
                                int rowsInserted = pst.executeUpdate();
                                if (rowsInserted > 0) {
                                    JOptionPane.showMessageDialog(this, "Player added successfully!");
                                    t3.setText("");
                                    t4.setText("");
                                    t5.setText("");
                                        }
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(this, "Registration Failed!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                    }
                    

                }
            }
        });

        JButton view2btn = new JButton("View Fixture");
        view2btn.addActionListener(e->{
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


        
        JButton exitbtn = new JButton("Exit");
        exitbtn.addActionListener(e->{dispose();});

        add(viewbtn); add(participatebtn); add(view2btn); add(exitbtn);

        setLayout(new FlowLayout());
        setVisible(true);
    }
}