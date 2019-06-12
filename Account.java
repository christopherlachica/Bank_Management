
package bankmanagement;

public class Account {
    
    private double balance = 0;
    private double interest = 0.2;
    private int accountNum;
    private static int numOfAccounts = 1;
    
    Account(){
        accountNum = numOfAccounts++;
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public double getInterest() {
        return interest * 100;
    }


    public void setInterest(double interest) {
        this.interest = interest;
    }


    public int getAccountNum() {
        return accountNum;
    }
    
    public void withdraw(double amount){
        if(amount + 5 > balance){
            System.out.println("You don't have enough funds!");
            System.out.println("Please try again.\n");
            return;
        }
        
        balance = balance - amount + 5;
        checkInterest(0);
        System.out.println("You have withdrawn $" + amount + " and incurred a fee of $5");
        System.out.println("Your current balance is $" + balance);
    }
    
    public void deposit(double amount){
        if(amount <= 0){
            System.out.println("You cannot deposit negative values");
            return;
        }
        checkInterest(amount);
        amount = amount + amount * interest;
        balance = balance + amount;
        System.out.println("You have deposited $" + amount + " with an interest rate of " + (interest * 100) + "%");
        System.out.println("Your current balance is $" + balance);
    }

    public void checkInterest(double amount) {
        if(balance + amount> 10000){
            interest = 0.05;
        }else{
            interest = 0.02;
        }
    }
    
    public String toData(){
        return "";
    }
    
}

