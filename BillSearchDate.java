package stockmaintenanceandaccounting;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class BillSearchDate implements ActionListener 
{
    JFrame f;
    JTextField jt1,jt2,jt3;
    JLabel lb1,lb2,lb3,lb4,lb5,lb6;
    JButton jb1,jb2,jb3;
    JComboBox cb1,cb2,cb3;
    BillSearchDate()
    {
        f=new JFrame("SEARCH DATE TO FIND BILL");
        f.getContentPane().setBackground(Color.CYAN);
        
        lb1=new JLabel("ENTER DATE (DD/MMM/YYY):");
        lb1.setBounds(100,80,230,30);
        f.add(lb1);
        
        String day[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};        
        cb1=new JComboBox(day);    
        cb1.setBounds(300,80,50,30);    
        f.add(cb1);
        
        String month[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        cb2=new JComboBox(month);    
        cb2.setBounds(370,80,60,30);    
        f.add(cb2);
        
        String year[]={"2019","2020","2021"};        
        cb3=new JComboBox(year);    
        cb3.setBounds(450,80,70,30);    
        f.add(cb3);
        
        jb1=new JButton("SEARCH BILL DETAILS");
        jb1.setBounds(100,140,210,30);
        f.add(jb1);
        
        jb2=new JButton("CLEAR");
        jb2.setBounds(340,140,150,30);
        f.add(jb2);
        
        lb2=new JLabel("BILL DETAILS ARE DISPLAYED BELOW IF EXISTS!!");
        lb2.setBounds(100,220,380,30);
        f.add(lb2);
        
        lb3=new JLabel("BILL NO:");
        lb3.setBounds(100,260,200,30);
        f.add(lb3);
        
        jt1=new JTextField();
        jt1.setBounds(300,260,150,30);
        f.add(jt1);
        
        lb4=new JLabel("CUSTOMER NAME:");
        lb4.setBounds(100,300,200,30);
        f.add(lb4);
        
        jt2=new JTextField();
        jt2.setBounds(300,300,150,30);
        f.add(jt2);
        
        lb5=new JLabel("AMOUNT RECIEVED:");
        lb5.setBounds(100,340,150,30);
        f.add(lb5);
        
        jt3=new JTextField();
        jt3.setBounds(300,340,200,30);
        f.add(jt3);
        
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
                     
                      String sql = "select billno,customer_name,inccost from IncomeBills where day='"+day+"' and mon='"+mon+"' and year='"+year+"' ";    
                      PreparedStatement ps = con.prepareStatement(sql);
                      ResultSet rs = ps.executeQuery();
                      
                      while(rs.next())
                      {
                            System.out.println("Bill Available!");
                            JOptionPane.showMessageDialog(null, "THE BILL AND CUSTOMER FOUNDED!!");
                            jt1.setText(rs.getString(1));
                            jt2.setText(rs.getString(2));
                            jt3.setText(rs.getString(3));
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
        BillSearchDate s = new BillSearchDate();
    }   
}