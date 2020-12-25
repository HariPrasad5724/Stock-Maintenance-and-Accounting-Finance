package stockmaintenanceandaccounting;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class ExpenseBills implements ActionListener
{
    JFrame f;
    JTextField jt1,jt2,jt3,jt4,jt5,jt6,jt7;
    JLabel lb1,lb2,lb3,lb4,lb5;
    JButton jb1,jb2,jb3;
    JComboBox cb1,cb2,cb3;
    ExpenseBills()
    {
        f=new JFrame("ADD PURCHASED BILLS");
        f.getContentPane().setBackground(Color.YELLOW);
        
        lb1=new JLabel("ENTER SUPPLIER NAME:");
        lb1.setBounds(100,50,150,30);
        f.add(lb1);
        
        jt1=new JTextField();
        jt1.setBounds(280,50,150,30);
        f.add(jt1);
        
        lb2=new JLabel("ENTER BILL NO:");
        lb2.setBounds(100,90,150,30);
        f.add(lb2);
        
        jt2=new JTextField();
        jt2.setBounds(280,90,150,30);
        f.add(jt2);
        
        lb3=new JLabel("ENTER TOTAL WEIGHT:");
        lb3.setBounds(100,130,150,30);
        f.add(lb3);
        
        jt3=new JTextField();
        jt3.setBounds(280,130,150,30);
        f.add(jt3);
        
        lb4=new JLabel("AMOUNT PAID:");
        lb4.setBounds(100,170,150,30);
        f.add(lb4);
        
        jt4=new JTextField();
        jt4.setBounds(280,170,150,30);
        f.add(jt4);
        
        lb5=new JLabel("DATE:(DAY/MON/YEAR)");
        lb5.setBounds(100,220,150,30);
        f.add(lb5);
        
        String day[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};        
        cb1=new JComboBox(day);    
        cb1.setBounds(280,220,50,30);    
        f.add(cb1);
        
        String month[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        cb2=new JComboBox(month);    
        cb2.setBounds(360,220,60,30);    
        f.add(cb2);
        
        String year[]={"2020","2021"};        
        cb3=new JComboBox(year);    
        cb3.setBounds(450,220,80,30);    
        f.add(cb3);
        
        jb1=new JButton("ADD PURCHASED BILLS");
        jb1.setBounds(100,300,200,50);
        f.add(jb1);
        
        jb2=new JButton("CLEAR");
        jb2.setBounds(320,300,130,50);
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
            String day =String.valueOf(cb1.getItemAt(cb1.getSelectedIndex()));  
            String mon =String.valueOf(cb2.getItemAt(cb2.getSelectedIndex()));  
            String year =String.valueOf(cb3.getItemAt(cb3.getSelectedIndex())); 
            
             try
	     {
                      Connection con=null;
                      con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","57241516");
                      System.out.println("Connected");
                     
                      String sql = "insert into ExpenseBills values('" +jt1.getText()+ "','" +jt2.getText()+ "','" +jt3.getText()+ "','" +jt4.getText()+ "','" +day+ "','" +mon+ "','" +year+ "')";
                      PreparedStatement ps = con.prepareStatement(sql);
                      ResultSet rs = ps.executeQuery();
                      
                      if(rs.next())
                      {
                            System.out.println("Inserted");
                            JOptionPane.showMessageDialog(null, "PURCHASE BILL IS ADDED SUCCESSFULLY!");
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
            jt3.setText(String.valueOf(""));
            jt4.setText(String.valueOf(""));
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
        ExpenseBills a=new ExpenseBills();
    }
}