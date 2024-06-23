package ma.enset.ebankingbackend.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.eceptions.CustomerNotfoundException;
import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.repositories.AccountOperationRepository;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import ma.enset.ebankingbackend.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService{
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;



    @Override
    public Customer saveCustomer(Customer customer) {
       log.info("Saving new Customer");
       Customer savedCustomer= customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalace, Long customerId, double overDraft) throws CustomerNotfoundException {

        Customer customer=customerRepository.findById(customerId).orElse(null);
        if (customer==null)
            throw new CustomerNotfoundException("Customer  not found ");
        CurrentAccount currentAccount= new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalace);
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);
        CurrentAccount savedBankAccount=bankAccountRepository.save(currentAccount);
        return savedBankAccount;
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalace, Long customerId, double interestRate) throws CustomerNotfoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if (customer==null)
            throw new CustomerNotfoundException("Customer  not found ");
        SavingAccount savingAccount= new SavingAccount();

        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalace);
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);
        CurrentAccount savedBankAccount=bankAccountRepository.save(savingAccount);
        return savedBankAccount;
    }
        return null;
    }




        return null;
    }

    @Override
    public List<Customer> listCustomers() {
        return null;
    }

    @Override
    public BankAccount getBankAccount(String accountId) {
        return null;
    }

    @Override
    public void debit(String accountId, double amount, String description) {

    }

    @Override
    public void credit(String accountId, double amount, String description) {

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) {

    }
}
