
package bankmanagement;
import java.util.Scanner;

public class Customer {

    private final String firstName;
    private final String lastName;
    final String ssn;
    private final String phone;
    private final Account account;
    private int customerID;
    private static int customerNum = 1;
    String customerInfo;
    
    public Customer(String firstName, String lastName, String ssn, String phone, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.phone = phone;
        this.account = account;
        customerID = customerNum ++;
    }
    
    @Override
    public String toString(){
        customerInfo = getCustomerID() + "\t" + firstName + "\t" + lastName + "\t" + ssn + "\t" + phone;
        return customerInfo;
    }
    
    public int getCustomerID(){
        return this.customerID;
    }
    
    public String info(){
        return  "First Name: " + firstName + 
                "\nLast Name: " + lastName + 
                "\nSSN: " + ssn +
                "\n" + account;
    }
    
    //getter for account
    Account getAccount(){
        return account;
    }
}
