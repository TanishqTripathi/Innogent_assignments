// Banking System with Deposit and Withdrawal Functionality
// Using Interfaces, Abstract Classes, Inheritance, and Polymorphism
// Interface Segregation Principle (ISP) and Liskov Substitution Principle (LSP) applied
interface depositable {
    void deposit(double amount);
}

interface withdrawable {
    void withdraw(double amount);
}

// Solid principles applied ,

abstract class account implements depositable, withdrawable {
    private String accountNumber;
    private double balance;

    public account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. New Balance: " + balance);
    }

    @Override
    public abstract void withdraw(double amount);
}

// solid principles applied , inheritance, polymorphism, overrriding and
// overloading both

class savingaccount extends account {
    private static final double MIN_BALANCE = 1000;

    public savingaccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public savingaccount(String accountNumber, int balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount >= MIN_BALANCE) {
            setBalance(getBalance() - amount);
            System.out.println(amount + " withdrawn. Remaining Balance: " + getBalance());
        } else {
            System.out.println("Insufficient balance! Required " + MIN_BALANCE);
        }
    }
}
// solid principles applied , open close principle, inheritance, polymorphism,
// overrriding and overloading both

class currentaccount extends account {
    private static final int OVERDRAFT_LIMIT = -1000;

    public currentaccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount >= OVERDRAFT_LIMIT) {
            setBalance(getBalance() - amount);
            System.out.println(amount + " withdrawn. Remaining Balance: " + getBalance());
        } else {
            System.out.println("Overdraft limit exceeded! Limit: " + OVERDRAFT_LIMIT);
        }
    }
}

// Dependency Inversion Principle (DIP) applied

class bank {
    public void processdeposit(depositable d, double amount) {
        d.deposit(amount);
    }

    public void processWithdrawal(withdrawable w, double amount) {
        w.withdraw(amount);
    }
}

public class banking {
    public static void main(String[] args) {
        savingaccount sa = new savingaccount("SA123", 5000);
        currentaccount ca = new currentaccount("CA123", 2000);
        bank banks = new bank();
        banks.processdeposit(sa, 1500);
        banks.processWithdrawal(sa, 3000);
        banks.processdeposit(ca, 1000);
        banks.processWithdrawal(ca, 2500);

    }
}