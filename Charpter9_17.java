package test;
import java.util.*;

public class CH9_17 {
	public static void main(String[] args) {
		Account test  = new Account(1122, 20000);
		test.withdraw(2500);
		test.deposit(3000);
		test.setAnnualInterstRate(0.045);
		System.out.println(test.getBalance());
		System.out.println(test.getMonthlyInterest());
		
		System.out.println( test.getDateCreated());

	}

}

class Account{
	private int id;
	private	double balance;
	private	static double annualInterestRate =0;
	private	java.util.Date dateCreated;

	
	public Account(){
		id =0;
		balance =0;
		dateCreated = new java.util.Date();
	}
	public Account(int id, double balance){
		this.id = id;
		this.balance = balance;
		dateCreated = new java.util.Date();
	}
	
	public int getID() {
		return this.id;
		//return id;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public static double getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	public void setID(int  s) {
		this.id = s;
	}
	public void setBalance(double s) {
		this.balance = s;
	}
	
	public static void setAnnualInterstRate( double s) {
		annualInterestRate = s;
	}
	
	public Date getDateCreated () {
		return this.dateCreated;
	}
	
	public double getMonthlyInterestRate() {
		return （annualInterestRate/12);
	}
	
	public double getMonthlyInterest() {
		return balance*(annualInterestRate/12.0);
	}
	
	public void withdraw(double s) {
		balance -=s;
	}
	
	public void deposit(double s) {
		balance +=s;
	}
		
}
