package stockmaintenanceandaccounting;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class MonthlyProfit implements ActionListener
{
    JFrame f;
    JPasswordField jt2;
    JTextField jt1,jt5,jt6,jt7;
    JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7;
    JButton jb1,jb2,jb3;
    JComboBox cb2,cb3;
    MonthlyProfit()
    {
        f=new JFrame("PROFIT DETAILS");
        f.getContentPane().setBackground(Color.YELLOW);
        
        lb1=new JLabel("ENTER USERNAME:");
        lb1.setBounds(100,50,150,30);
        f.add(lb1);
        
        jt1=new JTextField();
        jt1.setBounds(250,50,150,30);
        f.add(jt1);
        
        lb2=new JLabel("ENTER PASSWORD:");
        lb2.setBounds(100,90,150,30);
        f.add(lb2);
        
        jt2=new JPasswordField();
        jt2.setBounds(250,90,150,30);
        f.add(jt2);
        
        lb3=new JLabel("ENTER MMM/YYYY:");
        lb3.setBounds(100,130,150,30);
        f.add(lb3);
        
        String month[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        cb2=new JComboBox(month);    
        cb2.setBounds(250,130,80,30);    
        f.add(cb2);
        
        String year[]={"2020","2021"};        
        cb3=new JComboBox(year);    
        cb3.setBounds(360,130,80,30);    
        f.add(cb3);
        
        jb1=new JButton("LOGIN TO CHECK");
        jb1.setBounds(100,180,160,30);
        f.add(jb1);
       
        jb2=new JButton("CLEAR");
        jb2.setBounds(280,180,160,30);
        f.add(jb2);
        
        lb4=new JLabel("MONTHLY INCOME:");
        lb4.setBounds(100,240,150,30);
        f.add(lb4);
        
        jt5=new JTextField();
        jt5.setBounds(250,240,150,30);
        f.add(jt5);        
        
        lb5=new JLabel("MONTHLY EXPENSES:");
        lb5.setBounds(100,280,150,30);
        f.add(lb5);
        
        jt6=new JTextField();
        jt6.setBounds(250,280,150,30);
        f.add(jt6);     
        
        lb6=new JLabel("PROFIT ANALYSIS:");
        lb6.setBounds(100,320,150,30);
        f.add(lb6);
        
        jt7=new JTextField();
        jt7.setBounds(250,320,150,30);
        f.add(jt7);     
                
        jb3=new JButton("BACK HOME");
        jb3.setBounds(190,400,150,50);
        f.add(jb3);
        
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
       
       f.setLayout(null);    
       f.setSize(600,600);    
       f.setVisible(true); 
       f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }   
    
    @Override   
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==jb1)
        { 
            String month =String.valueOf(cb2.getItemAt(cb2.getSelectedIndex()));  
            String years =String.valueOf(cb3.getItemAt(cb3.getSelectedIndex())); 
               try
               {    
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system" ,"572415");
                    String sql =  "select * from UserDetails where user_idno ='"+jt1.getText()+"' and user_password ='"+jt2.getText()+"'";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next())
                    {
                        JOptionPane.showMessageDialog(null, "YOU ARE A VALID USER!! CLICK OK BUTTON TO CHECK!");               
                        try
                        {
                            Connection con=null;
                            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","572415");
                            System.out.println("Connected");
                     
                            String sumI = "select sum(inccost) from IncomeBills where mon ='"+month+"' and year='"+years+"'";    
                            PreparedStatement ps1 = con.prepareStatement(sumI);
                            ResultSet rs1 = ps1.executeQuery();
                      
                                if(rs1.next())
                                {
                                    int Icost=rs1.getInt("sum(inccost)");
                                    try
                                    {  
                                        Connection con1=null;
                                        con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","572415");
                                        System.out.println("Connected");
                     
                                        String sumE = "select sum(expcost) from ExpenseBills where mon ='"+month+"' and year='"+years+"' ";    
                                        PreparedStatement ps2 = con1.prepareStatement(sumE);
                                        ResultSet rs2 = ps2.executeQuery();
                                    
                                        if(rs2.next())
                                        {
                                                int Ecost=rs2.getInt("sum(expcost)");
                                            
                                                JOptionPane.showMessageDialog(null, "YOU ARE ALLOWED TO CHECK PROFIT!");
                                                System.out.println("Calculated..\n");
                                
                                                int tot=Icost-Ecost;
                                                jt5.setText(String.valueOf(Icost));
                                                jt6.setText(String.valueOf(Ecost));
                                                jt7.setText(String.valueOf(tot));
                                        }
                                        else
                                        {
                                                JOptionPane.showMessageDialog(null, "SOMETHING WENT WRONG!!");
                                        }
                                    }
                                    catch(Exception e) 
                                    { 
                                            System.out.println(e); 
                                    }
                                }
                        }
                                    
                        catch(Exception e) 
                        { 
                             System.out.println(e); 
                        }  
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "INVALID USERNAME OR PASSWORD!");
                    }                    
               }
               catch(Exception e)
               {
                        System.out.println(e);
               }
        }
        
        if(ae.getSource()==jb2)
        {
            jt1.setText(String.valueOf(""));
            jt2.setText(String.valueOf(""));
            jt5.setText(String.valueOf(""));
            jt6.setText(String.valueOf(""));
            jt7.setText(String.valueOf(""));
            
        }
        
        if(ae.getSource()==jb3)
        {
            StockMaintenanceAndAccounting a=new StockMaintenanceAndAccounting();
            f.setVisible(false);
            f.dispose();
            a.setVisible(true);
        }
    }
    public static void main(String args[])
    {
        MonthlyProfit mp=new MonthlyProfit();
    }
}