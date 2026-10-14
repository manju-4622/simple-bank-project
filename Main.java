import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Bank bank = new Bank();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== SIMPLE BANK =====");
            System.out.println("1. Open savings account");
            System.out.println("2. Open current account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Check balance");
            System.out.println("7. Mini statement");
            System.out.println("8. List all accounts");
            System.out.println("9. Add interest (savings)");
            System.out.println("0. Exit");
            int choice = readInt("Enter choice: ");

            // try-catch: show a friendly message instead of crashing
            try {
                switch (choice) {
                    case 1: openAccount(true); break;
                    case 2: openAccount(false); break;
                    case 3: getAcc().deposit(readDouble("Amount: ")); System.out.println("Deposited."); break;
                    case 4: getAcc().withdraw(readDouble("Amount: ")); System.out.println("Withdrawn."); break;
                    case 5: transfer(); break;
                    case 6: System.out.println(getAcc()); break;
                    case 7: for (String line : getAcc().getTransactions()) System.out.println("  " + line); break;
                    case 8: for (Account a : bank.getAllAccounts()) System.out.println(a); break;
                    case 9: addInterest(); break;
                    case 0: System.out.println("Thank you!"); return;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InsufficientBalanceException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static void openAccount(boolean savings) {
        System.out.print("Holder name: ");
        String name = sc.nextLine();
        double amount = readDouble("Opening balance: ");
        Account acc = savings ? bank.createSavings(name, amount) : bank.createCurrent(name, amount);
        System.out.println("Account created! Account number: " + acc.getAccountNumber());
    }

    // Asks for an account number and returns that account
    static Account getAcc() {
        int no = readInt("Account number: ");
        Account acc = bank.getAccount(no);
        if (acc == null) throw new IllegalArgumentException("Account " + no + " not found.");
        return acc;
    }

    static void transfer() throws InsufficientBalanceException {
        int from = readInt("From account: ");
        int to = readInt("To account: ");
        bank.transfer(from, to, readDouble("Amount: "));
        System.out.println("Transfer successful.");
    }

    static void addInterest() {
        Account acc = getAcc();
        if (acc instanceof SavingsAccount) {          // check the real type
            ((SavingsAccount) acc).addInterest();     // convert (cast) to use the savings-only method
            System.out.println("Interest added. New balance: " + acc.getBalance());
        } else {
            System.out.println("Only savings accounts earn interest.");
        }
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Please enter a whole number."); }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Please enter a number."); }
        }
    }
}
