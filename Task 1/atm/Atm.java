package atm;

import java.util.Scanner;

public class Atm {
    private Bank bank;
    private Scanner scanner;

    public Atm(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            if (bank.authenticate(accountNumber, pin)) {
                System.out.println("Login successful!");
                Account account = bank.getAccount(accountNumber);
                showMenu(account);
                return;
            } else {
                System.out.println("Invalid credentials. Try again.");
            }
        }
        System.out.println("Too many failed attempts. Goodbye!");
    }

    private void showMenu(Account account) {
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Change PIN");
            System.out.println("6. Fund Transfer");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> checkBalance(account);
                case 2 -> deposit(account);
                case 3 -> withdraw(account);
                case 4 -> miniStatement(account);
                case 5 -> changePin(account);
                case 6 -> fundTransfer(account);
                case 7 -> {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void checkBalance(Account account) {
        System.out.println("Your current balance is: $" + account.getBalance());
    }

    private void deposit(Account account) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            account.addTransaction(new Transaction("Deposit", amount));
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void withdraw(Account account) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            account.addTransaction(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    private void miniStatement(Account account) {
        System.out.println("--- Mini Statement ---");
        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : account.getTransactions()) {
                System.out.printf("%s: $%.2f on %s\n", t.getType(), t.getAmount(), t.getTimestamp());
            }
        }
    }

    private void changePin(Account account) {
        System.out.print("Enter your new PIN: ");
        String newPin = scanner.nextLine();
        account.getPin();
        System.out.println("PIN changed successfully.");
    }

    private void fundTransfer(Account account) {
        System.out.print("Enter the recipient's account number: ");
        String recipientAccount = scanner.nextLine();
        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        Account recipient = bank.getAccount(recipientAccount);
        if (recipient != null && amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            account.addTransaction(new Transaction("Transfer Out", amount));
            recipient.addTransaction(new Transaction("Transfer In", amount));
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed. Check details or balance.");
        }
    }
}
