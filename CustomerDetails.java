package stockmaintenanceandaccounting;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class CustomerDetails implements ActionListener
{
    JFrame f;
    JTextField jt1,jt2;
    JLabel lb1,lb2,lb3,lb4,lb5;
    JButton jb1,jb2,jb3;
    JComboBox cb1,cb2,cb3;
    CustomerDetails()
    {
        f=new JFrame("CUSTOMER DETAILS WITH REQUIREMENTS");
        f.getContentPane().setBackground(Color.CYAN);
        
        lb1=new JLabel("ENTER CUSTOMER NAME:");
        lb1.setBounds(100,50,200,30);
        f.add(lb1);
        
        jt1=new JTextField();
        jt1.setBounds(330,50,150,30);
        f.add(jt1);
        
        lb2=new JLabel("ENTER CUSTOMER CONTACT:");
        lb2.setBounds(100,90,200,30);
        f.add(lb2);
        
        jt2=new JTextField();
        jt2.setBounds(330,90,150,30);
        f.add(jt2);
        
        lb3=new JLabel("ENTER FABRIC TYPE:");
        lb3.setBounds(100,130,150,30);
        f.add(lb3);
        
        String type[]={"AIRTEX","LYCRA","DURBY","JERSEY"};        
        cb1=new JComboBox(type);    
        cb1.setBounds(250,130,150,30);    
        f.add(cb1);
        
        lb4=new JLabel("ENTER FABRIC COLOR:");
        lb4.setBounds(100,170,150,30);
        f.add(lb4);
        
        String color[]={"TURKOISE_BLUE","LEMON_YELLOW","VIOLET","BROWN","DARK_RED","WHITE","HALF_WHITE","LIGHT_RED","BABY_PINK","DARK_PINK","PARROT_GREEN","GRAY","MAD_BLACK","MAROON","THICK_YELLOW","DARK_GREEN","SKY_BLUE","NAVY_BLUE"};
        cb2=new JComboBox(color);    
        cb2.setBounds(250,170,150,30);    
        f.add(cb2);
        
        lb5=new JLabel("ENTER FABRIC GSM:");
        lb5.setBounds(100,210,150,30);
        f.add(lb5);
        
        String gsm[]={"200","210","220","230","240","250","260","270","280","290","300"};        
        cb3=new JComboBox(gsm);    
        cb3.setBounds(250, 210,150,30);    
        f.add(cb3);
        
        jb1=new JButton("ADD CUSTOMER");
        jb1.setBounds(100,300,170,50);
        f.add(jb1);
        
        jb2=new JButton("CLEAR");
        jb2.setBounds(300,300,130,50);
        f.add(jb2);
        
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
            String type =String.valueOf(cb1.getItemAt(cb1.getSelectedIndex()));  
            String color =String.valueOf(cb2.getItemAt(cb2.getSelectedIndex()));  
            String gsm =String.valueOf(cb3.getItemAt(cb3.getSelectedIndex())); 
             try
	     {
                      Connection con=null;
                      con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","57241516");
                      System.out.println("Connected");
                     
                      String sql = "insert into CustomerDetails values('" +jt1.getText()+ "','" +jt2.getText()+ "','" +type+ "','" +color+ "','" +gsm+ "')";
                      PreparedStatement ps = con.prepareStatement(sql);
                      ResultSet rs = ps.executeQuery();
                      
                      if(rs.next())
                      {
                            System.out.println("Inserted");
                            JOptionPane.showMessageDialog(null, "CUSTOMER ADDED SUCCESSFULLY!");
                      }
                      else
                      {
                            JOptionPane.showMessageDialog(null, "PLEASE TRY AGAIN");
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
        CustomerDetails a=new CustomerDetails();
    }
}