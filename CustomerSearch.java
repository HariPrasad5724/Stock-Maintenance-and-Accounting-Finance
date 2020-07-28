package stockmaintenanceandaccounting;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class CustomerSearch implements ActionListener
{
    JFrame f;
    JTextField jt1,jt2,jt3,jt4,jt5;
    JLabel lb1,lb2,lb3,lb4,lb5,lb6;
    JButton jb1,jb2,jb3;
    
    CustomerSearch()
    {
        f=new JFrame("SEARCH CUSTOMER DETAILS");
        f.getContentPane().setBackground(Color.YELLOW);
        
        lb1=new JLabel("ENTER CUSTOMER NAME:");
        lb1.setBounds(100,50,200,30);
        f.add(lb1);
        
        jt1=new JTextField();
        jt1.setBounds(300,50,150,30);
        f.add(jt1);
        
        lb2=new JLabel("ENTER CUSTOMER CONTACT:");
        lb2.setBounds(100,90,200,30);
        f.add(lb2);
        
        jt2=new JTextField();
        jt2.setBounds(300,90,150,30);
        f.add(jt2);
        
        jb1=new JButton("SEARCH CUSTOMER");
        jb1.setBounds(100,130,160,50);
        f.add(jb1);
        
        jb2=new JButton("CLEAR");
        jb2.setBounds(300,130,150,50);
        f.add(jb2);
        
        lb6=new JLabel("STOCK DETAILS WILL BE PRINTED HERE IF AVAILABLE!");
        lb6.setBounds(100,240,370,30);
        f.add(lb6);
        
        lb3=new JLabel("REQUESTED FABRIC TYPE:");
        lb3.setBounds(100,280,200,30);
        f.add(lb3);
        
        jt3=new JTextField();
        jt3.setBounds(330,280,150,30);
        f.add(jt3);
        
        lb4=new JLabel("REQUESTED FABRIC COLOR:");
        lb4.setBounds(100,320,200,30);
        f.add(lb4);
        
        jt4=new JTextField();
        jt4.setBounds(330,320,150,30);
        f.add(jt4);
        
        lb5=new JLabel("REQUESTED FABRIC GSM:");
        lb5.setBounds(100,360,200,30);
        f.add(lb5);
        
        jt5=new JTextField();
        jt5.setBounds(330,360,150,30);
        f.add(jt5);
        
        jb3=new JButton("BACK HOME");
        jb3.setBounds(190,450,150,50);
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
                      Connection con=null;
                      con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","572415");
                      System.out.println("Connected");
                     
                      String sql = "select fabtype,color,gsm from CustomerDetails where customer_name='"+jt1.getText()+"' or customer_phone='"+jt2.getText()+"' ";    
                      PreparedStatement ps = con.prepareStatement(sql);
                      ResultSet rs = ps.executeQuery();
                      
                      if(rs.next())
                      {
                            System.out.println("Customer Founded!");
                            String type =rs.getString("fabtype");
                            String color =rs.getString("color");
                            int gsm=rs.getInt("gsm");
                            
                            JOptionPane.showMessageDialog(null, "THE CUSTOMER IS AVAILABLE!!");
                            jt3.setText(type);
                            jt4.setText(color);
                            jt5.setText(String.valueOf(gsm));
                      }
                      else
                      {
                            JOptionPane.showMessageDialog(null, "CUSTOMER DOESN'T EXIST PLEASE ENTER CORRECTLY!");
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
        CustomerSearch s=new CustomerSearch();
    }   
}