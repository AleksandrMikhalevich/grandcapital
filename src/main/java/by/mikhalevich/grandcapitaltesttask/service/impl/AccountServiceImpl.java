package by.mikhalevich.grandcapitaltesttask.service.impl;

import by.mikhalevich.grandcapitaltesttask.api.exception.DataNotFoundException;
import by.mikhalevich.grandcapitaltesttask.api.exception.MoneyTransferException;
import by.mikhalevich.grandcapitaltesttask.dao.model.Account;
import by.mikhalevich.grandcapitaltesttask.dao.repository.AccountRepository;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.*;

/**
 * Класс-сервис для работы со счетами пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-27 16:14
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    /**
     * Репозиторий счетов
     */
    private final AccountRepository accountRepository;

    /**
     * Сервис кэша Redis
     */
    private final RedisBalanceCache redisBalanceCache;

    /**
     * Увеличение баланса счетов всех пользователей каждые 30 секунд на 10%
     */
    @Override
    @Scheduled(fixedRate = 30000)
    public void updateBalances() {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            BigDecimal initialDeposit = redisBalanceCache.getInitialBalance(account.getId(), account.getBalance());
            BigDecimal maxBalance = initialDeposit.multiply(BigDecimal.valueOf(2.07));
            BigDecimal currentBalance = account.getBalance();
            BigDecimal candidate = currentBalance.multiply(BigDecimal.valueOf(1.1));
            if (candidate.compareTo(maxBalance) > 0) {
                candidate = maxBalance;
            }
            account.setBalance(candidate);
        }
        accountRepository.saveAll(accounts);
    }

    /**
     * Перевод средств от одного пользователя к другому
     */
    public void transferMoney(Long fromUserId, Long toUserId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MoneyTransferException(MONEY_TRANSFER_AMOUNT_ERROR_MESSAGE);
        }
        Account senderAccount = accountRepository.findByUserIdForUpdate(fromUserId)
                .orElseThrow(() -> new DataNotFoundException(SENDER_ACCOUNT_NOT_FOUND_MESSAGE));
        Account receiverAccount = accountRepository.findByUserIdForUpdate(toUserId)
                .orElseThrow(() -> new DataNotFoundException(RECEIVER_ACCOUNT_NOT_FOUND_MESSAGE));
        if (senderAccount.getBalance().compareTo(amount) < 0) {
            throw new MoneyTransferException(MONEY_TRANSFER_NOT_ENOUGH_MONEY_MESSAGE);
        }
        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
    }
}
