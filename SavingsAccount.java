/** Savings account: must always keep a minimum balance. */
public class SavingsAccount extends Account {
    public static final double MIN_BALANCE = 500;

    public SavingsAccount(int accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);   // call parent constructor
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }
        if (balance - amount < MIN_BALANCE) {
            throw new InsufficientBalanceException(
                    "Savings account must keep minimum balance of " + MIN_BALANCE);
        }
        balance -= amount;
        addTransaction("Withdrew " + amount + "  | Balance: " + balance);
    }

    // Extra feature only savings accounts have
    public void addInterest() {
        double interest = balance * 0.04;      // 4% interest
        balance += interest;
        addTransaction("Interest added " + interest + "  | Balance: " + balance);
    }

    @Override
    public String getAccountType() { return "Savings"; }
}
