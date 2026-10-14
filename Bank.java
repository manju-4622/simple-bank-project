import java.util.HashMap;
import java.util.ArrayList;

/**
 * The bank keeps all accounts.
 * COLLECTIONS used:
 *   HashMap<Integer, Account> -> find account by account number
 *   ArrayList                 -> list of accounts to display
 */
public class Bank {
    private HashMap<Integer, Account> accounts = new HashMap<>();
    private int nextAccountNumber = 1001;

    public Account createSavings(String name, double openingBalance) {
        if (openingBalance < SavingsAccount.MIN_BALANCE) {
            throw new IllegalArgumentException("Savings needs at least " + SavingsAccount.MIN_BALANCE + " to open.");
        }
        Account acc = new SavingsAccount(nextAccountNumber++, name, openingBalance);
        accounts.put(acc.getAccountNumber(), acc);
        return acc;
    }

    public Account createCurrent(String name, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        Account acc = new CurrentAccount(nextAccountNumber++, name, openingBalance);
        accounts.put(acc.getAccountNumber(), acc);
        return acc;
    }

    // Returns the account or null if it does not exist
    public Account getAccount(int number) {
        return accounts.get(number);
    }

    public void transfer(int fromNo, int toNo, double amount) throws InsufficientBalanceException {
        Account from = accounts.get(fromNo);
        Account to = accounts.get(toNo);
        if (from == null || to == null) {
            throw new IllegalArgumentException("One of the account numbers is invalid.");
        }
        from.withdraw(amount);     // if this fails, the exception stops the transfer
        to.deposit(amount);
    }

    public ArrayList<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
}
