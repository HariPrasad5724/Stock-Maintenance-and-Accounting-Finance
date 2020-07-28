package stockmaintenanceandaccounting;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class FabricSearch implements ActionListener
{
    JFrame f;
    JTextField jt1,jt2,jt3;
    JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7;
    JButton jb1,jb2,jb3;
    JComboBox cb1,cb2,cb3;
    FabricSearch()
    {
        f=new JFrame("SEARCH FABRIC AVAILABILITY");
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
        
        String color[]={"TURKOISE-BLUE","LEMON_YELLOW","VIOLET","BROWN","DARK_RED","WHITE","HALF_WHITE","LIGHT_RED","BABY_PINK","DARK_PINK","PARROT_GREEN","GRAY","MAD_BLACK","MAROON","THICK_YELLOW","DARK_GREEN","SKY_BLUE","NAVY_BLUE"};
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
        
        jb1=new JButton("SEARCH FABRIC");
        jb1.setBounds(90,200,140,50);
        f.add(jb1);
        
        jb2=new JButton("CLEAR");
        jb2.setBounds(300,200,140,50);
        f.add(jb2);
        
        lb7=new JLabel("THE EXISTING FABRIC DETAILS ARE DISPLAYED !!");
        lb7.setBounds(100,260,380,30);
        f.add(lb7);
        lb7.setVisible(false);
        
        lb4=new JLabel("AVAILABLE WEIGHT:");
        lb4.setBounds(100,300,150,30);
        f.add(lb4);
        
        jt1=new JTextField();
        jt1.setBounds(250,300,150,30);
        f.add(jt1);
        
        lb5=new JLabel("COST PER KG:");
        lb5.setBounds(100,340,150,30);
        f.add(lb5);
        
        jt2=new JTextField();
        jt2.setBounds(250,340,150,30);
        f.add(jt2);
        
        lb6=new JLabel("STOCK ROOM ID:");
        lb6.setBounds(100,380,150,30);
        f.add(lb6);
        
        jt3=new JTextField();
        jt3.setBounds(250,380,150,30);
        f.add(jt3);
        
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
            String type =String.valueOf(cb1.getItemAt(cb1.getSelectedIndex()));  
            String color =String.valueOf(cb2.getItemAt(cb2.getSelectedIndex()));  
            String gsm =String.valueOf(cb3.getItemAt(cb3.getSelectedIndex()));  
             try
	     {
                      Connection con=null;
                      con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","572415");
                      System.out.println("Connected");
                     
                      String sql = "select weight,cost,stockid from StockItems where fabtype = '"+type+"' and color = '"+color+"' and gsm = '"+gsm+"'";    
                      PreparedStatement ps = con.prepareStatement(sql);
                      ResultSet rs = ps.executeQuery();
                      
                      if(rs.next())
                      {      
                            lb7.setVisible(true);
                            int wgt=rs.getInt("weight");
                            int cost=rs.getInt("cost");
                            int id=rs.getInt("stockid");
                            
                            JOptionPane.showMessageDialog(null, "FABRIC IS AVAILABLE IN STOCK!!");
                            jt1.setText(String.valueOf(wgt));
                            jt2.setText(String.valueOf(cost));
                            jt3.setText(String.valueOf(id));
                            System.out.println("Available");
                      }
                      else
                      {
                            JOptionPane.showMessageDialog(null, "FABRIC IS NOT AVAILABLE IN STOCK!!");
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
        FabricSearch s = new FabricSearch();
    }
}