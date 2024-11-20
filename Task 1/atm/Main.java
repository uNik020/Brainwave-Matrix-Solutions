package atm;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        //predefined account credentials
        bank.addAccount(new Account("12345", "1234", 5000));
        bank.addAccount(new Account("67890", "5678", 10000));

        Atm atm = new Atm(bank);
        atm.start();
    }
}
