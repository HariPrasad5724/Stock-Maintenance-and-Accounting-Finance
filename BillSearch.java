package stockmaintenanceandaccounting;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class BillSearch implements ActionListener 
{
    JFrame f;
    JTextField jt1,jt3,jt4,jt5;
    JLabel lb1,lb2,lb3,lb4,lb5,lb6;
    JButton jb1,jb2,jb3;
    JRadioButton r1,r2;
    ButtonGroup bg;
    BillSearch()
    {
        f=new JFrame("SEARCH BILL DETAILS");
        f.getContentPane().setBackground(Color.YELLOW);
        
        lb1=new JLabel("ENTER BILL NO:");
        lb1.setBounds(100,50,150,30);
        f.add(lb1);
        
        jt1=new JTextField();
        jt1.setBounds(250,50,150,30);
        f.add(jt1);
        
        lb2=new JLabel("ENTER BILL TYPE:");
        lb2.setBounds(100,90,250,30);
        f.add(lb2);
        
        r1=new JRadioButton("Sales");    
        r2=new JRadioButton("Purchased");    
        r1.setBounds(250,90,100,30);    
        r2.setBounds(360,90,100,30);    
        bg=new ButtonGroup();    
        bg.add(r1);
        bg.add(r2);    
        f.add(r1);
        f.add(r2); 
        
        jb1=new JButton("SEARCH BILL DETAILS");
        jb1.setBounds(100,140,210,30);
        f.add(jb1);
        
        jb2=new JButton("CLEAR");
        jb2.setBounds(340,140,150,30);
        f.add(jb2);
        
        lb3=new JLabel("BILL DETAILS ARE DISPLAYED BELOW IF EXISTS!!");
        lb3.setBounds(100,220,380,30);
        f.add(lb3);
        
        lb4=new JLabel("CUSTOMER/SELLER NAME:");
        lb4.setBounds(100,260,200,30);
        f.add(lb4);
        
        jt3=new JTextField();
        jt3.setBounds(300,260,150,30);
        f.add(jt3);
        
        lb5=new JLabel("WEIGHT OF FABRIC:");
        lb5.setBounds(100,300,200,30);
        f.add(lb5);
        
        jt4=new JTextField();
        jt4.setBounds(300,300,150,30);
        f.add(jt4);
        
        lb6=new JLabel("AMOUNT RECIEVED/PAID:");
        lb6.setBounds(100,340,150,30);
        f.add(lb6);
        
        jt5=new JTextField();
        jt5.setBounds(300,340,200,30);
        f.add(jt5);
        
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
            if(r1.isSelected())
            {
                lb4.setText("CUSTOMER NAME:");
                lb6.setText("AMOUNT RECIEVED:");
                try
                {
                      Connection con=null;
                      con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","57241516");
                      System.out.println("Connected");
                     
                      String sql = "select customer_name,weight,inccost from IncomeBills where billno = '"+jt1.getText()+"'";    
                      PreparedStatement ps = con.prepareStatement(sql);
                      ResultSet rs = ps.executeQuery();
                      
                      if(rs.next())
                      {
                            String name=rs.getString("customer_name");
                            int wgt=rs.getInt("weight");
                            int cost=rs.getInt("inccost");
                            
                            JOptionPane.showMessageDialog(null, "SALES BILL IS FOUNDED!!");
                            jt3.setText(name);
                            jt4.setText(String.valueOf(wgt));
                            jt5.setText(String.valueOf(cost));
                            System.out.println("Available");
                      }
                      else
                      {
                            JOptionPane.showMessageDialog(null, "BILL IS NOT AVAILABLE!!");
                      }
                }
                catch(Exception e) 
                { 
                        System.out.println(e); 
                }  
            }
            if(r2.isSelected()) 
            {
                 lb4.setText("SUPPLIER NAME:");
                 lb6.setText("AMOUNT PAID:");
                            
                  try
                  {
                      Connection con=null;
                      con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","57241516");
                      System.out.println("Connected");
                     
                      String sql = "select * from ExpenseBills where billno = '"+jt1.getText()+"'";    
                      PreparedStatement ps = con.prepareStatement(sql);
                      ResultSet rs = ps.executeQuery();
                      
                      if(rs.next())
                      {
                            String name=rs.getString("supplier_name");
                            int wgt=rs.getInt("weight");
                            int cost=rs.getInt("expcost");
                            JOptionPane.showMessageDialog(null, "PURCHASED BILL IS FOUNDED!!");
                            jt3.setText(String.valueOf(name));
                            jt4.setText(String.valueOf(wgt));
                            jt5.setText(String.valueOf(cost));
                            System.out.println("Available");      
                      }
                      else
                      {
                            JOptionPane.showMessageDialog(null, "BILL IS NOT AVAILABLE!!");
                      }
                }
                catch(Exception e) 
                { 
                        System.out.println(e); 
                }
            }
        }
        if(ae.getSource()==jb2)
        {
                jt1.setText(String.valueOf(""));
                jt3.setText(String.valueOf(""));
                jt4.setText(String.valueOf(""));
                jt5.setText(String.valueOf(""));
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
        BillSearch s = new BillSearch();
    }   
}