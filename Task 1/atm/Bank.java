package atm;

import java.util.HashMap;

public class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean authenticate(String accountNumber, String pin) {
        Account account = accounts.get(accountNumber);
        return account != null && account.getPin().equals(pin);
    }
}
