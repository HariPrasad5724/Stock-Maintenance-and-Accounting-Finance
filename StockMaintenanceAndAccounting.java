package stockmaintenanceandaccounting;
import java.awt.*;
import javax.swing.*;    
import java.awt.event.*;  

public class StockMaintenanceAndAccounting extends Canvas implements ActionListener
{
    JFrame f;    
    JMenuBar mb;
    JPanel panel;    
    JLabel lb1,lb2;
    JMenu stock,customer,Billing,Profit;    
    JMenuItem insertFab,removeFab,searchFab,insertCust,checkStatus,insertIncome,insertExpense,checkProfit,searchBill,searchCust,checkAct,searchBDate,checkMonProfit;   
       
    StockMaintenanceAndAccounting()
    {    
	f=new JFrame("STOCK MAINTENANCE AND ACCOUNTING ");
	f.getContentPane().setBackground(Color.BLACK);
        
	mb=new JMenuBar();
        stock=new JMenu("STOCK MAINTENANCE REPORT");    
	customer=new JMenu("CUSTOMER REQUIREMENT REPORT");    
	Billing=new JMenu("BILL REPORTS");
        Profit=new JMenu("PROFIT AND ACTIVITY TRACKING");
	
        lb1=new JLabel("UNIQUE FABZ PORTAL");
        lb1.setBounds(240,200,400,50);
        f.add(lb1);
        
		insertFab=new JMenuItem("ADD FABRIC");    
		removeFab=new JMenuItem("REMOVE FABRIC");
                searchFab=new JMenuItem("SEARCH FABRIC");
                insertCust=new JMenuItem("ADD CUSTOMER DETAILS");  
		checkStatus=new JMenuItem("CHECK READY CUSTOMER");
                searchCust=new JMenuItem("SEARCH CUSTOMER");
                checkProfit=new JMenuItem("CHECK ANNUAL PROFIT");
                insertIncome=new JMenuItem("ADD SALES BILLS");        
                insertExpense=new JMenuItem("ADD PURCHASED BILLS"); 
                searchBill=new JMenuItem("SEARCH via BILL NO");
                checkAct=new JMenuItem("CHECK ACTIVITY");
                searchBDate=new JMenuItem("SEARCH via DATE");
                checkMonProfit=new JMenuItem("CHECK MONTHLY PROFIT");
		
                stock.add(insertFab);
		stock.add(removeFab);
                stock.add(searchFab);
                customer.add(insertCust);
                customer.add(searchCust);
                customer.add(checkStatus);
                Billing.add(insertIncome);
                Billing.add(insertExpense);
                Billing.add(searchBill);
                Billing.add(searchBDate);
                Profit.add(checkAct);
                Profit.add(checkProfit);
                Profit.add(checkMonProfit);
                
                mb.add(stock);
		mb.add(customer);
		mb.add(Billing);      
                mb.add(Profit);
		insertFab.addActionListener(this);    
		removeFab.addActionListener(this);
                searchFab.addActionListener(this);
                insertCust.addActionListener(this);
		insertIncome.addActionListener(this);
                insertExpense.addActionListener(this);
                checkProfit.addActionListener(this);
		checkStatus.addActionListener(this);
                searchCust.addActionListener(this);
                searchBill.addActionListener(this);
                checkAct.addActionListener(this);
                searchBDate.addActionListener(this);
                checkMonProfit.addActionListener(this);
                
                f.add(mb);
        	f.setJMenuBar(mb);
                
     
                 panel=new JPanel();  
                 panel.setBounds(100,100,400,400);    
                 panel.setBackground(Color.yellow);  
                 f.add(panel);
                
                 f.setLayout(null);    
                f.setSize(700,700);    
		f.setVisible(true); 
                f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) 
	{            
		if(e.getSource()==insertFab)
		{
                    f.setVisible(false);
                    InsertStock addFab= new InsertStock();
                    f.dispose();
                }
		if(e.getSource()==removeFab)    
		{    
                    f.setVisible(false);
                    RemoveStock remFab= new RemoveStock();
                    f.dispose();
		}
                if(e.getSource()==searchFab)    
		{
                    f.setVisible(false);
                    FabricSearch findFab= new FabricSearch();
                    f.dispose();
                }
		if(e.getSource()==insertCust)    
		{   
                    f.setVisible(false);
                    CustomerDetails addCust=new CustomerDetails();
                    f.dispose();
		}                
		if(e.getSource()==insertIncome)    
		{   
                    f.setVisible(false);
                    IncomeBills insBill= new IncomeBills();
                    f.dispose();
        	}
		if(e.getSource()==insertExpense)    
		{   
                    f.setVisible(false);
                    ExpenseBills expBill= new ExpenseBills();
                    f.dispose();
       		}
                if(e.getSource()==checkStatus)
		{   
                    f.setVisible(false);
                    StatusCheck findSt= new StatusCheck();
                    f.dispose();
		}
                
                if(e.getSource()==checkAct)
		{   
                    f.setVisible(false);
                    ActivitiyCheck activity= new ActivitiyCheck();
                    f.dispose();
		}
                if(e.getSource()==checkProfit)
		{   
                    f.setVisible(false);
                    ProfitCheck findProfit= new ProfitCheck();
                    f.dispose();
		}
                
                if(e.getSource()==searchCust)    
		{
                    f.setVisible(false);
                    CustomerSearch  findCust= new CustomerSearch();
                    f.dispose();
		}
                
		if(e.getSource()==searchBill)    
		{   
                    f.setVisible(false);
                    BillSearch findBill= new BillSearch();
                    f.dispose();
		}
                if(e.getSource()==searchBDate)    
		{   
                    f.setVisible(false);
                    BillSearchDate findBillDate= new BillSearchDate();
                    f.dispose();
		}
                
                if(e.getSource()==checkMonProfit)    
		{   
                    f.setVisible(false);
                    MonthlyProfit findMonthPro= new MonthlyProfit();
                    f.dispose();
		}
               
	}    
        
	public static void main(String[] args) 
	{
            StockMaintenanceAndAccounting s=new StockMaintenanceAndAccounting();
        }    
}