package com.revature.banking;

public class SavingAccount extends BankAccount{
	private double interest;
	public SavingAccount(String name, double balance, String type, double interest) {
		super(name,balance, type);
		this.interest = interest;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		return super.toString() + "interest=" + interest;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SavingAccount other = (SavingAccount) obj;
		return !toString().equals(other.toString());

	}
	
	

}
