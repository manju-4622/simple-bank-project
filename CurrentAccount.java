/** Current account: allowed to go below zero up to an overdraft limit. */
public class CurrentAccount extends Account {
    public static final double OVERDRAFT_LIMIT = 5000;

    public CurrentAccount(int accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }
        if (balance - amount < -OVERDRAFT_LIMIT) {
            throw new InsufficientBalanceException(
                    "Overdraft limit of " + OVERDRAFT_LIMIT + " exceeded.");
        }
        balance -= amount;
        addTransaction("Withdrew " + amount + "  | Balance: " + balance);
    }

    @Override
    public String getAccountType() { return "Current"; }
}
