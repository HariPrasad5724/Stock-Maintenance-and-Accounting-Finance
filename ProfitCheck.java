package stockmaintenanceandaccounting;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ProfitCheck implements ActionListener {
    JFrame f;
    JPasswordField jt2;
    JTextField jt1, jt3, jt4, jt5;
    JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7;
    JButton jb1, jb2, jb3;

    ProfitCheck() {
        f = new JFrame("PROFIT DETAILS");
        f.getContentPane().setBackground(Color.CYAN);

        lb1 = new JLabel("ENTER USERNAME:");
        lb1.setBounds(100, 50, 150, 30);
        f.add(lb1);

        jt1 = new JTextField();
        jt1.setBounds(250, 50, 150, 30);
        f.add(jt1);

        lb2 = new JLabel("ENTER PASSWORD:");
        lb2.setBounds(100, 90, 150, 30);
        f.add(lb2);

        jt2 = new JPasswordField();
        jt2.setBounds(250, 90, 150, 30);
        f.add(jt2);

        jb1 = new JButton("LOGIN TO CHECK");
        jb1.setBounds(100, 150, 160, 30);
        f.add(jb1);

        jb2 = new JButton("CLEAR");
        jb2.setBounds(280, 150, 160, 30);
        f.add(jb2);

        lb3 = new JLabel("INCOME TILL DATE:");
        lb3.setBounds(100, 220, 150, 30);
        f.add(lb3);

        jt3 = new JTextField();
        jt3.setBounds(250, 220, 150, 30);
        f.add(jt3);

        lb4 = new JLabel("EXPENSES TILL DATE:");
        lb4.setBounds(100, 260, 150, 30);
        f.add(lb4);

        jt4 = new JTextField();
        jt4.setBounds(250, 260, 150, 30);
        f.add(jt4);

        lb5 = new JLabel("PROFIT ANALYSIS:");
        lb5.setBounds(100, 300, 150, 30);
        f.add(lb5);

        jt5 = new JTextField();
        jt5.setBounds(250, 300, 150, 30);
        f.add(jt5);

        jb3 = new JButton("BACK HOME");
        jb3.setBounds(190, 400, 150, 50);
        f.add(jb3);

        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);

        f.setLayout(null);
        f.setSize(600, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jb1) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
                        "572415");
                String sql = "select * from UserDetails where user_idno ='" + jt1.getText() + "' and user_password ='"
                        + jt2.getText() + "'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "YOU ARE A VALID USER!! CLICK OK BUTTON TO CHECK!");
                    try {
                        Connection con = null;
                        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "572415");
                        System.out.println("Connected");

                        String sum = "select sum(inccost) from IncomeBills";
                        PreparedStatement ps1 = con.prepareStatement(sum);
                        ResultSet rs1 = ps1.executeQuery();

                        if (rs1.next()) {
                            int Icost = rs1.getInt("sum(inccost)");
                            try {
                                Connection con1 = null;
                                con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
                                        "57241516");
                                System.out.println("Connected");

                                String sumE = "select sum(expcost) from ExpenseBills";
                                PreparedStatement ps2 = con1.prepareStatement(sumE);
                                ResultSet rs2 = ps2.executeQuery();

                                if (rs2.next()) {
                                    int Ecost = rs2.getInt("sum(expcost)");

                                    JOptionPane.showMessageDialog(null, "YOU ARE ALLOWED TO CHECK PROFIT!");
                                    System.out.println("Calculated..\n");

                                    int tot = Icost - Ecost;
                                    jt3.setText(String.valueOf(Icost));
                                    jt4.setText(String.valueOf(Ecost));
                                    jt5.setText(String.valueOf(tot));
                                } else {
                                    JOptionPane.showMessageDialog(null, "SOMETHING WENT WRONG!!");
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }

                    catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "INVALID USERNAME OR PASSWORD!");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (ae.getSource() == jb2) {
            jt1.setText(String.valueOf(""));
            jt2.setText(String.valueOf(""));
            jt3.setText(String.valueOf(""));
            jt4.setText(String.valueOf(""));
            jt5.setText(String.valueOf(""));
        }

        if (ae.getSource() == jb3) {
            StockMaintenanceAndAccounting a = new StockMaintenanceAndAccounting();
            f.setVisible(false);
            f.dispose();
            a.setVisible(true);
        }

    }

    public static void main(String[] args) {
        ProfitCheck p = new ProfitCheck();
    }
}