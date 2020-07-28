package stockmaintenanceandaccounting;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class StatusCheck implements ActionListener 
{
    JFrame f;
    JTextArea jt1,jt2;
    JLabel lb1;
    JButton jb1,jb2;
    StatusCheck()
    {
        f=new JFrame("READY CUSTOMER DETAILS");
        f.getContentPane().setBackground(Color.YELLOW);
        
        jb1=new JButton("LIST READY CUSTOMER");
        jb1.setBounds(100,50,300,50);
        f.add(jb1);
        
        lb1=new JLabel("CUSTOMERS WHOSE STOCK IS AVAILABLE:");
        lb1.setBounds(100,150,300,30);
        f.add(lb1);
        
        jt1=new JTextArea();
        jt1.setBounds(150,200,150,50);
        f.add(jt1);
        
        jt2=new JTextArea();
        jt2.setBounds(150,260,150,50);
        f.add(jt2);
        
        jb2=new JButton("BACK HOME");
        jb2.setBounds(150,330,150,50);
        f.add(jb2);
      
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        
       f.setLayout(null);    
       f.setSize(500,500);    
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
                      Connection con=null;
                      con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","572415");
                      System.out.println("Connected");
                     
                      String sql = "select customer_name,customer_phone from AvailableCustomers ";    
                      PreparedStatement ps = con.prepareStatement(sql);
                      ResultSet rs = ps.executeQuery();
                      
                      while(rs.next())
                      {
                            System.out.println("Customer Available!");
                           // JOptionPane.showMessageDialog(null, "THE CUSTOMER FOUND AND HE CAN BE NOTIFIED!!");
                            jt1.setText(rs.getString(1));
                            jt2.setText(rs.getString(2));
                      }
             }
		catch(Exception e) 
		{ 
                    System.out.println(e); 
		} 
        }
       
        if(ae.getSource()==jb2)
        {
            StockMaintenanceAndAccounting a=new StockMaintenanceAndAccounting();
            f.setVisible(false);
            f.dispose();
            a.setVisible(true);
        }
    }
    public static void main(String[] args) 
    {
        StatusCheck s =new StatusCheck();
    }
}