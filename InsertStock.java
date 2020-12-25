package stockmaintenanceandaccounting;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class InsertStock implements ActionListener 
{
    JFrame f;
    JTextField jt1,jt2;
    JLabel lb1,lb2,lb3,lb4,lb5,lb6;
    JButton jb1,jb2,jb3;
    JComboBox cb1,cb2,cb3,cb4;
    InsertStock()
    {
        f=new JFrame("ADD FABRICS TO STOCK");
        f.getContentPane().setBackground(Color.CYAN);
        
        lb1=new JLabel("ENTER FABRIC TYPE:");
        lb1.setBounds(100,50,150,30);
        f.add(lb1);
        
        String type[]={"AIRTEX","LYCRA","DURBY","JERSEY"};        
        cb1=new JComboBox(type);    
        cb1.setBounds(250,50,150,30);    
        f.add(cb1);
        
        lb2=new JLabel("ENTER FABRIC COLOR:");
        lb2.setBounds(100,90,150,30);
        f.add(lb2);
        
        String color[]={"TURKOISE_BLUE","LEMON_YELLOW","VIOLET","BROWN","DARK_RED","WHITE","HALF_WHITE","LIGHT_RED","BABY_PINK","DARK_PINK","PARROT_GREEN","GRAY","MAD_BLACK","MAROON","THICK_YELLOW","DARK_GREEN","SKY_BLUE","NAVY_BLUE"};
        cb2=new JComboBox(color);    
        cb2.setBounds(250,90,150,30);    
        f.add(cb2);
        
        lb3=new JLabel("ENTER FABRIC GSM:");
        lb3.setBounds(100,130,150,30);
        f.add(lb3);
        
        String gsm[]={"200","210","220","230","240","250","260","270","280","290","300"};        
        cb3=new JComboBox(gsm);    
        cb3.setBounds(250, 130,150,30);    
        f.add(cb3);
        
        lb4=new JLabel("ENTER TOTAL WEIGHT:");
        lb4.setBounds(100,170,150,30);
        f.add(lb4);
        
        jt1=new JTextField();
        jt1.setBounds(250,170,150,30);
        f.add(jt1);
        
        lb5=new JLabel("ENTER COST PER KG:");
        lb5.setBounds(100,210,150,30);
        f.add(lb5);
        
        jt2=new JTextField();
        jt2.setBounds(250,210,150,30);
        f.add(jt2);
        
        lb6=new JLabel("ENTER STOCK ID:");
        lb6.setBounds(100,250,150,30);
        f.add(lb6);
        
        String id[]={"1","2","3"};        
        cb4=new JComboBox(id);    
        cb4.setBounds(250, 250,50,20);    
        f.add(cb4);
        
        jb1=new JButton("ADD FABRIC");
        jb1.setBounds(100,340,130,50);
        f.add(jb1);
        
        jb2=new JButton("CLEAR");
        jb2.setBounds(280,340,130,50);
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
            String id =String.valueOf(cb4.getItemAt(cb4.getSelectedIndex()));  
             try
	     {
                      Connection con=null;
                      con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","57241516");
                      System.out.println("Connected");
                     
                      String sql = "insert into StockItems values('" +type+ "','" +color+ "','" +gsm+ "','" +jt1.getText()+ "','" +jt2.getText()+ "','" +id+ "')";    
                      PreparedStatement ps = con.prepareStatement(sql);
                      ResultSet rs = ps.executeQuery();
                      
                      if(rs.next())
                      {
                            System.out.println("Inserted");
                            JOptionPane.showMessageDialog(null, "FABRIC ADDED TO STOCK SUCCESSFULLY!");
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
        InsertStock a=new InsertStock();
    }
}