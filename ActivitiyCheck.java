package stockmaintenanceandaccounting;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class ActivitiyCheck implements ActionListener
{
    JFrame f;
    JPasswordField jt2;
    JTextField jt1,jt3,jt4,jt5,jt6;
    JLabel lb1,lb2,lb3,lb4,lb5,lb6;
    JButton jb1,jb2,jb3;
    ActivitiyCheck()
    {
        f=new JFrame("CHECK TRANSACTION DETAILS");
        f.getContentPane().setBackground(Color.YELLOW);
        
        lb1=new JLabel("ENTER USERNAME:");
        lb1.setBounds(100,40,200,30);
        f.add(lb1);
        
        jt1=new JTextField();
        jt1.setBounds(300,40,150,30);
        f.add(jt1);
        
        lb2=new JLabel("ENTER PASSWORD:");
        lb2.setBounds(100,80,200,30);
        f.add(lb2);
        
        jt2=new JPasswordField();
        jt2.setBounds(300,80,150,30);
        f.add(jt2);
        
        jb1=new JButton("CHECK DETAILS");
        jb1.setBounds(100,120,140,50);
        f.add(jb1);
        
        jb2=new JButton("CLEAR");
        jb2.setBounds(300,120,140,50);
        f.add(jb2);
        
        lb3=new JLabel("NO.OF ORDERS RECIEVED:");
        lb3.setBounds(100,180,200,30);
        f.add(lb3);
        
        jt3=new JTextField();
        jt3.setBounds(300,180,150,30);
        f.add(jt3);
        
        lb4=new JLabel("NO.OF SALES BILLS:");
        lb4.setBounds(100,230,150,30);
        f.add(lb4);
        
        jt4=new JTextField();
        jt4.setBounds(300,230,150,30);
        f.add(jt4);
        
        lb5=new JLabel("NO.OF PURCHASE BILLS:");
        lb5.setBounds(100,280,200,30);
        f.add(lb5);
        
        jt5=new JTextField();
        jt5.setBounds(300,280,150,30);
        f.add(jt5);
        
        lb6=new JLabel("NO.OF FABRIC VARIETY:");
        lb6.setBounds(100,330,200,30);
        f.add(lb6);
        
        jt6=new JTextField();
        jt6.setBounds(300,330,150,30);
        f.add(jt6);
        
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
               try
               {    
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system" ,"57241516");
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
                     
                                String sql1 = "select addFab,removeFab,addCust,addIncome,addExpense from StoreAccountingDetails";    
                                PreparedStatement ps1 = con.prepareStatement(sql1);
                                ResultSet rs1 = ps1.executeQuery();
                      
                                if(rs1.next())
                                {
                                    System.out.println("Total operations!");
                                    int insfab=rs1.getInt("addFab");
                                    int remfab=rs1.getInt("removeFab");
                                    int inscust=rs1.getInt("addCust");
                                    int insinc=rs1.getInt("addIncome");
                                    int insexp=rs1.getInt("addExpense");
                                    int fab=insfab-remfab; 
                                    JOptionPane.showMessageDialog(null, "TRANSACTIONS DONE BY THE USER!!");
                                    jt3.setText(String.valueOf(inscust));
                                    jt4.setText(String.valueOf(insinc));
                                    jt5.setText(String.valueOf(insexp));
                                    jt6.setText(String.valueOf(fab));
                                }
                                else
                                {
                                        JOptionPane.showMessageDialog(null, "SOMETHING WENT WRONG!!");
                                }
                        }
                        catch(Exception ee) 
                        { 
                            System.out.println(ee); 
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
            jt3.setText(String.valueOf(""));
            jt4.setText(String.valueOf(""));
            jt5.setText(String.valueOf(""));
            jt6.setText(String.valueOf(""));
        }
        if(ae.getSource()==jb3)
        {
            StockMaintenanceAndAccounting a=new StockMaintenanceAndAccounting();
            f.setVisible(false);
            f.dispose();
            a.setVisible(true);
        }
    }
    public static void main(String[] args) 
    {
        ActivitiyCheck c = new ActivitiyCheck();
    }    
}