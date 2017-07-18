package com.WilliamLewis.BankingApp.BankData.Accounts;

import com.WilliamLewis.BankingApp.BankData.AccountInterface;

import com.WilliamLewis.BankingApp.BankData.BankData;

import java.io.Serializable;

import org.apache.log4j.Logger;
/**
 * @fields accountHolder is the username of the customer who owns this account, later should be updated to be a customer instead of only the customer's name
 	accountNumber acts as a means of identifying accounts uniquely, so two customers with the same name have different accounts on creation, AccountFactory handles randomization of this value
 * @author William
 *
 */
public class Account implements AccountInterface, Serializable{
	private double accountBalance;
	private int accountNumber;
	private String accountHolder;
	private static final long serialVersionUID = 4219823L;
	private static Logger log = Logger.getRootLogger();
	
	//Constructors
	public Account()
	{ 
		super();
	}
	public Account(String name)
	{
		this.setAccountHolder(name);
		this.setAccountBalance(0);
	
	}
	public Account(String name, Integer accountNum)
	{
		this.setAccountHolder(name);
		this.setAccountNumber(accountNum);
		this.setAccountBalance(0);
	}
	//End Constructors, note we only use the third constructor in this program
	//Add the account to the BankData
	public void initialize(){
		//log.debug("Adding this account to the Bank Data: " + this.toString() );
		BankData.getInstance().addAccount(this, this.getAccountNumber());
	}
	
	//Interface contracted methods, not view is NOT viewBalance, but gives the whole acouunt as a string
	//use the getter to obtain the accountBalance
	@Override
	public void view() {

		log.info(this.toString());
	}
	/**
	 * 
	 */
	@Override
	public void deposit(double deposit) {
		if(deposit <= 0)
		{
			log.info("Please deposit a non-zero positive value, or submit a withdrawal request instead.");
			return;
		}
		this.setAccountBalance(accountBalance + deposit);
		BankData.getInstance().updateAccount(this, this.accountNumber);
		log.info("Successfully deposited: " + deposit + " to " + this.getAccountHolder() + " 's account.");

			
	}

	@Override
	public void withdraw(double withdraw) {

		if(!checkFunds(withdraw))
		{
			log.info("Insufficiant Funds");
			return;
		}
		else
		{
			log.info("Account Balance was: " + this.getAccountBalance());
			this.setAccountBalance(accountBalance - withdraw);
			BankData.getInstance().updateAccount(this, this.accountNumber);
			log.info("New Account Balance is; " + this.getAccountBalance() + "Succesfully withdrew: " + withdraw);
		}
		
	}
	/**
	 * Checks if this account has enough funds to make a withdrawal.
	 * @param withdraw balance amount to be withdrawn
	 * @return
	 */
	public boolean checkFunds(double withdraw)
	{
		if(this.getAccountBalance() < withdraw)
		{
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountBalance=" + accountBalance + ", accountNumber=" + accountNumber + ", accountHolder=" + accountHolder + "]";
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	private void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	private int getAccountNumber() {
		return accountNumber;
	}
	private void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}
	private void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

}
