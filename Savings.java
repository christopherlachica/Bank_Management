
package bankmanagement;

public class Savings extends Account {
    private static String accountType = "Savings";
    
        Savings(double initialDeposit){
        super();
        this.setBalance(initialDeposit);
        checkInterest(0);
    }
    
    @Override
    public String toString(){
        return "Account type: " + accountType + " Account\n" +
                "Account Number: " + this.getAccountNum();
    }
    
    @Override
    public String toData(){
        return this.getAccountNum() + "\t" + accountType + "\t\t" + this.getBalance() + "\t" + this.getInterest() + "%";
                 
    }
}

