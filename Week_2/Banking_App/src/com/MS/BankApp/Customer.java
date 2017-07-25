package com.MS.BankApp;

import java.sql.SQLException;
import java.util.Scanner;
import com.MS.BankApp.Application;
import com.MS.OJDBC.UserDAO;

public class Customer extends User{
	String privileges = "customer";
	int acctno;
	Double checkingbal;
	Double savingsbal;
	
	String menuchoice;
	
	public Customer(String name, String acctnos, String checking, String savings) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.acctno = Integer.parseInt(acctnos);
		if(!checking.equals("null"))
		{
			this.checkingbal = Double.parseDouble(checking);
		}
		else
		{
			this.checkingbal = (double) 0;
		}
		if(!savings.equals("null"))
		{
			this.savingsbal = Double.parseDouble(savings);
		}
		else
		{
			this.savingsbal = (double) 0;
		}
	}
	@Override
	public void menu() {
		// TODO Auto-generated method stub
		while(!"end".equals(menuchoice))
		{
			
			
			Application.logger.info("1. View my accounts balance");
			Application.logger.info("2. Deposit to account");
			Application.logger.info("3. Withdraw from account");
			Application.logger.info("4. Request to open an account");
			Application.logger.info("5. Logout\n");
			
			Scanner scan = new Scanner(System.in);
			menuchoice = scan.nextLine();
			
			switch(menuchoice)
			{
			case("1"):
			{
				this.viewBalance();
				break;
			}
			case("2"):
			{
				Application.logger.info("Deposit to which account and how much? Use format \"AccountType:amount\"");
				User.scan = new Scanner(System.in);
				String depositdata = User.scan.nextLine();
				this.deposit(depositdata);
				break;
			}
			
			case("3"):
			{
				Application.logger.info("Withdraw from which account and how much? Use format \"AccountType:amount\"");
				User.scan = new Scanner(System.in);
				String withdrawdata = User.scan.nextLine();
				this.withdraw(withdrawdata);
				break;
			}
			case("4"):
			{
				Application.logger.info("What type of account would you like to open? (checking/savings)");
				User.scan = new Scanner(System.in);
				String newacct = User.scan.nextLine();
				this.openAccount(newacct);
				break;
			}
			case("5"):
			{
				this.logout();
				menuchoice = "end";
				break;
			}
			default:
			{
				Application.logger.error("Input not recognized.\nReturning to menu");
				break;
			}
			}
		}
		
		
	}
	/*
	 * Customer request to open a new account. Simply flags a string for approval/denial
	 */
	public void openAccount(String newacct) {
		// TODO Auto-generated method stub
		
		
		if("checking".equals(newacct.toLowerCase()))
		{
			Application.logger.debug("A request to open a checking account has been opened\n");
			Application.requests[Application.userid] = newacct.toLowerCase();
		}
		else if("savings".equals(newacct.toLowerCase()))
		{
			Application.logger.debug("A request to open a savings account has been opened\n");
			Application.requests[Application.userid] = newacct.toLowerCase();
		}
		else
		{
			Application.logger.error("Error: Unrecognized input. Please type either \"checking\" or \"savings\"\n");
		}
		
	}
	/*
	 * Withdraws a set amount from a specified account. Input should be of
	 * the format "accounttype:amount". Has safeguards in place for invalid inputs.
	 */
	public void withdraw(String withdrawdata) {
		// TODO Auto-generated method stub
		UserDAO dao = new UserDAO();
		
		String withdraw[] = withdrawdata.split(":");
		
		if("checking".equals(withdraw[0].toLowerCase()) && !"null".equals(Application.checlist[Application.userid]))
		{
			if(Double.parseDouble(withdraw[1])>0 && Double.parseDouble(withdraw[1])<=
					Double.parseDouble(Application.checlist[Application.userid]))
			{
				//Entered number is acceptable
				Application.logger.debug("Withdrawing "+withdraw[1]+" from "+Application.namelist[Application.userid]
						+" checking account");
				Double newval = Double.parseDouble(Application.checlist[Application.userid]) - 
						Double.parseDouble(withdraw[1]);
				Application.checlist[Application.userid] = Double.toString(newval);
				
				try {
					dao.saveLog("Withdraw", Application.namelist[Application.userid], withdraw[0], withdraw[1]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.checkingbal = newval;
			}
			else if(Double.parseDouble(withdraw[1])>0 && Double.parseDouble(withdraw[1])>
					Double.parseDouble(Application.checlist[Application.userid]))
			{
				Application.logger.error("Withdrawal amount greater than funds left. Cannot overdraw");
				Application.logger.error("Returing to menu");
			}
			else
			{
				Application.logger.error("Invalid amount to withdraw. Returning to menu");
			}
			
		}
		else if("savings".equals(withdraw[0].toLowerCase()) && !"null".equals(Application.savlist[Application.userid]))
		{
			if(Double.parseDouble(withdraw[1])>0 && Double.parseDouble(withdraw[1])<
					Double.parseDouble(Application.checlist[Application.userid]))
			{
				//Entered number is acceptable
				Application.logger.debug("Withdrawing "+withdraw[1]+" from "+Application.namelist[Application.userid]
						+" savings account");
				Double newval = Double.parseDouble(Application.savlist[Application.userid]) 
						- Double.parseDouble(withdraw[1]);
				Application.savlist[Application.userid] = Double.toString(newval);
				
				try {
					dao.saveLog("Withdraw", Application.namelist[Application.userid], withdraw[0], withdraw[1]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.savingsbal = newval;
			}
			else if(Double.parseDouble(withdraw[1])>0 && Double.parseDouble(withdraw[1])>
			Double.parseDouble(Application.savlist[Application.userid]))
			{
				Application.logger.error("Withdrawal amount greater than funds left. Cannot overdraw");
				Application.logger.error("Returing to menu");
			}
			else
			{
				Application.logger.error("Invalid amount to deposit. Returning to menu");
			}
		}
		else
		{
			Application.logger.error("Invalid account type. Account not created");
		}
		System.out.println();
		
		
		
	}
	/*
	 * Deposits a set amount of money into a specified account. Input should
	 * be of the format "accounttype:amount". Has safeguards in place to prevent
	 * invalid inputs.
	 */
	public void deposit(String depositdata) {
		// TODO Auto-generated method stub
		
		UserDAO dao = new UserDAO();
		
		String deposit[] = depositdata.split(":");
		
		if("checking".equals(deposit[0].toLowerCase()))
		{
			if(Double.parseDouble(deposit[1])>0 && !"null".equals(Application.checlist[Application.userid]))
			{
				//Entered number is acceptable
				Application.logger.debug("Depositing "+deposit[1]+" to "+Application.namelist[Application.userid]
						+" checking account");
				Double newval = Double.parseDouble(Application.checlist[Application.userid]) + 
						Double.parseDouble(deposit[1]);
				Application.checlist[Application.userid] = Double.toString(newval);
				this.checkingbal = newval;
				
				try {
					dao.saveLog("Deposit", Application.namelist[Application.userid], deposit[0], deposit[1]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				Application.logger.error("Invalid amount to deposit. Returning to menu");
			}
			
		}
		else if("savings".equals(deposit[0].toLowerCase()) && !"null".equals(Application.savlist[Application.userid]))
		{
			if(Double.parseDouble(deposit[1])>0)
			{
				//Entered number is acceptable
				Application.logger.debug("Depositing "+deposit[1]+" to "+Application.namelist[Application.userid]
						+" savings account");
				Double newval = Double.parseDouble(Application.savlist[Application.userid]) 
						+ Double.parseDouble(deposit[1]);
				Application.savlist[Application.userid] = Double.toString(newval);
				
				try {
					dao.saveLog("Deposit", Application.namelist[Application.userid], deposit[0], deposit[1]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.savingsbal = newval;
			}
			else
			{
				Application.logger.error("Invalid amount to deposit. Returning to menu");
			}
		}
		else
		{
			Application.logger.debug("Invalid account type. Account not created");
		}
		System.out.println();
		
		
	}
	/*
	 * Views the balances of this customer's accounts.
	 */
	public void viewBalance() {
		// TODO Auto-generated method stub
		
		Application.logger.info("Number of accounts: " + this.acctno);
		if("null".equals(Application.checlist[Application.userid]))
		{
			Application.logger.error("Checking: Account not made");
		}
		else
		{
			Application.logger.debug("Checking: $" + this.checkingbal);
		}
		if("null".equals(Application.savlist[Application.userid]))
		{
			Application.logger.error("Savings: Account not made");
		}
		else
		{
			Application.logger.debug("Savings: $" + this.savingsbal);
		}
		System.out.println();
		
	}
	
	/*
	 * Getters and setters
	 */
	public int getAcctno() {
		return acctno;
	}
	public void setAcctno(int acctno) {
		this.acctno = acctno;
	}
	public String getPrivileges() {
		return privileges;
	}
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
	public Double getCheckingbal() {
		return checkingbal;
	}
	public void setCheckingbal(Double checkingbal) {
		this.checkingbal = checkingbal;
	}
	public Double getSavingsbal() {
		return savingsbal;
	}
	public void setSavingsbal(Double savingsbal) {
		this.savingsbal = savingsbal;
	}

	
	
}
