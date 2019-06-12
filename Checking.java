
package bankmanagement;

public class Checking extends Account{
    private static String accountType = "Checking";
    
    Checking(double initialDeposit){
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
        return this.getAccountNum() + "\t" + accountType + "\t" + this.getBalance() + "\t" + this.getInterest() + "%";
    }
    
}


