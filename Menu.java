
package bankmanagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu {
    
    Scanner keyboard = new Scanner(System.in);
    Scanner scanner = new Scanner("customer.txt");
    BankManagement bank = new BankManagement();
    boolean exit;
    int customerID = 0;
    Date date = new Date();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

    public static void main(String[] args){
        Menu menu = new Menu();
        menu.startMenu();
    }
    
    public void startMenu(){
        header();
        while(!exit){
            printMenu();
            int select = getInput();
            performAction(select);
        }
    }

    private void header() {
        System.out.println(" +==================================+");
        System.out.println(" |      Welcome to the Bank!        |");
        System.out.println(" +==================================+");
    }

    private void printMenu() {
        System.out.println("+------------------------------------+");
        System.out.println("|Please choose one of the following  |");
        System.out.println("|  1. Sign up for Account            |");
        System.out.println("|  2. Deposit                        |");
        System.out.println("|  3. Withdraw                       |");
        System.out.println("|  4. Loan                           |");
        System.out.println("|  5. Check Balance Inquiry          |");
        System.out.println("|  0. Exit the App                   |");
        System.out.println("+------------------------------------+");
    }

    private int getInput() {
        int select = -1;
        do{
            
            System.out.print("\nPlease choose from the selection: ");
            try{
                select = Integer.parseInt(keyboard.nextLine());
            }catch(NumberFormatException ex){
                System.out.println("Invalid entry! Please enter a number of choice");
            }
            if(select < 0 || select > 5 ){
                System.out.println("The selection is outside the range. Please enter a number of choice");
            }
        }while(select < 0 || select > 5 );
        return select;
    }

    private void performAction(int select) {
        switch(select){
            case 0:
                System.out.println("Thank you for banking with us.");
                System.out.println("Have a great day!");
                System.exit(0);
                break;
            case 1:
                createAccount();
                break;
            case 2:
                makeDeposit();
                break;
            case 3:
                makeWithdraw();
                break;
            case 4:
                makeLoan();
                break;
            case 5:
                checkBalance();
                break;
            default:
                System.out.println("Unknown error!");
        }
    }

    private void createAccount() {
        String firstName, lastName, ssn, phone, accountType = "";
        double initialDeposit = 0;
        boolean valid = false;
        
        while(!valid){
            System.out.print("Please enter an account type(checking/savings): ");
            accountType = keyboard.nextLine();
            if(accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")){
                valid = true;
            }else{
                System.out.println("Invalid account type. Please enter checking or savings.");
            }
        }
        System.out.print("Please enter your first name: ");
        firstName = keyboard.nextLine();
        System.out.print("Please enter your last name: ");
        lastName = keyboard.nextLine();
        System.out.print("Please enter your social security number: ");
        ssn = keyboard.nextLine();
        System.out.print("Please enter your phone number: ");
        phone = keyboard.nextLine();
        valid = false;
        while(!valid){
            System.out.print("Please enter an initial deposit: ");
            try {
                initialDeposit = Double.parseDouble(keyboard.nextLine());
            }catch(NumberFormatException ex){
                System.out.println("Deposit must be a number.");
            }
            if(accountType.equalsIgnoreCase("checking")){
                if(initialDeposit < 200){
                    System.out.println("Checking accounts require a minimum of $200 dollars to open.");
                }else{
                    valid = true;
                }
            } else if(accountType.equalsIgnoreCase("savings")){
                if(initialDeposit < 100){
                    System.out.println("Savings accounts require a minimum of $100 dollars to open.");
                }else{
                    valid = true;
                }
            }
        }
        Account account;
        if(accountType.equalsIgnoreCase("checking")){
            account = new Checking(initialDeposit);
        }else{
            account = new Savings(initialDeposit);
        }
        
        Customer customer = new Customer(firstName, lastName, ssn, phone, account);
        bank.addCustomer(customer);
        
        String accountInfo = account.toData() + "\t" + customer.getCustomerID();
        String customerInfo = customer.toString();
        String transaction = account.getAccountNum() + "\t" + dateFormatter.format(date) + "\t" + "Deposit" + "\t\t" + initialDeposit;
        
        WriterReader.appendToTabDelimitedFile(customerInfo, "customer.txt");
        WriterReader.appendToTabDelimitedFile(accountInfo, "account.txt");
        WriterReader.appendToTabDelimitedFile(transaction, "transaction.txt");
        customerID++;
        
        System.out.println("Thank you! You have successfully created an account.\n");
        
    }
    
    private void makeDeposit() {
        double amount = 0;
        int account = selectAccount();
        System.out.println("+---------------------------------------+");
        System.out.print(" How much would you like to deposit: ");
        try{
            amount = Double.parseDouble(keyboard.nextLine());
        }catch(NumberFormatException ex){
            amount = 0;
        }
        System.out.println("+---------------------------------------+");
        bank.getCustomer(account).getAccount().deposit(amount);
        
        String transaction = bank.getCustomer(account).getAccount().getAccountNum() + "\t" + dateFormatter.format(date) + "\t" + "Deposit" + "\t\t" + amount;
        WriterReader.appendToTabDelimitedFile(transaction, "transaction.txt");
    }

    private void makeWithdraw() {
        double amount = 0;
        int account = selectAccount();
        System.out.println("+---------------------------------------+");
        System.out.print(" How much would you like to withdraw: ");
        try{
            amount = Double.parseDouble(keyboard.nextLine());
        }catch(NumberFormatException ex){
            amount = 0;
        }
        System.out.println("+---------------------------------------+");
        bank.getCustomer(account).getAccount().withdraw(amount);
        
        String transaction = bank.getCustomer(account).getAccount().getAccountNum() + "\t" + dateFormatter.format(date) + "\t" + "Withdraw" + "\t" + amount;
        WriterReader.appendToTabDelimitedFile(transaction, "transaction.txt");
    }

    private void makeLoan() {
        
    }

    private void checkBalance() {
        double amount = 0;
        int account = selectAccount();
        if(account >= 0){
            System.out.println(bank.getCustomer(account).getAccount());
        }
    }

    private int selectAccount() {
        ArrayList<Customer> customers = bank.getCustomers();
        int account = -1;
        if(customers.size() <= 0){
            System.out.println("No customer in the system. Please create a new account.");
            return -1;
        }
        for(int i = 0; i < customers.size(); i++ ){
            System.out.println((i+1) + ") " + customers.get(i).info());
        }
        while(account < 0 || account > customers.size()){
            System.out.print("Please select from the accounts: ");
            try{
                account = Integer.parseInt(keyboard.nextLine()) -1;
            }catch(NumberFormatException ex){
                account = -1;
            }
            if(account < 0 || account > customers.size()){
            System.out.println("You have selected the wrong account selection.");
            account = -1;
            }
        }
        return account;
    }
}

