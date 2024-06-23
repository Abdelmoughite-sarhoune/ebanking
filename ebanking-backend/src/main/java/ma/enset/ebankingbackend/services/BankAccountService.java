package ma.enset.ebankingbackend.services;

import ma.enset.ebankingbackend.eceptions.CustomerNotfoundException;
import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.entities.SavingAccount;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);

    CurrentAccount  saveCurrentBankAccount(double initialBalace, Long customerId, double overDraft) throws CustomerNotfoundException;
    SavingAccount saveSavingBankAccount(double initialBalace, Long customerId, double interestRate) throws CustomerNotfoundException;
    List<Customer> listCustomers();
    BankAccount getBankAccount(String accountId);
    void debit(String accountId, double amount, String description);
    void credit(String accountId, double amount, String description);
    void transfer(String accountIdSource, String accountIdDestination,double amount);
}
