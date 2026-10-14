import java.util.ArrayList;

/**
 * Parent class for all accounts.
 * OOP ideas:
 *   ABSTRACTION   -> 'abstract' class: you cannot create a plain Account, only Savings/Current.
 *   INHERITANCE   -> SavingsAccount and CurrentAccount extend this class.
 *   POLYMORPHISM  -> withdraw() is declared here, but each child has its own rule.
 */
public abstract class Account {
    private int accountNumber;
    private String holderName;
    protected double balance;                       // protected: child classes can use it
    private ArrayList<String> transactions = new ArrayList<>();   // history of this account

    public Account(int accountNumber, String holderName, double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;
        addTransaction("Account opened with " + openingBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        addTransaction("Deposited " + amount + "  | Balance: " + balance);
    }

    // Every child MUST write its own version of this method
    public abstract void withdraw(double amount) throws InsufficientBalanceException;

    public abstract String getAccountType();

    protected void addTransaction(String text) {
        transactions.add(text);
    }

    public ArrayList<String> getTransactions() { return transactions; }
    public int getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return String.format("%d | %-10s | %-8s | Balance: %.2f",
                accountNumber, holderName, getAccountType(), balance);
    }
}
